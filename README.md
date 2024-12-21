# File Guard: Java Project

## Introduction

This project allows users to sign up and sign in using email OTP verification. After logging in, users can manage hidden files on their system. The main features include hiding files, showing hidden files, and unhiding files.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [Disclaimer](#disclaimer)

## Features

- **Sign Up and Sign In**: Secure user authentication using email OTP.
- **File Management Options**:
  1. Show hidden files.
  2. Hide a new file.
  3. Unhide a file.
  4. Go to the welcome screen.
  5. Exit the program.
- **Hide File**: Enter the file path to hide the file. If the file exists, it will be hidden; otherwise, an error message is displayed.
- **Show Hidden Files**: Display a list of all hidden files.
- **Unhide File**: Show all hidden files and allow the user to select a file to unhide by its index number.

## Concept

When a file is hidden, it is deleted from the user's PC and stored in a MySQL database. When a file is unhidden, it is deleted from the database and recreated on the user's PC. The project can store files up to a maximum of 100KB. If a file exceeds this size limit, its data will be deleted permanently.

## Installation
### First you install this code before you should check the pdf of it's.because you may some changes in your code like write your email id, password (security Key), mysql port,mysql password etc.

1. **Clone the repository**

    ```bash
    # Clone the repository
    git clone <repository-link>

    # Navigate to the project directory
    cd <project-directory>

    # Install dependencies
    # Add any necessary installation commands here
    ```

2. **Set up the MySQL database**
   - Create a new database and configure the connection settings in the project.



## Usage
#### you need a code editer and you must be lern how to import this project in your code editer.
1. **Run the Project**

    ```bash
    # Compile the Java project
    javac Main.java

    # Run the project
    java Main
    ```

2. **Sign Up and Sign In**
   - Follow the prompts to sign up using email OTP and sign in.
3. **File Management**
   - Choose an option from the menu to manage hidden files.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## Disclaimer

Please note that File Guard can store files up to a maximum size of 100KB. If a file exceeds this size limit, its data will be permanently deleted. Use this feature with caution.
