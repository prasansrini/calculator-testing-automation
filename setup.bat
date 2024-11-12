@echo off
SETLOCAL

REM Function to check if Node.js is installed
where node >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Node.js is not installed. Installing Node.js...
    REM Download and install Node.js (using Chocolatey)
    REM Make sure Chocolatey is installed before running this command
    choco install nodejs -y
) ELSE (
    echo Node.js is already installed.
)

REM Function to check if Python is installed
where python >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Python is not installed. Please install Python from https://www.python.org/downloads/
    exit /b
) ELSE (
    echo Python is already installed.
)

REM Function to check if Appium is installed
where appium >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    echo Appium is not installed. Installing Appium...
    npm install -g appium
) ELSE (
    echo Appium is already installed.
)

REM Run Appium server
echo Running Appium server...
start /B appium --log-level info --log-no-colors > appium_log2.txt
echo Appium server is running.

ENDLOCAL