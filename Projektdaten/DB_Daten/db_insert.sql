
--INSERT Sektor--------------------------------

INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		1,
		'Ecke',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		2,
		'Nacharbeit',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		3,
		'Kawasaki',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		4,
		'Manuell',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		5,
		'Adept',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		6,
		'Fanuc Default',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		7,
		'IBM',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		8,
		'Ecke',
		'',
		0,
		0,
		0,
		0,
		0		
	);

INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		9,
		'Verteilung',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		10,
		'Staeubli 1',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		11,
		'Bandwechsel 1',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		12,
		'Staeubli 2',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		13,
		'Bandwechsel 2',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		14,
		'KuKa',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		15,
		'Zusammenführung',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
INSERT INTO Sektor(
		id_sektor, 
		bezeichnung,
		user_parameter, 
		stoerung, 
		position_x, 
		position_y, 
		position_z, 
		position_ausrichtung 
	) VALUES (
		8,
		'Ecke',
		'',
		0,
		0,
		0,
		0,
		0		
	);
	
--INSERT Warentraeger--------------------------------

INSERT INTO Warentraeger(
		id_warentraeger,
		bezeichnung,
		user_parameter,
		stoerung,
		montagezustand,
		RFID_inhalt,
		abstand_mm
	) VALUES (
		1,
		'Warentraeger 1',
		'',
		0,
		0,
		'',
		0
	);

--INSERT Artikel--------------------------------	
INSERT INTO Artikel (
		id_artikel,
		bezeichnung,
		user_parameter
	) VALUES (
		1,
		'Cranfield',
		''
	);
	
INSERT INTO Artikel (
		id_artikel,
		bezeichnung,
		user_parameter
	) VALUES (
		2,
		'Klemmenmontage',
		''
	);

--INSERT Transportband--------------------------------
INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		12,
		'TB Sek1 bis Sek2',
		'',
		0,
		0,
		0,
		1,
		2
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		23,
		'TB Sek2 bis Sek3',
		'',
		0,
		0,
		0,
		2,
		3
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		34,
		'TB Sek3 bis Sek4',
		'',
		0,
		0,
		0,
		3,
		4
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		45,
		'TB Sek4 bis Sek5',
		'',
		0,
		0,
		0,
		4,
		5
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		56,
		'TB Sek5 bis Sek6',
		'',
		0,
		0,
		0,
		5,
		6
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		67,
		'TB Sek6 bis Sek7',
		'',
		0,
		0,
		0,
		6,
		7
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		78,
		'TB Sek7 bis Sek8',
		'',
		0,
		0,
		0,
		7,
		8
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		89,
		'TB Sek8 bis Sek9',
		'',
		0,
		0,
		0,
		8,
		9
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		910,
		'TB Sek9 bis Sek10 (aussen)',
		'',
		0,
		0,
		0,
		9,
		10
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1011,
		'TB Sek10 bis Sek11 (aussen)',
		'',
		0,
		0,
		0,
		10,
		11
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		911,
		'TB Sek9 bis Sek11 (innen)',
		'',
		0,
		0,
		0,
		5,
		6
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1112,
		'TB Sek11 bis Sek12 (aussen)',
		'',
		0,
		0,
		0,
		11,
		12
	);


INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1213,
		'TB Sek12 bis Sek13 (aussen)',
		'',
		0,
		0,
		0,
		5,
		6
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1113,
		'TB Sek11 bis Sek13 (innen)',
		'',
		0,
		0,
		0,
		11,
		13
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1314,
		'TB Sek13 bis Sek14 (aussen)',
		'',
		0,
		0,
		0,
		13,
		14
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1415,
		'TB Sek14 bis Sek15 (aussen)',
		'',
		0,
		0,
		0,
		14,
		15
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		1315,
		'TB Sek13 bis Sek15 (innen)',
		'',
		0,
		0,
		0,
		13,
		15
	);

INSERT INTO Transportband (
		id_transportband,
		bezeichnung,
		user_parameter,
		stoerung,
		laenge,
		geschwindigkeit,
		id_sektor_vor,
		id_sektor_nach
	) VALUES (
		151,
		'TB Sek15 bis Sek1',
		'',
		0,
		0,
		0,
		15,
		1
	);
	
	
--INSERT Sensor--------------------------------
	
INSERT INTO Sensor (
		id_sensor,
		bezeichnung,
		user_parameter,
		stoerung,
		zustand,
		phy_adresse,
		id_sektor
	) VALUES (
		110,
		'WT vor Eingangsstopper',
		'',
		0,
		0,
		'11.0 E',
		1
	);
	
--INSERT Roboter--------------------------------
	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		3,
		'Kawasaki',
		'',
		0,
		0,
		0,
		0,
		0	
	);

INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		5,
		'Adept',
		'',
		0,
		0,
		0,
		0,
		0	
	);
	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		6,
		'Fanuc',
		'',
		0,
		0,
		0,
		0,
		0	
	);
	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		7,
		'IBM',
		'',
		0,
		0,
		0,
		0,
		0	
	);
	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		10,
		'Staeubli 1',
		'',
		0,
		0,
		0,
		0,
		0	
	);

	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		12,
		'Staeubli 2',
		'',
		0,
		0,
		0,
		0,
		0	
	);

	
INSERT INTO Roboter (
		id_roboter,
		bezeichnung,
		user_parameter,
		stoerung,
		position_x,
		position_y,
		position_z,
		position_ausrichtung
	) VALUES (
		14,
		'KuKa',
		'',
		0,
		0,
		0,
		0,
		0	
	);
	
INSERT INTO Gelenk (
		id_gelenk,	
		bezeichnung,
		user_parameter,
		typ,
		nummer,
		gelenkstellung,
		id_roboter
	) VALUES (
		1,
		'Beispiel Gelenk',
		'',
		'Rotationsgelenk',
		1,
		0,
		3
	);