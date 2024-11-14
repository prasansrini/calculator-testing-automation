# Introduction
Maven application to automate tests for Samsung Calculator mobile and Windows applications.

## Setup:
1. Run `setup.bat` for Windows or `setup.sh` for Linux. This script installs the dependencies and starts appium server.
2. Install Android drivers including build-tools in Android SDK.
3. Connect an Android Samsung device.

## Tests using python
Open this repository in VS Code and run `python tests.py` to run Android tests.

## Tests using maven projects:
Run `cd windows-tests` and `run_windows_tests.bat`(`run_linux_tests.sh` if the host is Linux).

## Technology used:
1. Appium
2. Python
3. VS Code
4. Node.js
5. Android Debug Bridge
6. Java 21
7. Maven