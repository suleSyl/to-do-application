# TO-DO App

In this project, a To-do application is created that includes backend development with Spring Boot and front end development with React. 
Within this application, users will be able to create accounts and create their own to-do lists.

# Technology Stack

- Java 8
- Spring Boot
- Spring Data Couchbase
- Couchbase
- Swagger
- Maven
- Docker
- JUnit and Mockito
- React

# Building the Application

To build the To-Do Application, follow these steps:

git clone https://github.com/suleSyl/to-do-application
cd to-do-application

For backend:

cd to-do-application-backend
mvn clean install

For frontend:
cd ../to-do-application-ui
npm install
npm run build

# Running Tests

Execute the following command to run the unit tests:

cd to-do-application/to-do-application-backend
mvn test

# Running the Application with Dependencies

In order to run the application with its dependencies, use Docker. Follow these steps:

Pull the Docker image for backend from Docker Hub
docker pull 73511111/to-do-application-backend:latest
docker run -p 8080:8080 73511111/to-do-application-backend:latest

Pull the Docker image for frontend from Docker Hub
docker pull 73511111/to-do-application-frontend:latest
docker run -p 3000:3000 73511111/to-do-application-frontend:latest


# API Documentation

Access the API documentation:
http://localhost:8080/v2/api-docs
 
Access through the Swagger interface:
http://localhost:8080/swagger-ui/index.html#/