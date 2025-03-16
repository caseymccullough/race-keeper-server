CREATE TABLE Runner(
 	runner_id SERIAL PRIMARY KEY,
   first_name VARCHAR(50),
   last_name VARCHAR(50),
	street VARCHAR(100),
city VARCHAR(50),
state_code CHAR(2),
gender_code CHAR(1) CHECK (gender_code IN ('M', 'F')),
   country VARCHAR(50),
   birthday DATE CHECK (birthday > '1900-01-01')
);

INSERT INTO Runner (first_name, last_name, street, city, state_code, gender_code, country, birthday)
VALUES
('Casey', 'McCullough', '99 Main Street', 'Phoenix', 'AZ', 'M', 'USA','1971-03-09'),
('Mary', 'Jones', '234 Maple Street', 'San Jose', 'CA', 'F', 'USA','1980-06-09')



SELECT * FROM Runner

SELECT runner_id, first_name, last_name, street, city, state_code, gender_code, birthday FROM runner
