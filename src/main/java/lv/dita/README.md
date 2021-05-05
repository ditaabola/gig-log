##Introduction
A training project to learn Spring Boot. 
Simple gig logging app to log lists of managers, artists, venues and gigs the artists have performed in the venues.

##Libraries used:
* Spring Boot - version 2.4.0.
* Hibernate - version 5.3.10.
* Thymeleaf - version 5.3.0.
* H2 

##Setup
For building and running the application you need:
* JDK 11
* Maven 3

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the `main` method in the lv.dita.GigLogApplication class from your IDE.
Alternatively you can use the Spring Boot Maven plugin.
Build the project using:
```
manv clean install
```
Run using:
```
mvn spring-boot:run
```

The web application is accessible via localhost:8080

##Status
Project is in progress, this is the first draft, to showcase implementation of basic functionalities.