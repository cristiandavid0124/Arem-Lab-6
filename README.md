# Sistema CRUD de Gestión de Propiedades

## Autor : Cristian David Naranjo Orjuela

## Versiones y requisitos
- Git  Control de versiones
- apache-maven-3.9.9  Manejador de dependencias
- Java 17  Lenguaje de programación
- Docker  Plataforma de contenedores
- AWS (Amazon Web Services)  Plataforma de servicios en la nube
- Docker version 20.10.7

## Resumen del Proyecto
El Sistema de Gestión de Propiedades es una aplicación web diseñada para optimizar el proceso de gestión de anuncios inmobiliarios. El sistema proporciona una interfaz amigable tanto para administradores como para posibles clientes, permitiendo interactuar con la información de las propiedades de manera eficiente. Sus principales funcionalidades incluyen:
Listado de Propiedades: Los usuarios pueden ver un listado completo de propiedades disponibles para la venta o alquiler, junto con detalles esenciales como precio, ubicación, tamaño y descripciones.

1. **Operaciones CRUD**: Los administradores pueden crear, leer, actualizar y eliminar anuncios de propiedades. Esta funcionalidad permite la gestión en tiempo real de los datos de las propiedades, asegurando que los usuarios tengan acceso a la información más actualizada.

2. **Interacción del Usuario**: El sistema soporta interacciones a través de un frontend limpio e intuitivo desarrollado con HTML y JavaScript, permitiendo a los usuarios navegar, buscar y filtrar anuncios de propiedades según sus preferencias.

3. **Servicios Backend**: La aplicación está impulsada por un robusto backend implementado con Spring Boot, que maneja toda la lógica de negocio y el procesamiento de datos. Proporciona endpoints RESTful para una comunicación fluida entre el frontend y la base de datos.

4. **Gestión de Base de Datos**: Una base de datos MySQL alberga todos los datos de propiedades, utilizando JPA/Hibernate para un mapeo objeto-relacional eficiente y la persistencia de datos. Esto asegura que todas las operaciones CRUD se reflejen instantáneamente en la base de datos, manteniendo la integridad de los datos.

5. **Despliegue en AWS**: El sistema está alojado en  instancias EC2 de Amazon Web Services (AWS), proporcionando un entorno seguro y escalable para la aplicación.
   




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

6. entrar en  para veer su funcionamiento
 ```
http://localhost:8080/index.html
  ```


## Test y Pruebas de funcionamiento local

- post

![image](https://github.com/user-attachments/assets/3bf9dc68-6cc3-4656-8e9a-4a4a182aab93)
![image](https://github.com/user-attachments/assets/8e2ab835-4c6b-464a-a5e8-1897f09f066c)

- put modificamos la property que acabamos de agregar a casa de 3 pisos

![image](https://github.com/user-attachments/assets/f2b75122-de8b-4792-acd2-4b5158437ce9)

- delete borramos todas las property

![image](https://github.com/user-attachments/assets/5d566cf0-a250-46cf-ab38-c9eb4682fc3f)

Test

![testttt](https://github.com/user-attachments/assets/3c027dc4-640f-4ec0-98fc-434a6cf35a1c)


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
    - Devolver respuestas adecuadas al cliente, incluyendo códigos de estado HTTP y datos relevant

## Deployment

-creamos dos instancias ec2 una para la base de datos y otra para la aplicacion 

![image](https://github.com/user-attachments/assets/6b98c064-41cd-451e-a411-49d3fe8b95da)

- entramos a la instancia ec2 de la base de datos y instalammos mysql con el siguiente comando

 ```
  sudo yum install mysql-community-server -y
  ```

![descargando sevidor](https://github.com/user-attachments/assets/53b8695a-e84b-44e5-b3c5-df5b535e24f4)

- luego obtenemos la contraseña temporal de la base de datos y configuramos el servidor para que escuche a todos
  
 ```
  sudo cat /var/log/mysqld.log | grep 'temporary password'
 ```


  ![llaveBasededatos](https://github.com/user-attachments/assets/8866bbc7-dc95-43eb-abd3-d0c3b3462390)

- entramos al usuario root con
  
```
  sudo mysql -u root -p
 ```

- creamos un usuario y contraseña y la guardamos para luego colocarlo en nuestro codigo junto con la direcion ipde la maquina con esto ya tendriamos configurada la conexion a la base de datos luego de esto nos podemos salir con exit de la base de datos

```
CREATE USER 'mi_usuario'@'%' IDENTIFIED BY 'Cristiandavid0124.';
 ```

![image](https://github.com/user-attachments/assets/113a14dd-801e-46cf-8edd-539ce07a6eab)

- ahora modificamos ahora  el codigo para que se pueda consultar en la clase controller
  ![image](https://github.com/user-attachments/assets/f0b353ca-d106-4d2b-831e-adbe562a6690)


- ahora procedemos a iniciar la maquina virtual de la aplicacion y descargamos java jdk y maven y verificamos que esta instaladas

  ```
  sudo yum install java-17-amazon-corretto-headless
  ```

 ```
sudo yum install maven -y
 ```

![image](https://github.com/user-attachments/assets/ced3fdb2-4799-4bbe-89d3-a29a1e9c4e0b)


- luego procedemos a pasar nuestro codigo en una terminal de nuestro navegador a la maquina virtual con  spc

![guardar el codigo](https://github.com/user-attachments/assets/6add86a5-f230-4936-87f9-1e562d43ae6a)

- entramos a la carpeta del proyecto y corremos el jar del codigo con

 ```
cd AREM_Lab5
 ```

 ```
java -jar target/demo-0.0.1-SNAPSHOT.jar
 ```

- con esto ya podemos entrar   la aplicacion

![image](https://github.com/user-attachments/assets/ee59a01b-9350-42d1-90a7-213ca336f383)

## Video Probando funcionamiento


https://github.com/user-attachments/assets/cdcf30a9-8add-4796-94a9-938947b2a6e8


## Author

* **Cristian David Naranjo Orjuela** 




