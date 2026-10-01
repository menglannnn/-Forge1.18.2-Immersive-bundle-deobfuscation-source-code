@echo off
cd /d "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"
set GRADLE_USER_HOME=C:\GradleHome
call gradlew.bat compileJava > compile8_output.txt 2>&1
echo. >> compile8_output.txt
echo EXIT_CODE=%ERRORLEVEL% >> compile8_output.txt