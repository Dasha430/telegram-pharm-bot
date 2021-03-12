@echo off
set MAVEN_OPTS=-Xmx2G -Dfile.encoding=UTF-8

set MAVEN_HOME=%~dp0apache-ant
set PATH=%MAVEN_HOME%\bin;%PATH%
echo Setting ant home to: %ANT_HOME%
mvn -version