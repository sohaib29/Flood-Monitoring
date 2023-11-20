# Real-Time Flood-Monitoring

The Real-Time Flood Monitoring application comprises a lightweight Frontend, responsible for the user interface and developed using Angular. On the other hand, the Backend is responsible for retrieving responses from Environment Agency Real Time flood-monitoring APIs - to get infromation related to Monitoring Stations, processing their readings, and converting them into a readable format for the Frontend. The Backend is implemented in Spring Boot.

## Angular Project Setup

To install an Angular project, you'll need to have Node.js and Angular installed on your machine.

Navigate to the Project Directory

```bash
  cd src/main/webapp
  npm install
```

Run Angular Application

```bash
  ng serve
```
It will run on http://localhost:4200/

## Springboot Project Setup

To install an Springboot project, you'll need to have Maven and JDK 11 installed on your machine.

Navigate to the Project Directory

```bash
  mvn clean install
```

Run Springboot Application

```bash
  mvn spring-boot:run
```
It will run on http://localhost:8080/





