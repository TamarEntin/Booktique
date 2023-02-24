# Booktique: Library Management System

Booktique is a library management system designed to help library employees manage the library efficiently. It is built with a layered architecture and a repository pattern to ensure a robust and extensible codebase.


## Features
- Easy Access to Information: With Booktique, library employees can easily access information about books, authors, borrowers, and transactions, allowing them to respond promptly to any queries from library users.

- Manage Transactions: Booktique makes it easy to manage transactions such as borrowing and returning books. The system automatically updates book availability and keeps track of due dates.

- User Management: Booktique provides user management features that allow library employees to add new users and update user details. It also keeps track of user borrowing history.

- Reports: Booktique generates reports that provide insights into the library's performance. Reports can be generated for different time periods and include statistics on borrowing, returns, overdue books, and more.

## Architecture
Booktique is built with a layered architecture and a repository pattern. The layered architecture ensures that each layer has a clear responsibility, reducing coupling and making it easier to swap out individual components. The repository pattern ensures that data is stored in a consistent manner, with each repository responsible for a specific entity.

## Design Patterns
Booktique also uses several design patterns to improve its architecture and maintainability:

- Command Pattern: The service layer of Booktique is implemented using the Command pattern, which allows the user interface to invoke the services by calling one method which is executed with different parameters (different requests).

- Singleton Pattern: To prevent the client from creating more than one instance of the service command and to offer him the possibility to call this single instance wherever and whenever he wants, Booktique uses the Singleton pattern.

- Dependency Injection: Booktique uses Dependency Injection to achieve the architecture of the layers and to achieve a certain separation between the layers and our objects. This is done using an interface to describe each object, which is then injected by an upper layer initialization class (a container of other classes).

