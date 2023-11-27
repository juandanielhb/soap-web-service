CREATE DATABASE PARAMETA;

CREATE USER 'soap_app_user' IDENTIFIED BY '123456';
GRANT ALL ON PARAMETA.* TO 'soap_app_user';

SHOW GRANTS FOR 'soap_app_uidser'@'%';

CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    document_type VARCHAR(2) NOT NULL,
    document_number VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    joining_date DATE NOT NULL,
    position VARCHAR(255) NOT NULL,
    salary INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT chk_salary CHECK (salary >= 0)
);

CREATE UNIQUE INDEX idx_unique_document
ON employee (document_type, document_number);

SELECT * FROM employee; 