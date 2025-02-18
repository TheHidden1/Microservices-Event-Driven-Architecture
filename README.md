# Microservices Architecture with Spring Boot and Kafka

## Overview
This project is a microservices-based backend application that handles order processing using Spring Boot and Apache Kafka. It consists of three backend microservices (Order Service, Email Service, Stock Service).

## Prerequisites
Docker and Docker Compose installed

- Java 21+ installed

- Apache Kafka (running in Docker)

- Maven installed

## Running the application

### Step 1
This project uses Conduktor's Kafka Stack for Docker Compose. Clone the repository and start the [Kafka stack](https://github.com/conduktor/kafka-stack-docker-compose)

Run the following commands to start Kafka and Zookeeper:
```shell
git clone https://github.com/conduktor/kafka-stack-docker-compose.git
cd kafka-stack-docker-compose
docker compose -f zk-single-kafka-multiple.yml up
```
### Step 2 Build the Microservices
Run the following command in each service directory to build the project:
```shell
mvn clean install
```
### Step 3: Start the Microservices

Each service is a separate Spring Boot application. Run them in the following order:

#### Start Order Service (Producer)
```shell
cd order-service
./mvnw spring-boot:run
```
#### Start Email Service (Consumer)
```shell
cd email-service
./mvnw spring-boot:run
```
#### Start Stock Service (Consumer)
```shell
cd stock-service
./mvnw spring-boot:run
```
### Step 3 Create API call
#### Create an Order (POST request to Order Service)
```shell
POST /api/order
Content-Type: application/json
{
    "name": "OrderName",
    "quantity": 0,
    "price": 1
}
```
