# API de Empleados

Este repositorio contiene una API para gestionar empleados.

## Empleados Routes

| Método | Ruta                            | Descripción                                           | Datos Esperados                                      |
|--------|---------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| POST   | `/empleados/registrar`          | Registra un nuevo empleado                             | JSON de EmpleadoDTO                                  |
| GET    | `/empleados/obtener`            | Obtiene todos los empleados                            | -                                                     |
| GET    | `/empleados/obtener/{id}`      | Obtiene un empleado por su ID                          | -                                                     |
| DELETE | `/empleados/borrar/{id}`       | Borra un empleado por su ID                            | -                                                     |
| PUT    | `/empleados/editar/{id}`       | Modifica un empleado por su ID                         | JSON de EmpleadoDTO                                  |
| GET    | `/empleados/buscarDni/{dni}`  | Busca un empleado por su número de DNI                 | -                                                     |

### Datos Esperados para EmpleadoDTO

```json
{
    "nombre": "Nombre del Empleado",
    "dni": "12345678",
    "email": "empleado@example.com",
    "telefono": "1234567890"
}

```

# API de Empleados Comerciales

Este repositorio contiene una API para gestionar empleados comerciales, clientes y servicios asociados.

## Empleados Comerciales Routes

| Método | Ruta                            | Descripción                                           | Datos Esperados                                      |
|--------|---------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| POST   | `/comercial/registrar`          | Registra un nuevo empleado comercial                  | ```json { "id_empleado": 1 }```                       |
| GET    | `/comercial/obtener`            | Obtiene todos los empleados comerciales               | -                                                     |
| GET    | `/comercial/obtener/{id}`      | Obtiene un empleado comercial por su ID               | -                                                     |
| DELETE | `/comercial/borrar/{id}`       | Borra un empleado comercial por su ID                 | -                                                     |
| PUT    | `/comercial/editar/{id}`       | Modifica un empleado comercial por su ID              | ```json { "id_empleado": 1 }```                       |

## Clientes Routes

| Método | Ruta                                    | Descripción                                           | Datos Esperados                                      |
|--------|-----------------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| POST   | `/comercial/cliente/registrar`          | Registra un nuevo cliente                             | ```json { "nombre": "Nombre del Cliente", "dni": "12345678", "razonSocial": "Razón Social del Cliente", "cuil": "12345678901", "email": "cliente@example.com", "telefono": "1234567890", "registradoPor": 1 }``` |
| GET    | `/comercial/cliente/obtener`            | Obtiene todos los clientes                            | -                                                     |
| GET    | `/comercial/cliente/obtener/{id}`      | Obtiene un cliente por su ID                          | -                                                     |
| GET    | `/comercial/cliente/obtenerDni/{dni}`  | Obtiene un cliente por su número de DNI               | -                                                     |
| GET    | `/comercial/cliente/obtenerCuil/{cuil}`| Obtiene un cliente por su número de CUIL              | -                                                     |
| GET    | `/comercial/cliente/obtenerRazonSocial/{razonSocial}` | Obtiene un cliente por su razón social | -                                           |
| DELETE | `/comercial/cliente/borrar/{id}`       | Borra un cliente por su ID                            | -                                                     |
| DELETE | `/comercial/cliente/borrarDni/{dni}`  | Borra un cliente por su número de DNI                 | -                                                     |
| DELETE | `/comercial/cliente/borrarCuil/{cuil}`| Borra un cliente por su número de CUIL                | -                                                     |

## Servicios Routes

| Método | Ruta                                     | Descripción                                           | Datos Esperados                                      |
|--------|------------------------------------------|-------------------------------------------------------|-------------------------------------------------------|
| GET    | `/comercial/servicio/obtener/{id}`       | Obtiene un servicio por su ID                         | -                                                     |
| GET    | `/comercial/servicio/obtener`            | Obtiene todos los servicios                           | -                                                     |
| DELETE | `/comercial/servicio/borrar/{id}`        | Borra un servicio por su ID                           | -                                                     |
| POST   | `/comercial/servicio/registrar`          | Registra un nuevo servicio                            | ```json { "nombre": "Nombre del Servicio", "tipo": "Tipo de Servicio", "precio": 100.00, "duracion": "2023-12-01", "cliente_id": 1 }``` |
| PUT    | `/comercial/servicio/editar/{id}`        | Modifica un servicio por su ID                        | ```json { "nombre": "Nombre del Servicio", "tipo": "Tipo de Servicio", "precio": 100.00, "duracion": "2023-12-01", "cliente_id": 1 }``` |
