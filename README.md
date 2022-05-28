# Invitation-Scheduler
Partner Invitation Scheduler services with Java 8 + Spring Boot and UI with React and Javascript

# Service Details
Saves the list of partners fetched from a json file located in resources folder and adds to a h2 Database.
Fetches the list of availale partners along with relevant office details based on the distance criteria passed from the UI.

# Distance Calculation
The distance is calculated considering the starting latitude and longitude specified in the application.properties file.

# API Endpoints Examples
1.  http://localhost:8080/api/partners/ : returns List of All the Partners available.
2.  http://localhost:8080/api/partners/search?distance=10 : returns a List of Partners who are located within 10 km distance from the source location.


# UI Endpoint
http://localhost:3000/ : Opens the UI developed in React JS


## Architectural Diagram
![Alt text](architectural-diagram.png?raw=true "Architectural Diagram")


## Directory Info
![Alt text](directory-info.png?raw=true "Directory Info")


## Test Results
![Alt text](test-result.png?raw=true "Test Results")


## Test Coverage Details
![Alt text](test-coverage.png?raw=true "Test Coverage Details")


## Running JUnit and Coverage Test

1.  Backend API - Java
  
  i.  run mvn clean install, maven will create a jar at /target location along with test reports under /targer/surefire-reports.
  ii. Run maven jar with java -jar <jar-name>.jar command and it will start the backend application.

2.  UI - React JS
     
	i.  run npm start (at ui folder location)
  ii. Browser window will open with http://localhost:3000/ endpoint.


## Run Instructions
![Alt text](run-instructions.png?raw=true "Run Instructions")


## Database Design and Details
![Alt text](db-details.gif?raw=true "Database Design and Details")


## Application UI
![Alt text](application-working-ui.gif?raw=true "Application UI")


## Running JUnit and Coverage Test

1.  Using Eclipse / IntelliJ
      
	i.	Right click on your project in the Project Explorer then select "Coverage As" > "JUnit Test" / Run 'All Test' with Coverage. IDE will run the test and generate a report about the Junit execution as well as the coverage result. 

2.  Using Maven 
      
	i. Install Maven. 
      
	ii. Go to the project directory, then run mvn test. Maven will run the test and generate the Junit execution report. Coverage report will be generated at PROJECT_DIRECTORY\target\surefire-reports\
