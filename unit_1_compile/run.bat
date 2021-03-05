@echo off
echo -----Terminal-----
cd terminal
javac -sourcepath .\src -d bin -cp .\libs\plexus-utils-3.3.0.jar;.\libs\google-collect-1.0.jar src\com\metanit\SimpleOutput.java src\com\metanit\Test.java src\com\metanit\Main.java
java -cp bin;.\libs\plexus-utils-3.3.0.jar;.\libs\google-collect-1.0.jar com.metanit.Main
echo ------------------
cd..
cd ant
call setant.bat
echo -------Ant-------
echo Main-Class: com.metanit.Main>MANIFEST.MF
call ant clean
call ant compile
call ant jar
call ant run
echo -----------------
cd..
cd maven\app
echo ------Maven------
call setmaven.bat
call mvn clean install
java -jar target/app-1.0-SNAPSHOT.jar
echo -----------------
@pause
