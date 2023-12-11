# payment-project
payment micro-service

Before running the app , please set-up the mysql server from here
https://dev.mysql.com/downloads/installer/


please notice : it's a bunch of mysql component (mysql server , workbench ,connectors ..etc)
so it will install these components easily for you.

if you have already mysql server , please ensure the root user has a root password and skip this step.

--------------------------------------------

for the application 

run this command( sure you must have installed maven in your device):
mvn clean package -DskipTests

then go to target folder and run this command :

java -jar mintos-task-0.0.1-SNAPSHOT.jar

then enjoy :)

notice :

1- you can docker for mysql server easily , and run the jar file .
2- there is a postman collection and this is the link
https://api.postman.com/collections/19810691-f5514437-911d-4d9b-9909-83d1fc66a3bd?access_key=PMAT-01HHCXDZ1C0RFC1B3A2T6VDGA7


sorry for that , i wanted to configure docker to be easier installment but i just in project pressure and wanted to do everything but time i don't owe.

Thank you :)



