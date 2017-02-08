# BookStore
Book store project is with a REST Services in Spring Boot

### STEP: 1 ######

To run this project
```bash
mvn spring-boot:run
```
this will build and execute Junit test cases

### STEP: 2 ######

Testing services one by one

^ you can use postman or any client for webservices testing or curl from commandline.

1) GET service to fetch all books available 
 	
     http://localhost:8088/books/
 	
2) Get service to fetch a list of books by given search string

     http://localhost:8088/books/Spend Money 	
 	
3) Post service for add book to basket

     http://localhost:8088/books/add?quantity=14 
  
  body :   
   {
    "id": 100000001,
    "title": "Mastering åäö",
    "author": "Average Swede",
    "price": 762
  	}

4) Post service for buy some books


	  http://localhost:8088/books/buy 
 								 
Body :

[ {
    "id": 100000002,
    "title": "How To Spend Money",
    "author": "Rich Bloke",
    "price": 1000000
  },
  {
    "id": 100000003,
    "title": "Generic Title",
    "author": "First Author",
    "price": 185.5
  },
  {
    "id": 100000004,
    "title": "Generic Title",
    "author": "Second Author",
    "price": 1748
  }
  ]


