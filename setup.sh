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
    appium &
    echo "Appium server is running in the background."
}

# Main script execution
check_node
check_appium
run_appium