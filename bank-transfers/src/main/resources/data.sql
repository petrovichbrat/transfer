DROP TABLE IF EXISTS account;
DROP SEQUENCE IF EXISTS ACCOUNT_SEQ;

CREATE SEQUENCE ACCOUNT_SEQ;
CREATE TABLE account (
    ID NUMERIC DEFAULT (NEXT VALUE FOR ACCOUNT_SEQ) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(250) NOT NULL,
    balance DECIMAL
);

INSERT INTO account (name, balance) VALUES
('1231-2133-3213-3123', 1000000),
('1231-2133-3213-4121',1000000)