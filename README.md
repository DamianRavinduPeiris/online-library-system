# Online Library System

## Introduction

The goal of this project is to build an online library system that allows users to sign up, borrow books, and track their borrowing history. The system will store user details, book information, and borrowing records, and provide efficient performance for accessing resources as the dataset grows.

### Entities and Relationships

- **User**: Stores user information such as id, name, email, and account creation date.
  - Each `User` can have multiple borrow records, stored in the `BorrowRecord` entity.
  
- **Book**: Stores information about books such as id, title, author, published year, and the number of available copies.
  - Each `Book` can be borrowed multiple times, with records stored in the `BorrowRecord` entity.

- **BorrowRecord**: Stores information about borrowing transactions, including the user, book, borrowing time, and return time.
  - Each `BorrowRecord` is linked to one `User` and one `Book`.

### Relationships
- A `User` can have many `BorrowRecord` entries (One-to-Many relationship).
- A `Book` can be borrowed many times, which is also represented by the `BorrowRecord` (One-to-Many relationship).
- The `BorrowRecord` links both `User` and `Book`, and stores the borrow and return timestamps.

## Features

- **User Registration**: Users can create a profile by providing basic information.
- **Book Exploration**: After registration, users can browse the book collection and view available copies for borrowing.
- **Book Search**: Users can search books by specific criteria such as author or published year.
- **Borrow/Return Books**: Users can borrow and return available books.
- **Borrow History**: Users can view their past borrowed books.

# Security Configuration

The application uses **Spring Security** to manage authentication and authorization for its APIs. Below is a summary of the key features of the security setup:

### 1. CSRF Protection Disabled:
For simplicity, **CSRF protection** has been disabled in this configuration.

### 2. Endpoint Access Control:
- **Public Access**: Endpoints under `/api/v1/books/**` and `/api/v1/library/**` are publicly accessible without authentication.
- **Secured Access**: Endpoints under `/api/v1/users/**` require authentication. (Refer below on how to access using Postman Basic Auth.The username is `user` & the password is `password`)

![image](https://github.com/user-attachments/assets/ef62fd6a-5f95-4172-9501-ff52882e53de)

- If still getting **un-authorized** error make sure to check and add the **content-type** header to **application/json** in Postman.
  ![image](https://github.com/user-attachments/assets/07feb8ab-ca2f-42e2-9317-7074afcc7101)

### 3. Authentication Method:
- **Basic authentication** is used for securing APIs.

### 4. User Roles:
Predefined **in-memory users** with roles:
- **User**: 
  - Username: `user`
  - Password: `password`
  - Role: `USER`
- **Admin**: 
  - Username: `admin`
  - Password: `admin`
  - Role: `ADMIN`

---

This configuration ensures a separation of concerns and **role-based access control** for APIs, catering to both **public** and **secured** endpoints.


## API Endpoints (Refer **`postman_collection.json`** in the project root.)

### User Controller

- **POST /api/v1/users/register**
  - Registers a new user with the provided details.
  - **Request body**: `UserDTO`
  - **Response**: Success or failure response.

- **GET /api/v1/users/searchUser/{userId}**
  - Fetches user details by user ID.
  - **Path variable**: `userId`
  - **Response**: User details.

### Book Controller

- **POST /api/v1/books/register**
  - Registers a new book in the system.
  - **Request body**: `BookDTO`
  - **Response**: Success or failure response.

- **GET /api/v1/books/searchBook/{bookId}**
  - Fetches book details by book ID.
  - **Path variable**: `bookId`
  - **Response**: Book details.

- **GET /api/v1/books/getAllBooks**
  - Fetches all books in the library.
  - **Response**: List of books.

- **GET /api/v1/books/getAvailableBooks**
  - Fetches available books that can be borrowed.
  - **Response**: List of available books.

- **GET /api/v1/books/searchBook/author/{authorName}**
  - Searches books by author name.
  - **Path variable**: `authorName`
  - **Response**: List of books by author.

- **GET /api/v1/books/searchBook/year/{publishedYear}**
  - Searches books by published year.
  - **Path variable**: `publishedYear`
  - **Response**: List of books published in that year.

### Book Borrow Controller

- **POST /api/v1/library/borrow/{userId}/{bookId}**
  - Allows a user to borrow a book.
  - **Path variables**: `userId`, `bookId`
  - **Response**: Success or failure response.

- **POST /api/v1//library/return/{userId}/{bookId}**
  - Allows a user to return a borrowed book.
  - **Path variables**: `userId`, `bookId`
  - **Response**: Success or failure response.

- **GET /api/v1/library/borrow/history/{userId}**
  - Fetches the borrowing history of a user.
  - **Path variable**: `userId`
  - **Response**: List of borrowed books.

## Additional Features

- **Borrow and Return Books**: Users can borrow and return available books through the `/borrow` and `/return` endpoints.
- **View Borrowing History**: Users can see their past borrowed books with the `/borrow/history` endpoint.

## How to Run

1. Clone the repository to your local machine.
2. Build and run the Spring Boot application.
3. Use the endpoints to interact with the online library system.

## Conclusion

This project provides a functional and efficient online library system with a simple user interface to interact with books and borrowing records. The system is built with scalability and performance in mind, incorporating relational database design for efficient management of users, books, and borrowing records.
