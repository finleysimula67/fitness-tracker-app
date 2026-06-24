# 🏋️ Fitness Tracker App

A backend REST API that lets users **sign up, log workouts, and receive personalized fitness recommendations** — all secured with JWT authentication.

---

## What It Does

- **Sign up & log in** — create a secure account and receive a login token
- **Track workouts** — log fitness activities using structured categories
- **View your activity history** — fetch all workouts tied to your account
- **Get recommendations** — generate and retrieve personalized fitness tips based on your user profile or specific activities

---

## Tech Used

| Technology | Role |
|---|---|
| Java 25 + Spring Boot 4.0.6 | Core backend framework |
| PostgreSQL | Database |
| Spring Security + JWT (JJWT 0.13.0) | Secure login & token-based auth |
| Spring Data JPA | Database communication |
| Lombok | Cleaner code, less boilerplate |
| Maven | Build & dependency management |

---

## Run It Locally

**You need:** Java 25, Maven, PostgreSQL

```bash
git clone https://github.com/finleysimula67/fitness-tracker-app.git
cd fitness-tracker-app
```

Add your database and JWT details to `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fitness_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_secret_key
```

Then start the app:

```bash
mvn spring-boot:run
```

API is live at `http://localhost:8080` ✅

---

## API Endpoints

### 🔓 Auth — no token needed

| What | Method | Endpoint |
|---|---|---|
| Register a new account | `POST` | `/api/auth/register` |
| Log in & get a token | `POST` | `/api/auth/login` |

### 🏃 Activities — token required

| What | Method | Endpoint |
|---  |---  |---  |
| Log a new activity   | `POST`     | `/api/activities`   |
| Get all your activities   | `GET`   | `/api/activities`   |

> Pass your user ID in the request header: `X-User_ID: your-user-id`

### 💡 Recommendations — token required

| What   | Method | Endpoint |
|---  |---|  ---|
| Generate a recommendation   | `POST` |   `/api/recommendation/generate` |
| Get recommendations by user   | `GET` |   `/api/recommendation/user/{userId}` |
| Get recommendations by activity   | `GET`|   `/api/recommendation/activity/{activityId}` |

> After logging in, include your token in every request: `Authorization: Bearer <your-token>`

---

## Status

✅ Project complete — all features implemented and working.

---

## License

© 2026 Nabin Oli. All rights reserved.
