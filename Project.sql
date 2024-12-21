use javaproject;

desc data;
CREATE TABLE data (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NULL,
    path VARCHAR(255) NULL,
    Email VARCHAR(100) NULL,
    bin_data LONGBLOB NULL,
);

desc users;
CREATE TABLE users (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NULL,
    Email VARCHAR(100) NULL UNIQUE,
);
