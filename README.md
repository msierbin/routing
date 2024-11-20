# **Routing Service**

This is a Spring Boot service that calculates a land route between two countries using country border information. The service exposes a REST endpoint to fetch the route based on an origin and a destination.

---

## **Features**
- Calculates land routes between two countries based on their borders.
- Returns a single route if a valid land route exists.
- Returns an appropriate error message for unreachable destinations.
- Efficient algorithm for route calculation with caching or not.

---

## **Technologies Used**
- Java
- Spring Boot
- Maven
- MockMvc (for integration testing)

---

## **Prerequisites**
- Java 23 or later
- Maven 3.8 or later

---

## **Running the Application**

1. Clone the repository:
   ```bash
   mvn clean install

2. Run the application:
   ```bash
   mvn spring-boot:run
   
3. The application will start on port 8080.

4. You can access the service using the following endpoint.
   ```bash
   curl -X GET "http://localhost:8080/routing/CZE/ITA"

Expected response is `{"route":["CZE","AUT","ITA"]}`

 