@echo off
cd /d "c:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"
call gradlew.bat compileJava 2>&1
echo EXIT_CODE=%ERRORLEVEL%