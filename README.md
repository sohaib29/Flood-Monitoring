# Real-Time Flood-Monitoring

The Real-Time Flood Monitoring application comprises a lightweight Frontend, responsible for the user interface and developed using Angular. On the other hand, the Backend is responsible for retrieving responses from Environment Agency Real Time flood-monitoring APIs - to get infromation related to Monitoring Stations, processing their readings, and converting them into a readable format for the Frontend. The Backend is implemented in Spring Boot.

The frontend is tasked with enabling users to observe a paginated table displaying all accessible stations. When a user clicks on a particular row, they will be directed to a dedicated station page, unveiling detailed information about the station and displaying graphs illustrating its readings from the last 24 hours.

The backend is responsible for initiating requests on startup to retrieve all stations and caches the data, allowing the frontend to access the station list without initiating API calls each time. The cache undergoes updates every 15 minutes. Additionally, it handles the conversion of response data, simplifying it for easy consumption by the frontend.

## Todo

- Fix a bug when dynamically generating multiple charts for different readings.
- Display station information as a dashboard along with its graphs.
- Implement proper error handling instead of using e.printStackTrace().
- Add unit and integration tests for both frontend and backend.
- Refactor code for backend services.
- Enhance the overall UI experience by improving style and design.
- Integrate Angular and Spring Boot to run and build as a single application.

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





