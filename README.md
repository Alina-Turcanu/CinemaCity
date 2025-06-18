# 🎬 CinemaCity – Cinema Booking Backend Application

CinemaCity is a backend application that simulates the management of a real-world cinema system.
It allows administrators to create cinema halls, manage movie screenings, and track ticket bookings, while users can browse available movies and reserve seats.

## 🚀 Tech Stack

- Java 17  
- Spring Boot  
- Spring Security + JWT  
- MySQL  
- REST API  
- JavaMailSender  
- External Movie API integration (e.g. TMDB)

---

## ✨ Features

### 👤 Authentication & Roles
- JWT-based authentication
- Role-based access (ADMIN / USER)

### 🎞 Movie Management
- Add movies with metadata fetched from an external movie API
- Each movie can be scheduled on multiple dates
- Admins can create screenings only if no overlaps exist in the same hall

### 🏟 Hall & Seat System
- Admin can define cinema halls with rows and columns
- Ability to set extra pricing for specific rows (e.g. front or last seats)

### 🎟 Ticket Booking  
- Users have the ability to select and reserve available seats for movies scheduled at specific times  
- The system performs real-time validation to ensure seat availability and prevent double bookings during the reservation process


### 📩 Email Notifications
- Confirmation email sent to the user after booking
- Admin is notified via email upon every ticket purchase

---

## 📌 Future Improvements
- Front-end integration (React or Angular)
- Dashboard for users and admins
- Filtering by genre, rating, date

---

## 🛠 How to Run
1. Clone the repository  
2. Configure application.properties with your DB & email credentials  
3. Run the application with `mvn spring-boot:run`  
4. Use Postman or Swagger to test endpoints

---

## 🤝 Contact

Built by a passionate backend developer transitioning into IT.  
Looking for an entry-level opportunity in Java development.  
Feel free to connect with me on [LinkedIn](https://www.linkedin.com/in/alina-mihaela-turcanu-206a4034a/).

