FROM java:8
WORKDIR /usr/student/app
COPY ./target/studentregistration-0.0.1-SNAPSHOT.jar /usr/student/app
EXPOSE 8080
CMD ["java", "-jar", "studentregistration-0.0.1-SNAPSHOT.jar"]