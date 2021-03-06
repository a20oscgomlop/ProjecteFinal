USE a20oscgomlop_projectefinal;

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

CREATE TABLE IF NOT EXISTS FOLLOW(
	user_follows VARCHAR(150),
    user_followed VARCHAR(150),
	PRIMARY KEY(user_follows,user_followed),
    FOREIGN KEY (user_follows) REFERENCES USER (username)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (user_followed) REFERENCES USER (username)
		ON DELETE CASCADE ON UPDATE CASCADE
	);
    
CREATE TABLE IF NOT EXISTS BLOCK(
	user_blocks VARCHAR(150),
    user_blocked VARCHAR(150),
	PRIMARY KEY(user_blocks,user_blocked),
    FOREIGN KEY (user_blocks) REFERENCES USER (username)
		ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (user_blocked) REFERENCES USER (username)
		ON DELETE CASCADE ON UPDATE CASCADE
	);
    
    