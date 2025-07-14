# 📝 Journal Management System

A  **Spring Boot + MongoDB** based Journal Management System designed for managing user journals with role-based authentication.

---

## 🚀 Features

- User Registration & Login
- Role-based Access (USER / ADMIN)
- Admin-only endpoints for management
- Journal Entry Creation, Viewing
- Spring Security with JWT
- MongoDB Integration

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- MongoDB
- JWT Authentication
- Maven

---

## 📁 Project Structure
```
src/
├── main/
│   ├── java/com/satish/journal/
│   │   ├── controller/            # API Controllers
│   │   ├── entity/                # Domain Models
│   │   ├── repository/            # MongoDB Repositories
│   │   ├── service/               # Business Logic
│   │   └── config/                # Security Configuration
│   └── resources/
│       └── application.properties
```

---


---

## 🔐 Roles and Permissions

| Path           | Access             |
|----------------|--------------------|
| `/user/**`     | Authenticated User |
| `/admin/**`    | Admin Only         |
| `/journal/**`  | Authenticated User |
| All others     | Public             |

---

---

## ▶️ How to Run


## 1. Clone the repo
git clone https://github.com/satishkumar1999/journal-management-system.git

## 2. Go into project folder
cd journal-management-system

## 3. Run with Maven
./mvnw spring-boot:run

---

## ⚙️ Configuration
Set your MongoDB connection and server details in:



spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@cluster0.mu79jw4.mongodb.net/?retryWrites=true&w=majority
      database: journaldb
      auto-index-creation: true

server:
  port: 8081
  servlet:
    context-path: /journal


