This repo contains the source code of a CMS application.

The back-end was written using Java with Spring Boot.

The front-end was written using AngularJS.

 ### Running the Application

 In the src/main/resources directory you can find a file called ```application.yaml```.

Open this file and enter your MongoDB host and port string as directed in the comment. This is the information the driver will use to connect.

```yml
host: 192.168.99.100
port: 27017
```

Run the application with maven:

 ```bash
$ mvn spring-boot:run
 ```

 And then point your browser to http://localhost:8080/swagger-ui.html to interact with the API using Swagger UI.