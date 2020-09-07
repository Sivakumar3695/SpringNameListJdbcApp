# SpringNameListJdbcApp
A simple Spring Application with CRUD operation for Learning

#### Before starting the application:
1. Create a simple Database with the name _**`TestDB`**_.
2. Create a table in mysql as below:
**_`Create Table NameList (ID int auto_increment not null, Name varchar(50) not null, SurName varchar(50), Primary Key (ID));`_**

Application is meant just for learning purpose.

We can do basic CRUD operation as listed below:

1. Create -> To as new Name and Surname
2. Read -> To read all the NameList, get Name and Surname for a particular name, get Name for given ID
3. Update -> To update the name and/or surname for the given ID.
4. Delete -> delete the Name entry from the list.