RestBackEnd
RestBackEnd is a backend application developed using Java and JavaScript, designed to provide RESTful API services. The project includes Swagger documentation for API endpoints, facilitating easy integration and testing.

Features
RESTful API: Implements a set of RESTful endpoints for client-server communication.

Swagger Documentation: Includes swagger.json for interactive API documentation and testing.

Java & JavaScript: Combines Java for backend logic and JavaScript for additional functionalities.
github.com

Project Structure
graphql
Copy
Edit

RestBackEnd/
├── .idea/                  # Project configuration files
├── src/
│   └── main/               # Main application source code
├── app.js                  # JavaScript application entry point
├── pom.xml                 # Maven project configuration
├── swagger.json            # Swagger API documentation
└── .gitignore              # Git ignore rules
Getting Started
Prerequisites
Java Development Kit (JDK) installed

Maven installed

Node.js and npm installed (for JavaScript components)
github.com
+2
huggingface.co
+2
github.com
+2

Installation
Clone the repository:

bash
Copy
Edit
git clone https://github.com/Matorota/RestBackEnd.git
cd RestBackEnd
Build the project using Maven:

bash
Copy
Edit
mvn clean install
Run the application:

bash
Copy
Edit
mvn spring-boot:run
Access Swagger UI:

Navigate to http://localhost:8080/swagger-ui.html to view and interact with the API documentation.
