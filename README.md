# cheesecake-core

## Overview

The Internet Store Project is a modern e-commerce application built with microservices architecture. It leverages various technologies to provide a scalable and resilient solution for an online store.

## Technologies Used

- Java
- Kotlin
- Spring Boot
- Spring Cloud
- Spring Security (HIDDEN)
- Hibernate
- JDBC
- MongoDB
- PostgreSQL
- RabbitMQ (FUTURE)
- Docker
- Kubernetes

## Architecture

The project follows a microservices architecture, which allows for modular development and deployment of individual services. The key components of the architecture include:

- **API Gateway**: The API gateway serves as the entry point for all client requests and acts as a single point of contact for communication with the microservices. It handles request routing, load balancing, and provides security features such as authentication and authorization.

- **Microservices**:
  - **Gateway Service**: Responsible for managing authentication/authorization and request routing.
  - **User Service**: Responsible for managing user accounts.
  - **Product Service**: Handles product catalog management and related operations.
  - **Order Service**: Manages the ordering, fulfillment, and payment process.
  - **Notification Service (IN PROGRESS)**: Responsible for sending notifications.
  - **Configuration Service**: Manages microservices YAML configuration.

- **Data Storage**:
  - **MongoDB**: Used for storing product information.
  - **PostgreSQL**: Used for storing order-related, user data, and other structured information.

- **Communication**:
  - **REST API**: Used for communication between services.
  - **JDBC**: Used for accelerating processes with the PostgreSQL database using Java Database Connectivity (JDBC) API.
  - **Hibernate**: Used for mapping and working with the PostgreSQL database.
  - **Spring Cloud**: Provides various communication mechanisms, such as RESTful APIs and messaging, for inter-service communication.

- **Containerization and Orchestration**:
  - **Docker**: Used to package each microservice into a containerized image, ensuring consistency and portability across different environments.
  - **Kubernetes**: The project utilizes Kubernetes for container orchestration, allowing for efficient deployment, scaling, and management of the microservices' communication.

## Development and Deployment

- The project is developed using Java and Kotlin programming languages, utilizing the Spring Boot framework for rapid application development.
- Hibernate is used as the Object-Relational Mapping (ORM) tool for interacting with databases.
- The project is containerized using Docker, allowing for easy deployment and management of the microservices in different environments.
- Kubernetes is used for container orchestration, enabling seamless scaling and management of the microservices in a distributed environment.

## Conclusion

The Internet Store Project with microservices architecture provides a robust and scalable solution for building an e-commerce platform. Leveraging Java, Kotlin, Spring Boot, and various other technologies, it enables efficient development, deployment, and management of the different microservices involved in running an online store.

## Creators

- **De_Santos** - [GitHub](https://github.com/De-Santos)
- **1ulans1** - [GitHub](https://github.com/1ulans1)

