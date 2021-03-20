@echo off
echo -------------Setting maven-------------
call setmaven.bat
echo -------------Homework 3-------------
call mvn clean install
java -jar ReversingString/target/ReversingString-1.0-SNAPSHOT-jar-with-dependencies.jar
@pause