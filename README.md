# technicalTest
User guide for test the Application :  

Author : Aziz Hadj Saïd 

Tools && Technologies :  

IntelliJ; 

Java11 

SpringBoot 

RestApi 

Talend Api

H2 Database Engine 

SLF4J 

Git 

Before running the application you need to add a path to the field  com.info.microDetails.path On the application.properties for the logging file and the db file,  

You can build a jar by this command line : mvn clean install -DskipTests 

And running the application by this command line : java -jar microdetails-0.0.1-SNAPSHOT.jar 

In the application I have used 3 webservices Rest :  

-To create user with specific details :  

Method : POST 

URL: http://localhost:8080/AddUser

Example(Request) : { "userName": "Aziz", "birthDay": "01/01/1995", "countryOfResidence": "France", "phoneNumber":"20318110", "gender" : "Male" } 

-To get all the users : 

Method : GET 

URL : http://localhost:8080/Users 

Example(Result) : User{id=1, userName='Aziz', birthDay=2000-01-01, countryOfResidence='France', phoneNumber='20319110', gender='Male'} 
 
-To get a specific user by ID :

Method : GET

URL : http://localhost:8080/User/1 

Example(Result) : 
User{id=1, userName='Aziz', birthDay=2000-01-01, countryOfResidence='France', phoneNumber='20319110', gender=''} 
