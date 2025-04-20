# XpressWave-SpringBoot-Backend
![Drawing tool](https://github.com/user-attachments/assets/895ea23d-6c6e-403d-86d1-f12560936c95)

# XpressWave Spring Boot Backend

XpressWave is a backend service built using **Spring Boot**, **Spring Data JPA**, and **Apache Derby**. It provides core functionality for user management and product inventory operations, designed to support a scalable e-commerce or classified platform.

---

## 🧩 Features

- ✅ **User Registration & Login**
- 🔐 Secure password storage using hashing
- 📦 **Product Management (CRUD)**: Add, update, delete, and fetch products
- 🔍 **Search by Categories** and Names
- 🔄 **Service Layer Architecture** for clean separation of concerns
- 🗂️ Uses **Spring Data JPA** repositories for DB interaction
- 🗃️ **Apache Derby DB** for quick and lightweight local setup

---

## 🗂️ Project Structure


---

[//]: # (## 📦 Endpoints Overview)

[//]: # ()
[//]: # (### User)

[//]: # (- `POST /user/signup` – Register a new user)

[//]: # (- `POST /user/login` – User login)

[//]: # ()
[//]: # (### Product)

[//]: # (- `GET /products` – Fetch all products)

[//]: # (- `GET /products/{category}` – Filter by category)

[//]: # (- `POST /products` – Add new product)

[//]: # (- `PUT /products/{id}` – Update product)

[//]: # (- `DELETE /products/{id}` – Delete product)

[//]: # ()
[//]: # (---)

## 🛠️ Tech Stack

| Layer        | Technology       |
|--------------|------------------|
| Backend      | Spring Boot      |
| Database     | Apache Derby     |
| ORM          | Spring Data JDBC |
| Build Tool   | Maven            |
| Language     | Java             |

---

## 🧪 Setup Instructions

1. **Clone the Repository**  
   `git clone https://github.com/AnuroopFarkya11/XpressWave-SpringBoot-Backend.git`

2. **Import into IntelliJ/Eclipse**

3. **Run SQL scripts** in `sqls/` to set up schema (optional – JPA auto-creation may work)

4. **Run the Application**  


5. **Access API at**: `http://localhost:8080/`

---

## 📎 Notes

- The project uses an embedded **Derby DB**, ideal for testing.
- Passwords are hashed for user security.
- RESTful conventions are followed for endpoint design.

---

## 🤝 Contribution

Contributions, issues, and feature requests are welcome!  
Feel free to fork the repo and submit pull requests.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👤 Author

- [Anuroop Farkya](https://github.com/AnuroopFarkya11)
