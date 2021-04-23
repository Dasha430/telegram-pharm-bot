@echo off
mvn clean install & java -jar BookStore/target/BookStore-1.0-SNAPSHOT-jar-with-dependencies.jar
@pause