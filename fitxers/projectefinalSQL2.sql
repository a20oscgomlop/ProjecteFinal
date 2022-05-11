USE a20oscgomlop_provabasefinal;

CREATE TABLE IF NOT EXISTS EVENTS(
	id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150),
    country VARCHAR(150),
    town VARCHAR(150),
    date VARCHAR(150),
    styles VARCHAR(150),
    description TEXT,
    organizer VARCHAR(150)
    );
    
CREATE TABLE IF NOT EXISTS USER(
	username VARCHAR(150) PRIMARY KEY,
    full_name VARCHAR(150),
    birth_date VARCHAR(150),
    email VARCHAR(150),
    gender VARCHAR(150),
    country VARCHAR(150),
    language VARCHAR(150),
    description TEXT
    );

CREATE TABLE IF NOT EXISTS ATTEND(
	id INT,
    username VARCHAR(150),
    PRIMARY KEY(id,username),
    FOREIGN KEY (id) REFERENCES EVENTS (id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (username) REFERENCES USER (username)
		ON DELETE CASCADE ON UPDATE CASCADE
	);