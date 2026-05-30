# productos-service

Microservicio Spring Boot para gestionar productos y practicar pruebas unitarias
con JUnit 5 y Mockito.

## Tecnologias

- Java 21
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5, Mockito y JaCoCo

## Estructura principal

```text
src/
├── main/java/com/universidad/productosservice/
│   ├── ProductosServiceApplication.java
│   ├── controller/ProductoController.java
│   ├── domain/Producto.java
│   ├── repository/ProductoRepository.java
│   └── service/
│       ├── ProductoService.java
│       └── ProductoServiceImpl.java
└── test/java/com/universidad/productosservice/
    ├── ProductosServiceApplicationTests.java
    └── service/ProductoServiceImplTest.java
```

## Ejecucion

Compilar el proyecto:

```bash
./mvnw compile
```

Ejecutar pruebas unitarias y generar el reporte JaCoCo:

```bash
./mvnw test
```

Iniciar la aplicacion:

```bash
./mvnw spring-boot:run
```

## Endpoints

| Metodo | Ruta | Descripcion |
| --- | --- | --- |
| POST | `/api/productos` | Crea un producto |
| GET | `/api/productos/{id}` | Busca un producto por id |
| PATCH | `/api/productos/{id}/stock` | Actualiza el stock |
| DELETE | `/api/productos/{id}` | Elimina un producto |

Ejemplo para crear:

```json
{
  "nombre": "Laptop",
  "precio": 1500.0,
  "stock": 10
}
```

## Pruebas

La suite `ProductoServiceImplTest` cubre:

- Creacion exitosa de productos.
- Busqueda por id existente y no existente.
- Validaciones de nombre, precio y stock con pruebas parametrizadas.
- Normalizacion del nombre con `ArgumentCaptor`.
- Actualizacion de stock.
- Eliminacion de producto existente.

Resultado de la ultima ejecucion:

```text
Tests run: 24, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

El reporte JaCoCo se genera en `target/site/jacoco/index.html`. La clase
`ProductoServiceImpl` queda con 100% de cobertura de lineas e instrucciones.

![Resultado de mvn test](docs/evidencias/mvn-test-build-success.png)
