@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=C:\Program Files\Java\jdk-17\bin;%PATH%
set GRADLE_USER_HOME=C:\GradleHome
cd /d X:\
call gradlew.bat --no-daemon --stacktrace compileJava
echo EXIT_CODE=%ERRORLEVEL%