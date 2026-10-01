@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
echo Starting SRG to Official mapping replacement...
powershell -ExecutionPolicy Bypass -NoProfile -File "%~dp0fix_srg_names.ps1"
echo Done.
pause