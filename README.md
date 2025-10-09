# Online Book Store – Backend Application

A **Spring Boot** backend project for managing books and orders with secure role-based access and RESTful APIs.

## Tech Stack
- **Java 21**
- **Spring Boot (MVC, JPA, Hibernate, Security)**
- **PostgreSQL**
- **Swagger** – API Documentation
- **Git/GitHub** – Version Control

## Features
- **Role-Based Access**
  - *Admin* – Add, update, delete books  
  - *User* – View and order books  
- **Authentication & Authorization**
  - Implemented using **Spring Security + JWT Token**  
  - Generates JWT token on login and verifies token for protected endpoints  
- **Order Management** – Automatically updates stock after each order  
- **Error Handling** – Custom responses for invalid or failed operations  
- **Swagger UI** – Easily test all APIs in browser  

## How It Works
1. **User Signup/Login** – Create account and authenticate  
2. **Admin Access** – Manage all book data using CRUD APIs  
3. **User Access** – Browse and order books  
4. **Stock Update** – Quantity reduces automatically after order placement  

## API Documentation
After running the project, open Swagger UI in your browser:
