# Invitation-Scheduler
Partner Invitation Scheduler services with Java 8 + Spring Boot and UI with React and Javascript

# Service Details
Saves the list of partners fetched from a json file located in resources folder and adds to a h2 Database.
Fetches the list of availale partners along with relevant office details based on the distance criteria passed from the UI.

# Distance Calculation
The distance is calculated considering the starting latitude and longitude specified in the application.properties file.

# Sample Queries

http://localhost:8080/api/partners/ : returns List of All the Partners available.

http://localhost:8443/api/partners/search?distance=10 : returns a List of Partners who are located within 10 km distance from the source location.
