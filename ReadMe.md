### About
todo

### Endpoints
<b>Register new user/customer</b>   
POST http://localhost:8080/register   
Request body:   
```
{"fullName": "", "userName": "", "password": ""}
```
<b>Login</b>   
POST http://localhost:8080/login   
Request body:   
```
{"userName": "", "password": ""}
```
<b>Logout</b>   
POST http://localhost:8080/logout 

<b>Update logged in user/customer</b>   
PATCH http://localhost:8080/  
Request body (Only the field(s) that should be updated need to be included):   
```
{"fullName": "", "userName": "", "password": ""}
```

<b>Register logged in user as vendor</b>   
PATCH http://localhost:8080/vendor/register  

<b>Create store for logged in vendor</b>   
PATCH http://localhost:8080/vendor/register/store 

<b>Register Pending Order</b>
POST  http://localhost:8080/pending-orders
```
{"storeUUID": "", "userName": "", "password": ""}
```



### How to set up ActiveMQ for jms to work

- Download ActiveMQ 5 "Classic" from the official website.
- Type "java -version" in a command prompt to see if you have the java 64 or 32 bit version installed.
- The script InstallService.bat can be found in "downloaded folder"/bin/win
- Run the script as an administrator. Running it through the command line will show if it succeeded.
- Press the windows key and type "services" to see installed services on the computer then navigate to ActiveMQ and right click on it to start it.
- A manager for ActiveMQ should now exist on localhost:8161. To see an overview of the queues, go to the link, click on manage, enter admin as both the username and password and then go to the queue tab.
- Add this to application properties in both the sender and receiver project to be able to connect to the queues:
```
spring.activemq.user=admin
spring.activemq.password=admin
spring.activemq.broker-url=tcp://localhost:61616
```
FireWolf jms receiver project:
https://github.com/joeliths/Jms

Add application.resources with the following text:
(WRITE THIS SEGMENT)


**Description of API**

**End Points** 

**User:**

Reserve products:


Vendor:
customer:

 
