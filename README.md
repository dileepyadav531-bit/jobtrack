# JobTrack — Job Application Management System

JobTrack is a full-stack web application that allows users to explore job opportunities, apply for jobs, and track their application status. Administrators can manage job postings and applications through a dedicated admin interface.

The project was developed using React on the frontend and Spring Boot on the backend, with JWT-based authentication, role-based authorization, JPA/Hibernate, and MySQL.

## Features

### User Features

* User registration
* User login
* JWT-based authentication
* View available jobs
* Search jobs
* View job details
* Apply for jobs
* Prevent duplicate applications
* View personal applications
* Track application status
* View user profile

### Admin Features

* Admin login
* Add new job postings
* Update existing jobs
* Deactivate job postings
* View all applications
* View applicant information
* View job information associated with applications
* Update application status
* Role-based access control

## Technology Stack

### Frontend

* React
* JavaScript
* React Router
* Fetch API
* HTML
* CSS
* Vite

### Backend

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* REST APIs
* Bean Validation
* Global Exception Handling
* Maven

### Database

* MySQL

## Application Architecture

```text
React Frontend
       ↓
REST API
       ↓
Spring Boot
       ↓
Controller
       ↓
Service
       ↓
Repository
       ↓
JPA / Hibernate
       ↓
MySQL Database
```

## Authentication Flow

1. User registers with name, email, password, and phone number.
2. Password is encrypted using BCrypt before storing it in the database.
3. User logs in using email and password.
4. Backend validates the credentials.
5. A JWT token is generated after successful authentication.
6. The frontend stores the token and sends it with protected API requests.
7. Spring Security validates the JWT and identifies the authenticated user.
8. Role-based authorization controls access to user and admin operations.

## Application Workflow

### User Workflow

```text
Register
   ↓
Login
   ↓
View Available Jobs
   ↓
Search / View Job Details
   ↓
Apply for Job
   ↓
View My Applications
   ↓
Track Application Status
```

### Admin Workflow

```text
Admin Login
   ↓
Manage Jobs
   ↓
Add / Update / Deactivate Jobs
   ↓
View Applications
   ↓
View Applicant & Job Information
   ↓
Update Application Status
```

## Security

The application implements:

* JWT-based authentication
* Spring Security
* BCrypt password encryption
* Role-based authorization
* Protected REST APIs
* Duplicate application prevention
* Authentication and authorization checks
* Validation and exception handling

## Exception Handling

The backend uses global exception handling with `@RestControllerAdvice` to provide meaningful responses for situations such as:

* Duplicate email registration
* Invalid login credentials
* Duplicate job applications
* Inactive jobs
* Invalid application status
* Invalid registration data

## Project Structure

```text
JobTrack-GitHub
│
├── backend
│   └── jobtrack
│       ├── src
│       │   ├── main
│       │   │   ├── java
│       │   │   └── resources
│       │   └── test
│       ├── pom.xml
│       └── mvnw
│
├── frontend
│   └── jobtrack-frontebd
│       ├── src
│       ├── public
│       ├── package.json
│       └── vite.config.js
│
└── .gitignore
```

## Database

The application uses MySQL to store:

* Users
* Job postings
* Job applications
* Application status

The actual local `application.properties` file containing database credentials is excluded from GitHub for security.

A safe `application.properties.example` file is included as a reference.

## Future Scope

Possible future improvements include:

* Email notifications
* Resume upload
* Password reset
* Cloud deployment

## Author

**Dileep Kumar**

Java Full Stack Developer
