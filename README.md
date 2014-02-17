DemoSQLInjection
================

This is a Netbeans project to demonstrate SQL injection. 

Import this project in netbeans and add your appropriate driver.jar for mysql i've added one driver for mariaDB.

Add the database userdata to your mysql config:

mysql -u root -p < userdata.sql

Change password and username to your mysql preferences in the MainFrame class row 16.

Also change if needed, the jdbc driver in Database class! row 109. I use mariaDB.

Only valid password and username:

username: admin
password: admin

Bypass password by enable the radio button and enter for example:

anything' OR 'x'='x

