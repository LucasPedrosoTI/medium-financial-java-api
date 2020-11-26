CREATE TABLE transaction (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  nsu VARCHAR(50),
  authorization_number VARCHAR(50) NOT NULL,
  transaction_date DATETIME NOT NULL,
  amount DECIMAL(10, 2) NOT NULL,
  type VARCHAR(20) NOT NULL,
  UNIQUE (nsu),
  UNIQUE (authorization_number)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
INSERT INTO transaction (
    nsu,
    authorization_number,
    transaction_date,
    amount,
    type
  )
VALUES (
    '220788',
    '010203',
    '2020-01-01 10:10:10',
    '22.88',
    'CARD'
  ),
  (
    null,
    '040506',
    '2020-01-01 10:10:10',
    '120.0',
    'MONEY'
  );