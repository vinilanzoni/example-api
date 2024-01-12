DROP TABLE IF EXISTS CUSTOMER;
CREATE TABLE CUSTOMER
(
    id     BIGINT       NOT NULL AUTO_INCREMENT,
    name   VARCHAR(255) NOT NULL,
    email  VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    cep          VARCHAR(255) NOT NULL,
    street       VARCHAR(255) NOT NULL,
    number       INT          NOT NULL,
    complement   VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    city         VARCHAR(255) NOT NULL,
    state        VARCHAR(255) NOT NULL,
    customer_id  BIGINT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMER (id)
);