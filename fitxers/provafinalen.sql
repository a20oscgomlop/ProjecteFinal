USE a20oscgomlop_provafin;
CREATE TABLE USUARIS (
    id_usuari varchar(40) PRIMARY KEY
);

CREATE TABLE EVENTOS (
                      id_event int AUTO_INCREMENT,
                      titol VARCHAR(100),
                      link VARCHAR(100),
                      pais VARCHAR(100),
                      dia VARCHAR(100),
                      PRIMARY KEY(id_event)
);

CREATE TABLE PARTICIPEN (
                      id_usuari varchar(40),
                      id_event int,
                      PRIMARY KEY (id_usuari, id_event),
                      FOREIGN KEY (id_usuari) REFERENCES USUARIS (id_usuari)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                      FOREIGN KEY (id_event) REFERENCES EVENTOS (id_event)
                          ON DELETE CASCADE ON UPDATE CASCADE
);