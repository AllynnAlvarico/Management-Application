# Management-Application

A collaborative learning project by Allynn Alvarico and Anson Ling Guang Cheng.

## Overview

This repository is an **attempt to build a modern banking application** using a full-stack approach that combines:

- [Angular](https://angular.io/) (frontend)
- [Tailwind CSS](https://tailwindcss.com/) (styling)
- [Spring Boot](https://spring.io/projects/spring-boot) (backend, Java)

The primary goal is to **demonstrate and integrate skills learned over the summer** by designing and implementing the essential features of a bank application—including authentication, account management, and basic transactions.


## Features

- User authentication (login/signup)
- Responsive dashboard and account views
- Simple money transfer and balance check
- Modern UI styling with Tailwind CSS
- RESTful Spring Boot backend

> **Note:** This project is a learning exercise and not intended for real-world banking use.

## Tech Stack

| Layer    | Technology               | Description                           |
|----------|--------------------------|-------------------------------------|
| Frontend | Angular                  | TypeScript SPA framework            |
| Styling  | Tailwind CSS             | Modern utility CSS                  |
| Backend  | Spring Boot (Java)       | REST API, business logic            |
| Database | MySQL / MariaDB (hosted on Raspberry Pi) | Remote database server for persistence |

## Database Details

- **Type:** MySQL, powered by MariaDB
- **Host:** Dedicated Raspberry Pi server (set up during the summer)
- **Usage:** Spring Boot backend connects to this remote database for all data storage and retrieval.

Be sure to configure your Spring Boot application's `application.properties` or `application.yml` to connect to the correct Raspberry Pi server address and credentials.

## Getting Started

### Prerequisites

- Node.js (for Angular)
- Java (JDK 17+ recommended for Spring Boot)
- [Maven](https://maven.apache.org/) (for backend)
- Any SQL database supported by Spring Boot (MySQL/MariaDB in this case)

### Setup

#### Frontend (Angular + Tailwind)

Navigate to `http://localhost:4200` in your browser.

#### Backend (Spring Boot)

API will be available at `http://localhost:8080`.


## Collaboration

This project is built in collaboration with:

- **Allynn Alvarico**
- **Anson Ling Guang Cheng**

We are each practicing both new and familiar technologies, stitching our strengths together for this summer project.

## Learning Outcomes

- Applying Angular for enterprise-level web frontends
- Designing and consuming REST APIs in Spring Boot
- Modern CSS and responsive design with Tailwind
- Team collaboration and version control workflows

## License

This project is for educational use only.

---
