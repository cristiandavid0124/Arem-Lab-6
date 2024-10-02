# Sistema CRUD de Gestión de Propiedades

## Autor : Cristian David Naranjo Orjuela


## Resumen del Proyecto
El Sistema de Gestión de Propiedades es una aplicación web diseñada para optimizar el proceso de gestión de anuncios inmobiliarios. El sistema proporciona una interfaz amigable tanto para administradores como para posibles clientes, permitiendo interactuar con la información de las propiedades de manera eficiente. Sus principales funcionalidades incluyen:
Listado de Propiedades: Los usuarios pueden ver un listado completo de propiedades disponibles para la venta o alquiler, junto con detalles esenciales como precio, ubicación, tamaño y descripciones.

1. **Operaciones CRUD**: Los administradores pueden crear, leer, actualizar y eliminar anuncios de propiedades. Esta funcionalidad permite la gestión en tiempo real de los datos de las propiedades, asegurando que los usuarios tengan acceso a la información más actualizada.

2. **Interacción del Usuario**: El sistema soporta interacciones a través de un frontend limpio e intuitivo desarrollado con HTML y JavaScript, permitiendo a los usuarios navegar, buscar y filtrar anuncios de propiedades según sus preferencias.

3. **Servicios Backend**: La aplicación está impulsada por un robusto backend implementado con Spring Boot, que maneja toda la lógica de negocio y el procesamiento de datos. Proporciona endpoints RESTful para una comunicación fluida entre el frontend y la base de datos.

4. **Gestión de Base de Datos**: Una base de datos MySQL alberga todos los datos de propiedades, utilizando JPA/Hibernate para un mapeo objeto-relacional eficiente y la persistencia de datos. Esto asegura que todas las operaciones CRUD se reflejen instantáneamente en la base de datos, manteniendo la integridad de los datos.

5. **Despliegue en AWS**: El sistema está alojado en  instancias EC2 de Amazon Web Services (AWS), proporcionando un entorno seguro y escalable para la aplicación.
   
## Arquitectura del Sistema
El sistema se divide en tres componentes principales:

- **Frontend**: Desarrollado utilizando HTML y JavaScript, el frontend proporciona una interfaz de usuario para interactuar con los anuncios de propiedades. Se comunica con el backend a través de  Fetch API.
  
- **Backend**: El backend está implementado utilizando Spring Boot, proporcionando endpoints RESTful para cada operación CRUD. Maneja la lógica de negocio y se comunica con la base de datos Sql desplegada en AWS con POST ,PUT, DELETE .
  
- **Base de Datos**: Una base de datos MySQL almacena los detalles de las propiedades. Se utiliza JPA/Hibernate para el mapeo objeto-relacional, asegurando la persistencia de datos para todas las operaciones CRUD esta se encuentra al


## Diseño de Clases

El diseño del sistema se basa en un enfoque orientado a objetos, utilizando clases que representan las entidades y servicios necesarios para la gestión de propiedades inmobiliarias. A continuación, se describen las principales clases del sistema:

- **Property**: 
  - **Descripción**: Representa la entidad propiedad, encapsulando todos los detalles relevantes de una propiedad inmobiliaria.
  - **Responsabilidades**:
    - **Encapsulación de datos**: Almacena y protege la información relacionada con una propiedad inmobiliaria, asegurando que los atributos se manejen correctamente a través de métodos getter y setter.
    - **Validación de datos**: Implementa validaciones a nivel de clase mediante anotaciones (como `@NotBlank` y `@Min`) para garantizar que los datos de una propiedad cumplan con las restricciones definidas antes de ser guardados en la base de datos.
    - **Representación en cadena**: Proporciona una representación en cadena de la propiedad a través del método `toString()`, lo que facilita la depuración y el registro de información.


- **PropertyService**: 
  - **Descripción**: Contiene la lógica de negocio relacionada con la gestión de propiedades. Actúa como intermediario entre el controlador y el repositorio.
  - **Responsabilidades**:
    - Crear, recuperar, actualizar y eliminar propiedades a través de métodos que interactúan con el repositorio.
    - Validar la lógica de negocio antes de realizar operaciones sobre las propiedades.
    - Manejar las excepciones que puedan surgir durante la interacción con el repositorio.

- **PropertyController**: 
  - **Descripción**: Maneja las solicitudes y respuestas HTTP para las operaciones relacionadas con las propiedades. Es el punto de entrada para las interacciones del cliente con el sistema.
  - **Responsabilidades**:
    - Recibir solicitudes HTTP y mapearlas a métodos del servicio correspondiente.
    - Validar los datos de entrada utilizando anotaciones de validación de Jakarta.
    - Devolver respuestas adecuadas al cliente, incluyendo códigos de estado HTTP y datos relevantes.





























## Instalación y Ejecución local

1. Clonar el repositorio.
 ```
https://github.com/cristiandavid0124/AREM_Lab5.git
  ```
2. instalar dependencias
   
 ```
mvn clean install
  ```

3. generar package
 ```
mvn package install
  ```
4. generar base de datos local docker

 ```
docker-compose up
  ```

6. ejecuta el codigo el codigo
 ```
java -jar target/demo-0.0.1-SNAPSHOT.jar
  ```  
