# Blog Application

This is a full-stack web application for a blog platform built using Angular for the frontend and Spring Boot for the backend. The application supports three roles: **STAFF**, **ADMIN**, and **User**.

## Features

### General Features:
- **User Authentication:** Users can sign up, log in, and log out securely.
- **Role-based Access Control:** Different roles have different permissions as described below.

### Role-based Features:

#### STAFF Role:
- Create, edit, and delete blog posts.
- Manage users (create, update, delete user accounts).

#### ADMIN Role:
- Create, edit, and delete blog posts.
- Manage users (create, update, delete user accounts).

#### User Role:
- View blog posts.
- Like or favorite blog posts.

## Technologies Used

### Frontend (Angular):
- Angular CLI
- PrimeNG
- Bootstrap (or any other UI framework you've used)

### Backend (Spring Boot):
- Spring Boot
- Spring Security for authentication and authorization
- Spring Data JPA for data persistence
- MySQL (or any other database system you've used)

## Installation

### Prerequisites:
Before starting the installation, make sure you have the following software installed on your system:

- **Node.js and npm**: Make sure Node.js and npm (Node Package Manager) are installed globally on your system. You can download them from [nodejs.org](https://nodejs.org/).
- **Angular CLI**: Install Angular CLI globally using npm if you haven't already:
  ```bash
  npm install -g @angular/cli

- **Java Development Kit (JDK):** Install JDK 8 or higher. You can download it from oracle.com/java.
- **MySQL Database:** Set up a MySQL database server. You can download MySQL Community Server from dev.mysql.com/downloads.

#### Steps to Install and Run:
**1. Clone the Repository:**
- git clone https://github.com/ado/BlogApp.git
- cd BlogApp

**2. Backend Setup:**
- Navigate to the backend directory in your project.
- Configure the database connection:
- Open src/main/resources/application.properties.
- Modify the database URL, username, and password as per your MySQL setup
  
- spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
- spring.datasource.username=your_mysql_username
- spring.datasource.password=your_mysql_password
- Save the changes.

**- Run the Spring Boot application:**
- Open a terminal in the backend directory.
- Build and run the application using Maven:

- ./mvnw spring-boot:run
- The backend server will start running at **http://localhost:8080**
  
  **3. Frontend Setup:**
- Navigate to the frontend directory in your project.
- **Install dependencies:**
 npm install
**Start the Angular development server:**
  ng serve


  The Angular development server will start running at **http://localhost:4200**.



  ### Accessing the Application:
  - Open your web browser and go to **http://localhost:4200** to access the blog application.
 
  ### Login Credentials:
  - Use the provided login credentials or register a new account based on your application logic.
