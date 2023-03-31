# tipico-ui-test
tipiko-ui-test
This test required MySQL db to be setup. Please follow link to 
Download mySQL officially from below link- http://dev.mysql.com/downloads/
Mysql server- https://dev.mysql.com/downloads/mysql/
MySql workbench- https://dev.mysql.com/downloads/workbench/

#creating database
  create database tipicoCar;

#create Table. first point to database you want to use and then create table

      use tipicoCar;
      create table activeJobs(JobTitle varchar(300), Department varchar(300), Location varchar(30));
      alter table activeJobs add primary key (JobTitle,Department,Location)

#check the details of your Table
      use tipicoCar;
      describe activeJobs;
      
# Update the globalvariable.properties file in resources with the connection details like- host, port, username and password.

# The test framework is based on Gherkins and TestNG and can be run using maven comands.


