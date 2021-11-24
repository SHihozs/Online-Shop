# Online-Shop

## Authors
- [Devicez](https://github.com/Devicez)
- [SHihozs](https://github.com/SHihozs)

## FILE IN /SRC FOLDER
- OnlineShop.java

## CLASS IN /OUT FOLDER
- customers.class
- OnlineShop.class
- postage.class
- product.class
- typeException.class

 ## REQUIREMENTS
# 1. Copy the following data to file products.txt. 
      - Each line contains the product name, price, and weight
      # Implement class Product, with at least the following
         - Variables name, price, weight: values are read from each line of products.txt
         - Variables totalSalesInCash and totalSalesInUnits: keep the total sales of each product
         - Add more variables and methods as needed 
  
# 2. Copy the following data to file postages.txt. 
      - Each line contains the type (“E” for EMS, “R” for registered), min weight (exclusive), max weight (inclusive), and rate
      # Implement class Postage, with at least the following
         - Variables type, minWeight, maxWeight, rate: values are read from each line of postages.txt
         - Add more variables and methods as needed
         
# 3. Implement class OnlineShop as the main class. When the program starts
      - Read data from products.txt into array or ArrayList of Products
      - Read data from postages.txt into array or ArrayList of Postages
      - Read data from customers.txt into array or ArrayList of Customers

# 4. The program must be able to handle the following errors/exceptions
     - Missing files
     - File customers.txt may contain input errors. You may use 2 separate files, one with clean input and
another with errors.

# 5. In summary, your program must have
     - Files: products.txt, postages.txt, customers.txt with your data (10 lines)
     - Classes: Product, Postage, Customer, OnlineShop (main class)
