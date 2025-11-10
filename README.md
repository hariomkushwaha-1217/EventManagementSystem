# EventManagementSystem

A full-featured **Event Management System** built using **Spring Boot, JPA (Hibernate), and PostgreSQL**.  
This application helps manage **Events, Venues, Organizers, Attendees, and Registrations** in a structured and efficient way.

---

## 🚀 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend Framework** | Spring Boot (v3+) |
| **ORM** | Spring Data JPA + Hibernate |
| **Database** | PostgreSQL |
| **Language** | Java 17+ |
| **Build Tool** | Maven |
| **API Format** | JSON (REST APIs) |

---

## 🏗️ Project Structure
com.eventmanagement
│
├── controller # REST Controllers
├── dto # Data Transfer Objects (if used)
├── entity # JPA Entity Classes
├── exception # Custom Exception Handling
├── repository # Spring Data JPA Repositories
├── service # Business Logic Layer
└── EventManagementApplication.java # Main Application


---

## 🧩 Entities Overview

| Entity | Description |
|--------|--------------|
| **Event** | Stores event title, description, date, and references to organizer & venue |
| **Venue** | Contains venue details like name, capacity, and location |
| **Organizer** | Holds organizer information such as name and contact |
| **Attendee** | Stores attendee details like name, email, and contact number |
| **Registration** | Links attendees to events with a registration date |

---

## 🧠 Features

✅ CRUD APIs for all entities  
✅ Register attendees for specific events  
✅ Fetch registrations by **Event ID** or **Attendee Name**  
✅ Fetch venues by **Location**  
✅ Automatically set current date for registration  
✅ Proper entity relationships using JPA  
✅ Exception handling for “No Records Found” cases  

---

## 📡 Sample API Endpoints

### 🎫 Venue
- `POST /venue/add` → Add a new venue  
- `GET /venue/getAll` → Get all venues  
- `GET /venue/location/{location}` → Get venues by location  

### 🧑‍💼 Organizer
- `POST /organizer/add` → Add a new organizer  
- `GET /organizer/getAll` → Get all organizers  

### 🎉 Event
- `POST /event/add` → Create a new event  
- `GET /event/getAll` → View all events  

### 👥 Attendee
- `POST /attendee/add` → Add a new attendee  
- `GET /attendee/getAll` → Get all attendees  

### 📝 Registration
- `POST /registration/add` → Register an attendee for an event  
- `GET /registration/event/{eventId}` → Get registrations by event ID  
- `GET /registration/attendee/{name}` → Get registrations by attendee name  

---

## 📘 Example JSON Inputs

### ➕ Add Venue
{
  "name": "Grand Convention Hall",
  "capacity": 500,
  "location": "Lucknow"
}

➕ Add Event
{
  "title": "Tech Conference 2025",
  "description": "Annual technology and innovation event",
  "event_date": "2025-12-20",
  "organizer": { "id": 1 },
  "venue": { "id": 1 }
}

➕ Add Attendee
{
  "name": "Ravi Kumar",
  "email": "ravi@example.com",
  "contact": 9876543210
}

➕ Add Attendee
{
  "registration_date": "2025-11-10",
  "attendee": { "id": 1 },
  "event": { "id": 1 }
}

⚙️ How to Run the Project

1.Clone the repository
git clone https://github.com/hariomkushwaha-1217/EventManagementSystem

2.Open the project in your IDE (Eclipse, IntelliJ IDEA, or VS Code).

3.Configure PostgreSQL in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/eventdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4.Run the application
mvn spring-boot:run

5.Test APIs using Postman at:
http://localhost:8080/


