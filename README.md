# SpringJDBC-StoredProcedure
**Intro to Spring JDBC**. (MySQL & Java)<br/

**Bean definition**: The objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container. These beans are created with the configuration metadata that you supply to the container. sc: https://www.tutorialspoint.com/spring/spring_bean_definition.htm<br/>

**Spring IoC (Inversion of Control)** container in the case of this program simply instantiates and assembles the MySQL database connection for our ProductDAO class to use.<br/>

Program connects to database and uses a prepared statement to query all product codes. Then 2-6 of those product codes are randomly chosen
to be included in the delimited string. The delimited string is then counted, put into an array and split. Final delimited string array is fed to a stored procedure one by one.<br/>

All data in the database is fake and used for testing only.<br/>
This program is the same as JDBC-StoredProcedure but it is now using the Spring Framework.<br/>
Created by Kevin Kemmerer


# Program being run using Eclipse IDE
![jdbc1](https://github.com/kkemmere/SpringJDBC-StoredProcedure/blob/main/Screen%20Shot%202022-06-09%20at%207.52.22%20PM.png)
<br/>


# Image of simple stored procedure
![jdbc1](https://github.com/kkemmere/JDBC-StoredProcedure/blob/main/Screen%20Shot%202022-05-26%20at%202.59.36%20AM.png)
<br/>
