# 🏥 eVaidhya Backend Application

Welcome to the **eVaidhya Backend Application** — a **Java-based Spring Boot** backend designed to streamline healthcare operations by managing **doctor appointments, patient records, and secure scheduling** with reliability and scalability.

This service acts as the **core backend** of the eVaidhya platform, exposing secure and extensible APIs for healthcare systems and frontend applications.

---

## 🌟 Project Vision

The **eVaidhya Backend Application** aims to:

- 🗓️ Simplify doctor appointment scheduling
- 🧑‍⚕️ Securely manage patient and doctor information
- 🔗 Provide robust REST APIs for frontend and third-party integrations
- 🚀 Enable scalable, cloud-ready healthcare solutions

---

## 🔑 Key Features

- 📅 **Doctor Appointment Scheduling**
  - Schedule, reschedule, and cancel appointments with conflict prevention

- 🧾 **Patient & Doctor Management**
  - Secure storage and retrieval of healthcare data

- 🔐 **Security & Authentication**
  - JWT-based authentication using Spring Security
  - Role-based access control for protected APIs

- 📧 **Email Notifications**
  - Appointment and system notifications via Spring Mail

- 🔌 **Integration Ready**
  - RESTful APIs for seamless frontend and external system integration

---

## ⚙️ System Architecture

### 🛠️ Technologies Used

- **Java (99.4%)**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Mail
- **JWT Authentication** (`jjwt`)
- **MySQL** for persistent healthcare records
- **Dependency Injection** using Spring Framework

### ☁️ Containerization & Cloud

- 🐳 Dockerized application for consistent deployments
- ☸️ Kubernetes YAML configurations for orchestration and scaling

---

## 📜 API Modules

### 🧩 Core Modules

1. **Patient Module**
   - Create, update, and retrieve patient information

2. **Appointment Module**
   - Schedule, reschedule, and cancel doctor appointments

3. **Authentication Module**
   - Secure login with JWT token-based authentication

---

## 📂 Repository Structure

```plaintext
eVaidhya-backend-application
├── src
│   ├── main
│   │   └── Backend Services Implementation
│   ├── test
│       └── Unit and Integration Tests
├── k8s
│   ├── app-deployment.yaml      # Backend application deployment
│   ├── mysql-deployment.yaml    # MySQL database deployment
├── Dockerfile                   # Docker container configuration
├── Jenkinsfile                  # CI/CD pipeline definition
├── pom.xml                      # Maven dependencies
└── README.md
````

---

## 🔧 Build & Run Instructions

### ✅ Prerequisites

* Java 17
* Maven
* Docker (optional for containerized execution)

### 🔨 Build the Application

```bash
mvn clean package
```

### 🐳 Run Using Docker

1. Build Docker image:

   ```bash
   docker build -t evaidhya-app .
   ```

2. Run the container:

   ```bash
   docker run -p 8080:8080 evaidhya-app
   ```

3. Access APIs:

   ```
   http://localhost:8080
   ```

---

## ☸️ Kubernetes Deployment

Kubernetes YAML configurations are available under the `k8s/` directory.

### 📦 Deploy to Kubernetes

```bash
kubectl apply -f k8s/mysql-deployment.yaml
kubectl apply -f k8s/app-deployment.yaml
```

### 📌 Components

* **Backend Deployment**

  * Spring Boot application container
  * Exposed service ports

* **MySQL Deployment**

  * Persistent database storage for healthcare data

---

## 🧪 Maven Dependencies

### 🔗 Core Dependencies

* Spring Boot Starter (Web, Security, JPA, Mail)
* JWT (`jjwt`)
* MySQL Connector
* Lombok for reduced boilerplate code

---

## 👤 Author & Contact

**Rohith Kodipaka**
🔗 GitHub: [https://github.com/rohithkodipaka](https://github.com/rohithkodipaka)

Contributions, issues, and feature requests are welcome.
⭐ If you find this project useful, please consider starring the repository.

---

## 🚀 Future Enhancements

* 🏥 Multi-tenant healthcare support
* ☁️ Cloud-native deployments
* 🔁 Full CI/CD automation using Jenkins
* 📊 Advanced monitoring and logging
