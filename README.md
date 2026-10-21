# driver-service

Microservicio de gestión de conductores: perfil del operador, licencia vigente, calificación de seguridad y certificaciones. Base de datos propia `driver_db`.

**Stack:** Java 17 · Spring Boot 3.3 · Maven · PostgreSQL  
**Puerto:** 8082

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/drivers` | Lista todos los conductores |
| GET | `/drivers/{id}` | Obtiene un conductor (404 si no existe) |
| GET | `/drivers/by-vehicle/{vehicleId}` | Conductor asignado a un vehículo (404 si no hay) |
| POST | `/drivers` | Crea un conductor (201 + Location) |

## Modelo (`drivers`)

`id`, `fullName`, `licenseCategory`, `licenseExpiration`, `safetyScore`, `safetyCertifications`, `vehicleId`, `assignmentId`


## Cómo ejecutarlo

```bash
# Antes: CREATE DATABASE driver_db;  (desde psql)
mvn spring-boot:run
```

## Diagramas

Los diagramas editables en draw.io están en `docs/driver-service-diagramas.drawio` (páginas: componentes y secuencia).
Las mismas vistas en Mermaid (se renderizan directo en GitHub):

### Componentes

```mermaid
flowchart LR
    C["Cliente<br/>(fleet-composite-service / Postman)"] -->|HTTP REST| DC
    subgraph DSV["driver-service :8082"]
        DC["DriverController<br/>(RestController)"] --> DS["DriverService<br/>(Service)"]
        DS --> DR["DriverRepository<br/>(Spring Data JPA)"]
        SEED["DataSeeder"] --> DR
    end
    DR -->|JDBC / Hibernate| DB[("PostgreSQL<br/>driver_db")]
```

### Secuencia

```mermaid
sequenceDiagram
    participant C as Cliente
    participant DC as DriverController
    participant DS as DriverService
    participant DR as DriverRepository
    participant DB as PostgreSQL driver_db
    C->>DC: GET /drivers/by-vehicle/{vehicleId}
    DC->>DS: findByVehicleId(vehicleId)
    DS->>DR: findByVehicleId(vehicleId)
    DR->>DB: SELECT * FROM drivers WHERE vehicle_id = ?
    DB-->>DR: fila o sin resultado
    DR-->>DS: Optional con el conductor
    alt no hay conductor asignado
        DS-->>DC: ResponseStatusException 404
        DC-->>C: 404 Not Found
    else existe
        DS-->>DC: Driver
        DC-->>C: 200 OK + JSON
    end
```
