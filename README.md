# ?? Day 24 – JWT Logout & Token Blacklisting in Spring Boot

## ?? Goal
Implement **secure logout** in a JWT-based Spring Boot application using **token blacklisting**.

JWTs are stateless — once issued, they remain valid until they expire.  
To "invalidate" a token before expiry (e.g., during logout), we maintain a **blacklist** of invalid tokens and block them during authentication.

---

## ?? Project Setup

### ?? Tech Stack
- Spring Boot 3.x  
- Spring Security  
- Spring Data JPA (H2 in-memory DB)  
- JJWT (Java JWT Library)


---

## ?? How It Works

1?? **Login** – Generates JWT token for valid user  
2?? **Access Protected APIs** – Token used in `Authorization: Bearer <token>` header  
3?? **Logout** – Token added to blacklist  
4?? **Subsequent Requests** – Blacklisted tokens rejected by `JwtAuthFilter`

---

## ?? API Endpoints

### ?? `POST /auth/login`
**Request:**
```json
{
  "username": "john",
  "password": "password"
}

## Response
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

