# Super Blog · blog-server (Backend)

> A blog backend service built with Spring Boot 2.7 + MyBatis-Plus + MySQL + Spring Security (JWT).
>
> [中文 README →](./README.md)

## 🔗 Repositories

| Side | Repository |
|------|------------|
| **Backend blog-server (this repo)** | https://github.com/vfaner/blog-server |
| **Frontend blog-client** | https://github.com/vfaner/blog-client |

If this project helps you, please give it a ⭐️ **Star** — that's the best way to support ongoing open-source work!

---

## ✨ Screenshots

### Home page
![Home](./images/bk_index.png)

### Front-end pop-up login
![Home login popup](./images/bk_index_login.png)

### Standalone login page (redirected to when hitting `/admin/*` unauthenticated)
![Login](./images/bk_login.png)

### Category article list
![Category](./images/bk_cate.png)

### Article detail
![Article detail](./images/bk_detail.png)

### Admin dashboard
![Admin dashboard](./images/bk_admin.png)

### Admin article editor
![Article editor](./images/bk_admin_arth.png)

### Admin comment management
![Comment management](./images/bk_admin_comment.png)

---

## 🚀 Tech Stack

- **JDK 17**
- **Spring Boot 2.7.18**
- **Spring Security + JWT (jjwt 0.12.6)**
- **MyBatis-Plus 3.5.5** — single-table CRUD
- **MyBatis XML Mapper** — multi-table joins (easy to hand-optimize SQL)
- **MySQL 8.x**
- **SpringDoc OpenAPI 1.8.0** (Swagger UI)
- **Lombok**
- **BCryptPasswordEncoder**

## 📦 Features

- Login, JWT auth, role/menu-based authorization
- Full admin for articles, categories, tags, comments, links, system logs
- Dynamic home cards (any number of categories/tags with custom article counts)
- Article view count & like count persisted in DB
- Nested comment tree with automatic `articleId` inheritance on replies
- Optional per-article downloadable attachment
- Live dashboard statistics
- Auto-migration of missing columns and BCrypt-encoded admin password on first boot

## 🏃 Quick Start

### Requirements
- JDK 17+
- MySQL 8.x
- Maven 3.6+

### 1. Create database

```sql
CREATE DATABASE blog DEFAULT CHARACTER SET utf8mb4;
```

Schema & seed data (`db/schema.sql` + `db/data.sql`) are executed automatically on startup.

### 2. Configure

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: your-password
```

### 3. Run

```bash
mvn spring-boot:run
```

After startup:
- API base: http://localhost:8090/rgh/api
- Swagger UI: http://localhost:8090/swagger-ui/index.html

### 4. Default account

| Username | Password |
|----------|----------|
| admin    | 123456   |

> `DataInitializer` re-encodes the admin password with BCrypt on first boot.

## 🗂 Project Layout

```
blog-server/
├── src/main/java/com/rgh
│   ├── conf/           # Security / CORS / Swagger config
│   ├── controller/     # REST controllers
│   ├── entity/         # JPA/MyBatis entities
│   ├── mapper/         # MyBatis mappers
│   ├── service/        # Business services
│   ├── vo/             # View objects
│   └── util/           # Utilities
├── src/main/resources
│   ├── mapper/         # XML mappers (joins)
│   ├── db/schema.sql   # DDL
│   ├── db/data.sql     # Seed data
│   └── application.yml
└── pom.xml
```

## 🧩 Front-end Integration

Frontend project: https://github.com/vfaner/blog-client

The frontend defaults to port `8080` (Vite auto-falls back to 8081/8082/… when the port is in use). `SecurityConfig` already whitelists `http://localhost:*` and `http://127.0.0.1:*` for CORS, so any local port works out of the box.

---

## ❤️ Sponsor & Star

If this project helps you, feel free to buy me a coffee ☕️:

![Sponsor QR codes](./images/ds.png)

> And please don't forget to smash that ⭐️ **Star** button at the top-right!

## 📄 License

MIT
