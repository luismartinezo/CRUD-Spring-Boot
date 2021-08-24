# CRUD-Spring-Boot
El proyecto realizado es un sistema CRUD  de productos  por medio de JPA a base de datos MySql.

## configuracion base de datos
* Si deseamos cambiar el puerto de la aplicacion lo hacemos en el archivo application.properties en la ruta: `\DockerJava\src\main\resources` apartado `server.port=8888` cambiamos por el numero 8888
* En el mismo archivo de configuracion podemos cambiar las credenciales de base de datos que son usuario y contraseña en `spring.datasource.username=sa` y `spring.datasource.password=sa`
  
## Configuracion Docker

* Luego de crear el royecto en java con la configuracion de base de datos mysql  
* Descargamos la imagen de mysql y creamos el contenedor con `docker run --name mysql-conexion -e MYSQL_ROOT_PASSWORD=sa -e MYSQL_DATABASE=dbdocker -e MYSQL_USER=sa -e MYSQL_PASSWORD=sa -d mysql:5.7`
* miramos que se halla creado y este corriendo, luego ingresamos con `docker exec -it mysql-conexion bash -l`   
* Luego accedemos a mysql con `mysql -usa -psa` usuario=sa y password=sa   
* Miramos las base de datos que hay creadas con `show databases;` debe estar nuestra la base de datos creada 
* Creamos la imagen con `docker build -t dockerjava:1 .`
* Asociamos el contenedor a la imagen creada anteriormente con `docker run -d -p 8089:8089 --name containerjava --link mysql-conexion dockerjava:1`
