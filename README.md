# AREP-LAB06
Autor: Cristin David Naranjo Orjuela
## Taller: Security Application Design
El objetivo de este taller es integrar un sistema de logeo y registro a la aplicación web anteriormente desarrollada de administración de propiedades inmobiliarias en Spring-Boot, donde el usuario puede consultar, ingresar, editar y eliminar registros. Adicionalmente se quiere desplegar en AWS con dos instancias, una para alojar los archivos del front la cual hará las peticiones a la otra instancia que tendrá todo el backend de la aplicación

## Instalación y Ejecución
Las siguientes instrucciones son para ejecutar el proyecto LOCALMENTE. El primer paso es instalar java, git y maven en su equipo con las siguientes versiones:

* Apache Maven 3.9.6

* Java 17
 
y luego de esto clonar el repositorio desde la terminal de la siguiente manera:

```
https://github.com/cristiandavid0124/Arem-Lab-6.git
```


Seguido de esto, se ingresa a la carpeta resultante y se ejecutan los siguientes comandos:

* Para compilar el proyecto

```
mvn clean install
```

* Finalmente para ejecutar la aplicación

```
mvn spring-boot:run
```
luego entramos a localhost:8080 para interactuar con la aplicación 
![image](https://github.com/user-attachments/assets/0ab97f37-066b-4b09-8cac-223d445b225e)



La aplicación guardará la contraseña encriptada con el algoritmo SHA-256 cuando ceamos el usuario.


Si ponemos una contraseña que no es la del usuario saldra 
![image](https://github.com/user-attachments/assets/4dd1a92a-73fb-46b1-b305-970268fecc40)

luego de esto podemos añadir eliminar o editar propiedades que son solo del propio usuario del resto de usuarios solo se puedne veer en local se uso una base de datos h2 

![image](https://github.com/user-attachments/assets/2253353e-57c9-499d-b8a8-6194fbb042e5)

editamos

![image](https://github.com/user-attachments/assets/32eb0417-e1b2-45a0-ab21-66603ba8c11c)

![image](https://github.com/user-attachments/assets/64685d30-7652-4122-9f0b-da4a9d4a3edc)


ahora vamos a probar que creando a otro usuario el no va a poder ni editar ni eliminar la propiedad del individuo

![image](https://github.com/user-attachments/assets/adbc19c1-0584-43ab-9c06-e96d0a3c1fc0)

![image](https://github.com/user-attachments/assets/c847b5b3-e79a-4251-a75b-7da268e2d56e)

![image](https://github.com/user-attachments/assets/99948d07-5d15-41f6-98a7-e2f4e17d11ad)

![image](https://github.com/user-attachments/assets/efce384f-8b99-4a6a-97a2-dff2580e4262)

por ultimo creamos una propiedad para este usuario

![image](https://github.com/user-attachments/assets/deedc5ad-043e-4384-b903-9cc9959d723d)


Test
![image](https://github.com/user-attachments/assets/567b0ac2-a62f-4e19-8abf-dbf2117f0969)




## Deployment in AWS
La aplicación se implementa en AWS EC2, donde hay dos instancias con Apache instalado. Una de ellas almacena los archivos del frontend, que incluyen HTML, CSS y JavaScript. La otra instancia ejecuta Spring Boot, gestionando todo el backend de la aplicación, incluyendo una base de datos en H2. Se instaló un certificado SSL en cada instancia utilizando Certbot (eff.org) para habilitar el acceso mediante HTTPS, tanto al frontend como para realizar solicitudes al backend.



video
https://github.com/user-attachments/assets/133d2d86-9dae-41bb-a10b-bc427f6f9dc9






## Arquitectura

1. **Property (Entity)**

    - Represents a real estate property in the database with attributes such as `id`, `address`, `price`, `size`, and `description`. This class includes getter and setter methods to access and modify these attributes.

2. **User (Entity)**

    - Represents a user in the system, mapped to a database table. Users are utilized for authentication and system management, with attributes like `id`, `username`, and `password` (which is encrypted).

3. **PropertyRepository (Repository)**

    - Provides CRUD operations for `Property` entities using JPA. It includes a custom query method to find properties by address.

4. **UserRepository (Repository)**

    - Manages CRUD operations for `User` entities. It includes a method to find a user by their username.

5. **PropertyService (Service)**

    - Contains the business logic related to property management. It interacts with the repository to create, update, delete, and retrieve properties.

6. **UserService (Service)**

    - Handles the business logic for user management, such as authentication and registration, and interacts with the repository for database operations.

7. **PropertyController (Controller)**

    - Provides API endpoints to manage properties (create, retrieve, update, delete). It communicates with `PropertyService` to execute business logic.

8. **UserController (Controller)**

    - Exposes API endpoints for user operations, such as registration and authentication. It communicates with `UserService` to handle these actions.





## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework for building microservices
- [MySQL](https://www.mysql.com/) - Relational Database

## Versioned

We use [Git](https://github.com/) for version control. For available versions, see the tags in this repository.

## Author

* **Cristian David Naranjo**











