### About

Do you ever want to buy some tasty, tasty snacks in the middle of the night during some
last minute studying but that pesky ICA-store is closed? This is a flaw in our modern society.

The amazing team at FireWolf&copy; had a vision, and that
vision was to fix that flaw and bring everyone chips. This program will let you register yourself as a seller, your house as a store and your old bag of noodles you bought like two years ago as a product. Add your noodles to the store, give it a price and just wait for other app-users to clamor over that shit. You decide when to open and close up shop.

You will be able to see all stores in your area, reserve products and other shit i forgot.

This is FireMarknadsplatShopify.&trade;

### How to set up MySQL 

- Download MySQL Server at https://dev.mysql.com/downloads/mysql/
- Extract the zip-file named mysql-[version]-[system].zip
- Start the server by going to [path-to-mysql-folder]/bin and type the following command:
```
> mysqld
```
- To start mysql:
```
> mysql -u root
```
- Create database "firewolf":
```
> CREATE DB firewolf;
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

Add application.resources with the following text:
(WRITE THIS SEGMENT)


**Description of API**

In order to use this API, ActiveMQ- and MySQL-Server must be up and running.

You will be able to register both as customer and vendor. You will need to log in to use most endpoints. Post, patch, delete and get will work on Stores, Products, Users etc. 

#
### Endpoints and Entities

####localhost:[port]/swagger-ui.html

 
