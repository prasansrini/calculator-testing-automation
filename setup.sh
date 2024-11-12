#!/bin/bash

# Function to check if Node.js is installed
check_node() {
    if command -v node &> /dev/null; then
        echo "Node.js is already installed."
    else
        echo "Node.js is not installed. Installing Node.js..."
        # Install Node.js (for Ubuntu/Debian)
        curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
        sudo apt-get install -y nodejs
    fi
}

# Function to check if Python is installed
check_python() {
    if command -v python3 &> /dev/null; then
        echo "Python is already installed."
    else
        echo "Python is not installed. Please install Python."
        echo "You can install it using the following command:"
        echo "sudo apt-get install -y python3"
        exit 1
    fi
}

# Function to check if Appium is installed
check_appium() {
    if command -v appium &> /dev/null; then
        echo "Appium is already installed."
    else
        echo "Appium is not installed. Installing Appium..."
        npm install -g appium
    fi
}

# Function to run Appium server
run_appium() {
    echo "Running Appium server..."
    appium --log-level info --log-no-colors > appium_log2.txt
    echo "Appium server is running in the background."
}

# Main script execution
check_node
check_python
check_appium
run_appium