DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS numbers;

CREATE TABLE users(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
)

CREATE TABLE numbers (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   user_id INT,
   number VARCHAR (40) NOT NULL,
   company VARCHAR (40) NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users (id)
)
INSERT INTO users (id, name) VALUES (1 , 'Kate');
  INSERT INTO users (id , name) VALUES(2 , 'Georg');
  INSERT INTO users (id , name) VALUES(3 , 'Alexandr');

INSERT INTO numbers (user_id, number, company) VALUES
  ('1', '90218403', 'EPAM'),
  ('2', '215313', 'Coca-cola'),
  ('3', '231613623', 'Pepsi');
  ('1', '623163662', 'Macdonald*s');