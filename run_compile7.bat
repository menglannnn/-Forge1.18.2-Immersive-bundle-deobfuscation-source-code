@echo off
cd /d "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"
call gradlew.bat compileJava > compile7_output.txt 2>&1
echo. >> compile7_output.txt
echo EXIT_CODE=%ERRORLEVEL% >> compile7_output.txt