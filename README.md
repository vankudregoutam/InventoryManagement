
# 📦 Inventory Management System – Microservices Architecture

A **complete microservices-based Inventory Management System** built using **Spring Boot and Spring Cloud**, demonstrating real-world backend engineering practices such as **service discovery, API gateway routing, inter-service communication, and domain-driven service separation**.

This project is structured and documented at a level expected from a **3–5 years experienced Java backend developer** and is suitable for **interviews, GitHub showcase, and resume linking**.

---

## 🧱 High-Level Architecture

```
Client
   |
   v
API Gateway
   |
   v
-------------------------------------------------
| Order Service | Product Service | Inventory |
| Payment Service                 | Service   |
-------------------------------------------------
           |
           v
      Eureka Server
```

### Why Microservices?
- Independent deployment & scaling
- Clear domain ownership
- Fault isolation
- Cloud-native & enterprise-ready design

---

## 🛠️ Tech Stack (Common Across Services)

- Java
- Spring Boot
- Spring Cloud
- Spring MVC (REST APIs)
- Spring Data JPA
- Eureka (Service Discovery)
- Spring Cloud Gateway
- Maven
- Lombok

---

## 🔀 API Gateway Service

### Role
The **API Gateway** acts as the **single entry point** for all client requests.

### Responsibilities
- Routes incoming requests to downstream services
- Uses Eureka for dynamic service resolution
- Abstracts internal service structure from clients
- Foundation for security, logging, and rate limiting

### Flow
```
Client → API Gateway → Target Microservice
```

---

## 🧭 Eureka Service (Service Registry)

### Role
The **Eureka Server** enables **service discovery**, allowing services to find and communicate with each other dynamically.

### Responsibilities
- Registers all microservices
- Maintains service health via heartbeats
- Eliminates hardcoded service URLs

### Benefit
- Improves scalability and fault tolerance
- Essential for microservices environments

---

## 📦 Product Service

### Role
The **Product Service** manages all product-related data and acts as the **source of truth** for product information.

### Responsibilities
- Create and manage products
- Expose product APIs
- Serve product data to Inventory & Order services

### Sample APIs
```
POST /api/products
GET  /api/products/{id}
```

---

## 🏬 Inventory Service

### Role
The **Inventory Service** manages stock levels and validates product availability.

### Responsibilities
- Maintain inventory quantities
- Increase / decrease stock
- Prevent overselling
- Respond to order stock checks

### Key Backend Concept
- Ensures **data consistency**
- Acts as a transactional boundary for stock updates

---

## 🛒 Order Service

### Role
The **Order Service** orchestrates the complete order lifecycle.

### Responsibilities
- Create orders
- Validate inventory
- Trigger payment processing
- Maintain order status

### Flow
```
Client → Order Service
           ↓
     Inventory Service
           ↓
      Payment Service
```

### Key Backend Concept
- Orchestration-based service communication
- Handles partial failures and state transitions

---

## 💳 Payment Service

### Role
The **Payment Service** handles payment processing independently.

### Responsibilities
- Process payments
- Maintain transaction records
- Update payment status
- Communicate results back to Order Service

### Design Benefit
- Isolated financial logic
- Easy integration with external payment gateways

---

## 📂 Project Structure (Logical)

```
MicroService Task
├── Api_Gateway
├── Eureka_Service
├── Product_Service
├── Inventory_Service
├── Order_Service
└── Payment_Service
```

---

## ⚙️ How to Run the System

### Prerequisites
- Java 17+
- Maven
- IDE (IntelliJ / Eclipse)
- Postman (for testing)

### Startup Order
1. Eureka Service
2. API Gateway
3. Product Service
4. Inventory Service
5. Order Service
6. Payment Service

### Run Command (Each Service)
```bash
mvn clean install
mvn spring-boot:run
```

---

## 📈 Future Enhancements (Interview-Ready)

- JWT-based authentication at API Gateway
- Saga pattern for order-payment consistency
- Circuit breakers (Resilience4j)
- Event-driven communication (Kafka)
- Distributed tracing (Zipkin)
- Docker & Kubernetes deployment

---

## ✅ Why This Project Stands Out

- Real microservices separation
- Proper service discovery & routing
- Clear backend ownership per service
- Scalable and production-aligned architecture
- Easy to explain end-to-end in interviews

---

## 👨‍💻 Author

**Java Backend Developer**  
Spring Boot | Microservices | REST APIs | Clean Architecture

---

⭐ If you find this project useful, consider giving it a star on GitHub!
