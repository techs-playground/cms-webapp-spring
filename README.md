This repo contains the source code of a CMS application.

The back-end was written using Java with Spring Boot.

The front-end was written using AngularJS.

### Running the Application
 
First create a docker network:
 
  ```bash
 $ docker network create cms-app
  ```
  
Then check if was successfully created:
 
  ```bash
 $ docker network ls
  ```
  
Run an container with MongoDB:

 ```bash
$ docker run -d --name mongodb --net cms-app -p 27017:27017 mongo:3.4.10
 ``` 
 
 Run an container with Mongo Express to visualize the data inside MongoDB database:
 
  ```bash
 $ docker run -d --name mongo-express --link mongodb:mongo --net cms-app -p 8081:8081 mongo-express
  ```

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
 
Or run the application image. First build the image with the docker-maven-plugin:
 
```bash
 $ mvn clean install docker:build
```
  
Check if the image was created successfully:
  
```bash
 $ docker images
```
 
 Now run the container:
 
  ```bash
 $ docker run -d --name cms-app --link mongodb:mongodb --net cms-app -p 8080:8080 filipebezerra/cms-app:latest
  ```