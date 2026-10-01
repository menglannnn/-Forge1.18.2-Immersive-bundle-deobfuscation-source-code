@echo off
cd /d "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"
set JAVA_HOME=C:\Program Files\Java\jdk-17
set GRADLE_USER_HOME=C:\GradleHome
call gradlew.bat compileJava --stacktrace 2>&1 > compile12_output.txt
echo. >> compile12_output.txt
echo EXIT_CODE=%ERRORLEVEL% >> compile12_output.txt