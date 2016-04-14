# SPMS
Swimming Pool Management Software

## Preparation
>"For the program to work a database should be hosted with any database server with tables as in `sqml.sql` provided here. And a FTP Server must also be kept running."

## Installation
* #### For Windows
    >"run `javac -cp .\jar\*.jar src\spms\*.java src\application\*.java src\user\*.java`.
      Then goto src directory and run 
`java -cp .;..\jar\mail-1.4.7.jar;..\jar\mysql-connector-java-5.1.28-bin.jar;..\jar\commons-net-3.4.jar spms.WelcomePage`"

* #### For Linux
    >"run `javac -cp .:./jar/* src/spms/*.java src/application/*.java src/user/*.java`.
      Then goto src directory and run `java -cp .:../jar/* spms.WelcomePage`"
