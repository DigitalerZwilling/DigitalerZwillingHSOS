
INSERT INTO Sektor (id_sektor, bezeichnung, zeitstempel, user_parameter, stoerung, position_x, position_y, position_z, position_ausrichtung)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,INT*/);

INSERT INTO Warentraeger (id_warentraeger, bezeichnung, zeitstempel, user_parameter, stoerung, montagezustand, RFID_inhalt, abstand_mm)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,CHAR(128),INT*/);

INSERT INTO Artikel (id_artikel, bezeichnung, zeitstempel, user_parameter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB*/);

INSERT INTO Transportband (id_transportband, bezeichnung, zeitstempel, user_parameter, stoerung, laenge, geschwindigkeit, id_sektor_vor, id_sektor_nach)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,BIGINT,BIGINT*/);

INSERT INTO Sensor (id_sensor, bezeichnung, zeitstempel, user_parameter, stoerung, zustand, phy_adresse, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,CHAR(10),BIGINT*/);

INSERT INTO Roboter (id_roboter, bezeichnung, zeitstempel, user_parameter, stoerung, position_x, position_y, position_z, position_ausrichtung)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,INT*/);

INSERT INTO Gelenk (id_gelenk, bezeichnung, zeitstempel, user_parameter, typ, nummer, gelenkstellung, id_roboter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,VARCHAR(100),INT,INT,BIGINT*/);

INSERT INTO Werkzeug (id_werkzeug, bezeichnung, zeitstempel, user_parameter, zustand, id_roboter)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,BIGINT*/);

INSERT INTO Hubpodest (id_hubpodest, bezeichnung, zeitstempel, user_parameter, oben, unten, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,BIGINT*/);

INSERT INTO Hubquerpodest (id_hubquerpodest, bezeichnung, zeitstempel, user_parameter, motor, oben, mittig, unten, id_sektor)
VALUES (/*BIGINT,VARCHAR(100),TIMESTAMP,CLOB,INT,INT,INT,INT,BIGINT*/);












