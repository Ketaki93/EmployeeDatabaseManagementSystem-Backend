# Vmware Assignment - Employee Management System

## Technology
Java 8
Spring Boot 2.2.1 (with Spring Web MVC, Spring Data JPA)
MySQL
Maven 3.6.1
STS tool
Visual Studio Code

## Setup
1. Install Node and npm
2. Install the Angular CLI for Angular 8
3. Install Java 8
4. Install MySQL 5.5
5. Clone project from github url 'https://github.com/Ketaki93/EmployeeDatabaseManagementSystem-Backend.git'.
6. Import springboot project 'EmployeeDatabaseManagementSystem' into STS.
7. Create database 'employeedb' in mysql database;
8. Right click and Execute the main class 'EmployeeDatabaseManagementSystemApplication.java' as a Spring boot application. The application will start on http://localhost:8080/
9. Clone Angular project 'EmployeeManagementSystem' and open in Visual Studio Code.
10. Open terminal in Visual Studio Code and execute 'npm install' to install all dependencies.
11. Execute 'ng serve' command to start the application.

You can access the application on url **'http://localhost:4200/'**

#API Implementation

1. Get all Employees - **GET /employees**
2. Get Employee By Id - **GET /employees/{empid}**
3. Add Employee - **POST /employees**
4. Edit Employee - **PUT /employees/{empid}**
5. Get Managers for an Employee - **GET /employees/{empid}/managers**
6. Get Reportees for an Employee - **GET /employees/{empid}/reportees**
