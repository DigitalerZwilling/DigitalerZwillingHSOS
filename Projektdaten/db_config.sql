DROP TABLE Roboter_Sektor;
DROP TABLE Transportband_Warentraeger;
DROP TABLE Sektor_Warentraeger;
DROP TABLE Artikel_Warentraeger;
DROP TABLE Hubquerpodest;
DROP TABLE Hubpodest;
DROP TABLE Gelenk;
DROP TABLE Roboter;
DROP TABLE Sensor;
DROP TABLE Transportband;
DROP TABLE Artikel;
DROP TABLE Warentraeger;
DROP TABLE Sektor;

CREATE TABLE Sektor (
	id_serktor BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	stoerung INT,
	position_x INT,
	position_y INT,
	position_z INT,
	position_ausrichtung INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	PRIMARY KEY (id_transportband)
	)

CREATE TABLE Warentraeger (
	id_warentraeger BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	stoerung INT,
	montagezustand INT,
	RFID_inhalt CHAR(128),
	abstand_mm INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	PRIMARY KEY (id_warentraeger)
	)

CREATE TABLE Artikel (
	id_artikel BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	zeitstempel TIMESTAMP,
	user_parameter CLOB,
	PRIMARY KEY (id_artikel)
	)
	
CREATE TABLE Transportband (
	id_transportband BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	stoerung INT,
	laenge INT,
	geschwindigkeit INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	id_sektor_vor BIGINT,
	id_sektor_nach BIGINT,
	PRIMARY KEY (id_transportband),
	FOREIGN KEY (id_sektor_vor) REFERENCES Sektor(id_sektor),
	FOREIGN KEY (id_sektor_nach) REFERENCES Sektor(id_sektor)
	)
	
CREATE TABLE Sensor (
	id_sensor BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	stoerung INT,
	zustand INT,
	phy_adresse CHAR(10),
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	id_sektor BIGINT,
	PRIMARY KEY (id_sensor),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	)

CREATE TABLE Roboter (
	id_roboter BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	stoerung INT,
	position_x INT,
	position_y INT,
	position_z INT,
	position_ausrichtung INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	PRIMARY KEY (id_roboter)
	)
	
CREATE TABLE Gelenk (
	id_gelenk BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	typ VARCHAR(100),
	nummer INT,
	gelenkstellung INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	id_roboter BIGINT,
	PRIMARY KEY (id_gelenk),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter)
	)
	
CREATE TABLE Hubpodest (
	id_hubpodest BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	oben INT,
	unten INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	id_sektor BIGINT,
	PRIMARY KEY (id_hubpodest),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	)
	
CREATE TABLE Hubquerpodest (
	id_hubquerpodest BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	bezeichnung VARCHAR(100),
	motor INT,
	oben INT,
	mittig INT,
	unten INT,
	user_parameter CLOB,
	zeitstempel TIMESTAMP,
	id_sektor BIGINT,
	PRIMARY KEY (id_hubquerpodest),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	)
	
CREATE TABLE Artikel_Warentraeger (
	id_artikel BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_artikel, id_warentraeger),
	FOREIGN KEY (id_artikel) REFERENCES Artikel(id_artikel),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	)
	
CREATE TABLE Sektor_Warentraeger (
	id_sektor BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_sektor, id_warentraeger),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	)
	
CREATE TABLE Transportband_Warentraeger (
	id_transportband BIGINT,
	id_warentraeger BIGINT,
	PRIMARY KEY (id_transportband, id_warentraeger),
	FOREIGN KEY (id_transportband) REFERENCES Transportband(id_transportband),
	FOREIGN KEY (id_warentraeger) REFERENCES Warentraeger(id_warentraeger)
	)
	
CREATE TABLE Roboter_Sektor (
	id_roboter BIGINT,
	id_sektor BIGINT,
	PRIMARY KEY (id_roboter, id_sektor),
	FOREIGN KEY (id_roboter) REFERENCES Roboter(id_roboter),
	FOREIGN KEY (id_sektor) REFERENCES Sektor(id_sektor)
	)