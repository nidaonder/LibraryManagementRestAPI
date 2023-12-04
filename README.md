# LibraryManagementRestAPI
It is a Library Management System Rest API that can perform CRUD operations.

## Technologies

* Java
* Spring Boot
* PostgreSQL

You can view the application by navigating to http://localhost:8080 in your browser.

## Entities

* Author
* Book
* BookBorrowing
* Category
* Publisher

## Endpoints

| Endpoint           | HTTP Code      | Description                                      |
| ------------------ | -------------- | :----------------------------------------------: |
| "/v1/authors"      | POST           |  Add new Author.                                 |
| "/v1/authors"      | GET            |  All authors are listed.                         |
| "/v1/authors/{id}  | GET            |  Authors belonging to a specific id are listed.  |
| "/v1/authors"      | PUT            |  The author of a specific id is updated.         |
| "/v1/authors"      | DELETE         |  The author of a specific id is deleted.         |

For other entities, you can browse the Controller classes.
