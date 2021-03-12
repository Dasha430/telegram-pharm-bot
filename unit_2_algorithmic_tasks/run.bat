@echo off
echo -------------Setting maven-------------
cd ..
cd maven
call setmaven.bat
cd ..
echo -------------task1-------------
cd unit_2_algorithmic_tasks\task-1
call mvn clean install
java -jar target/task-1-1.0-SNAPSHOT.jar
cd ..
echo -------------task2-------------
cd task-2
call mvn clean install
java -jar target/task-2-1.0-SNAPSHOT.jar
cd ..
echo -------------task2-------------
cd task-3
call mvn clean install
java -jar target/task-3-1.0-SNAPSHOT.jar
@pause