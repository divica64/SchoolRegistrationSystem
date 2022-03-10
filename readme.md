
## School Registration System

## Description
This is a take home test to design and implement a simple school registration system.

## Technology

- **Spring Boot**     - Server side framework
- **JPA**             - Entity framework
- **Lombok**          - Provides automated getter/setters
- **Swagger**         - In-built swagger2 documentation support
- **Docker**          - Docker containers
- **Junit**           - Unit testing framework
- **MySQL**           - MySQL Database Server

## Application Structure

## Running the server locally
The StudentRegistration application can be started using any favourite IDE and its run configuration support. 

````
mvn spring-boot:run
````

##set up MySQL
Create and run an image of the MySQL database
````
docker run --name student-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 13306:3306 -d mysql
````

## Docker
StudentRegistration supports docker container out of the box. 
This boilerplate is meant to cater to both web based applications as well as scalable micro services written in Java. 
Please select one of the following two ways to use docker to build and run the application -

**Dockerfile**

To build a fresh image, use -
````
docker build -t studentregistration .
````
To run the new image, use -
````
docker run -p 8080:8080 studentregistration
````

**Docker-Compose**

To build a fresh image, use -
````
docker-compose build
````
To run the new image, use -
````
docker-compose up
````

## Swagger Documentation
Swagger documentation is in-built in this starter-kit and can be accessed at the following URL -
````
http://<host-name>:8080/swagger-ui.html
````

## Unit test cases
There are multiple unit test cases written to cover the CourseRegistrationController and StudentController

````
mvn clean test -Dtest=StudentRegistrationApplicationTests
````





## Request Payload

Create Course- POST "http://localhost:8080/v1/course/"
````
{
  "courseCode": "MTH987",
  "courseName": "Complex Mathematics",
  "rrn": "7b12d29f-a058-11ec-a675-79060c003893",
}
````

Update Course- PUT "http://localhost:8080/v1/course/"

````
{
  "rrn": "7b12d29f-a058-11ec-a675-79060c003893",
  "courseName": "Applied Mathematics",
  "courseCode": "MTH987"
}
````
Get Course
````
curl -X GET "http://localhost:8080/v1/course/7b12d29f-a058-11ec-a675-79060c003893" -H "accept: */*"
````

Delete Course
````
curl -X DELETE "http://localhost:8080/v1/course/7b12d29f-a058-11ec-a675-79060c003893" -H "accept: */*"
````

Courses without any student
````
curl -X GET "http://localhost:8080/v1/course/filter-by/student" -H "accept: */*"
````
Courses for a specific student
````
curl -X GET "http://localhost:8080/v1/course/filter-by/student/b3184b70-a059-11ec-a675-89e8aae31c91" -H "accept: */*"
````

Create Student- POST "http://localhost:8080/v1/student/"
````
{
  "dept": "Bio-Chemistry",
  "email": "jayjay@yahoo.com",
  "firstname": "Jonny",
  "lastname": "Jakes",
  "phone": "23390000000000",
  "studentRegistrationNo": "BCH2022001"
}
````

Update Student PUT "http://localhost:8080/v1/student/"
````
{
  "rrn": "d52994a1-a05c-11ec-8edb-43d2fe7ee76d",
  "studentRegistrationNo": "BCH2022001",
  "dept": "Bio-Chemistry",
  "firstname": "Jacob",
  "lastname": "Jakes",
  "phone": "23390000000000",
  "email": "jayjay@yahoo.com"
}
````


Get Student
````
curl -X GET "http://localhost:8080/v1/student/b3184b70-a059-11ec-a675-89e8aae31c91" -H "accept: */*"
````

Delete Student
````
curl -X DELETE "http://localhost:8080/v1/student/b3184b70-a059-11ec-a675-89e8aae31c91" -H "accept: */*"
````
Filter all students without any course
````
curl -X GET "http://localhost:8080/v1/student/filter-by/course" -H "accept: */*"
````

Filter all students with a specific course
````
curl -X GET "http://localhost:8080/v1/student/filter-by/course/CHM701" -H "accept: */*"
````
Student Course Registration
````
{
  "courseCodes": [
    "CHM301"
  ],
  "studentRegistrationNo": "BCH2022001"
}
````

