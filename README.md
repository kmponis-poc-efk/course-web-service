# course-web-service
A Spring Boot REST app that connects on MongoDB to CRUD a course.

## Prerequisites
* To run the application you need to have java8, git, mvn and mongodb installed. To initialise the database you have to go to web application tab 'admin'.

## Run 
* Open a new terminal and move to your workspace.
* Download project using your username: 
    ```sh
    $ git clone https://[username]:zK7tMEqRKEUhZiV1oFuq@innersource.soprasteria.com/kostas.bonis/course-web-service.git
    ```
* Go to project: 
    ```sh
    $ cd course-web-service
    ```
* For Windows go to /src/main/resources and use the commented out logging file.
* Build project: 
    ```sh
    $ mvn clean install
    ```
* Start application: 
    ```sh
    $ java -jar target/course-web-service-0.0.1-SNAPSHOT.jar
    ```

## Use
* Go to browser: 
    ```sh
    localhost:9093/course/findAll?sessionId=testSession
    ```
    >If empty, initialise database from web UI (AngularJS application)