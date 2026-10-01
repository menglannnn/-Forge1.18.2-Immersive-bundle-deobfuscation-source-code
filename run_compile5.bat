@echo off
cd /d "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"
call gradlew.bat compileJava --refresh-dependencies > compile5_output.txt 2>&1
echo. >> compile5_output.txt
echo EXIT_CODE=%ERRORLEVEL% >> compile5_output.txt