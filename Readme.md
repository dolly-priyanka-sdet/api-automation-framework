# 🚀 API Automation Framework (RestAssured)
 
## 📌 Overview
This project is an API automation framework built using RestAssured and TestNG. 
It demonstrates CRUD operations, API chaining, and data-driven testing.
 
---
 
## 🛠 Tech Stack
- Java
- RestAssured
- TestNG
- Maven
 
---
 
## ✅ Features
- CRUD API Testing (GET, POST, PUT, DELETE)
- Dynamic data handling (ID extraction using JsonPath)
- API chaining across requests
- Data-driven testing using TestNG DataProvider
- Clean and structured automation framework
 
---
 
## 📁 Project Structure
api-automation-framework/ │ ├── base/              # Common setup (BaseTest) ├── tests/             # Test classes │   └── CrudOperations.java │ ├── pom.xml └── Readme.md
 
Plain Text
---
## ▶️ How to Run
```bash
mvn test
✅ Test Scenarios Covered
Get all users
Get user by ID
Create user
Update user
Delete user
Delete all users
🔥 Key Highlights
API chaining (dynamic ID usage)
JSON response parsing using JsonPath
Reusable and scalable test design
👩‍💻 Author
Priyanka — SDET Automation Engineer