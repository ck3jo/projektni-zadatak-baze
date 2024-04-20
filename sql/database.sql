CREATE DATABASE cs2novosti;
USE DATABASE cs2novosti;

CREATE TABLE Mecevi (
    IDMeca INT PRIMARY KEY AUTO_INCREMENT,
    PrviTim INT,
    DrugiTim INT,
    IDTurnira INT,
    BrojMapa INT NOT NULL,
    Rezultat VARCHAR(3) NOT NULL,
    DatumMeca DATE NOT NULL,
    FOREIGN KEY (PrviTim) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
    FOREIGN KEY (DrugiTim) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
    FOREIGN KEY (IDTurnira) REFERENCES Turniri(IDTurnira)
    ON UPDATE CASCADE
    ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE Turniri (
    IDTurnira INT PRIMARY KEY AUTO_INCREMENT,
    Ime VARCHAR(45) NOT NULL,
    DatumPocetka DATE NOT NULL,
    DatumZavrsetka DATE NOT NULL,
    MestoIgranja VARCHAR(45),
    NagradniFond INT,
    VeciTurnir TINYINT NOT NULL 
) ENGINE=INNODB;

CREATE TABLE Igraci (
    IDIgraca INT PRIMARY KEY AUTO_INCREMENT,
    Ime VARCHAR(45) NOT NULL,
    Nadimak VARCHAR(45) NOT NULL,
    Prezime VARCHAR(45) NOT NULL,
    DatumRodjenja DATE,
    IDTima INT,
    Rejting DECIMAL(10, 2),
    MajorTrofeji INT,
    MajorMVP INT,
    FOREIGN KEY (IDTima) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE Transferi (
    IDTransfera INT PRIMARY KEY AUTO_INCREMENT,
    IDIgraca INT NOT NULL,
    IDStarogTima INT NOT NULL,
    IDNovogTima INT NOT NULL,
    DatumTransfera DATETIME NOT NULL,
    FOREIGN KEY (IDIgraca) REFERENCES Igraci(IDIgraca)
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
    FOREIGN KEY (IDStarogTima) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION,
    FOREIGN KEY (IDNovogTima) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE Timovi (
    IDTima INT PRIMARY KEY AUTO_INCREMENT,
    ImeTima VARCHAR(45) NOT NULL,
    LogoTima BLOB,
    RangPozicija INT,
    ProsecneGodine INT
) ENGINE=INNODB;

CREATE TABLE Treneri (
    IDTrenera INT PRIMARY KEY AUTO_INCREMENT,
    Ime VARCHAR(45) NOT NULL,
    Nadimak VARCHAR(45) NOT NULL,
    Prezime VARCHAR(45) NOT NULL,
    IDTima INT,
    FOREIGN KEY (IDTima) REFERENCES Timovi(IDTima)
    ON UPDATE CASCADE
    ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE Vesti (
    IDVesti INT PRIMARY KEY AUTO_INCREMENT,
    Naslov VARCHAR(45),
    DatumObjavljivanja DATE,
    IDAutora INT,
    FOREIGN KEY (IDAutora) REFERENCES Autori(IDAutora)
    ON UPDATE CASCADE
    ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE Autori (
    IDAutora INT PRIMARY KEY AUTO_INCREMENT,
    Ime VARCHAR(45) NOT NULL,
    Nadimak VARCHAR(45) NOT NULL,
    Prezime VARCHAR(45) NOT NULL
) ENGINE=INNODB;

INSERT INTO igraci(Ime, Nadimak, Prezime, DatumRodjenja, IDTima, Rejting, MajorTrofeji, MajorMVP) VALUES
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,),
("", "", "", '', , '', , ,);

INSERT INTO treneri(Ime, Nadimak, PREZIME) VALUES
("Filip", "NEO", "Kubski", 1),
("Andrej", "B1ad3", "Gorodenskij", 2),
("Denis", "sycrone", "Nielsen", 3),
("Sergej", "hally", "Šavajev", 4),
("Viktor", "TAZ", "Vojtas", 5),
("Remi", "XTQZZZ", "Kunjam", 6),
("Sezgin", "Fabre", "Kalajci", 7),
("Konstantin", "groove", "Pikiner", 8),
("Eetu", "sAw", "Saha", 9),
("Dastan", "dastan", "Akbajev", 10),
("Kasper", "ruggah", "Due", 11),
("Deni", "zonic", "Sorensen", 12),
("Tiaan", "T.c", "Koertzen", 13),
("Nikolas", "guerri", "Nogeria", 14),
("Jakub", "kuben", "Gurćinski", 15),
("Enrike", "rikz", "Vaku", 16),
("Vilton", "zews", "Prado", 17),
("Rafael", "zakk", "Fernandes", 18),
("Peter", "casle", "Ardenskjold", 19),
("Erdenedalaji", "maaRaa", "Bajanbat", 20);

INSERT INTO timovi (ImeTima, RangPozicija, MajorTrofeji, Region) VALUES
("FaZe Clan", 1, 1, "Evropa"),
("Natus Vincere", 2, 2, "Evropa"),
("MOUZ", 3, 0, "Evropa"),
("Team Spirit", 4, 0, "Evropa"),
("G2 Esports", 5, 0, "Evropa"),
("Team Vitality", 6, 1, "Evropa"),
("Eternal Fire", 7, 0, "Evropa"),
("Cloud9", 8, 1, "Evropa"),
("HEROIC", 9, 0, "Evropa"),
("Virtus.pro", 10, 0, "Evropa"),
("Astralis", 11, 4, "Evropa"),
("Team Falcons", 12, 0, "Evropa"),
("Complexity Gaming", 13, 0, "Severna Amerika"),
("FURIA Esports", 14, 0, "Juzna Amerika"),
("ENCE Esports", 15, 0, "Evropa"),
("paiN Gaming", 16, 0, "Juzna Amerika"),
("Team Liquid", 17, 0, "Severna Amerika"),
("Imperial Esports", 18, 0, "Juzna Amerika"),
("Gaimin Gladiators", 19, 0, "Evropa"),
("TheMongolz", 20, 0, "Azija");

INSERT INTO Mecevi(PrviTim, DrugiTim, IDTurnira, BrojMapa, Rezultat, DatumMeca) VALUES
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO Vesti(Naslov, DatumObjavljivanja, IDAutora) VALUES 
(),
(),
(),
();

INSERT INTO Autori(Ime, Nadimak, Prezime) VALUES
(),
(),
(),
(),
();

INSERT INTO Transferi(IDIgraca, IDStarogTima, IDNovogTima, DatumTransfera) VALUES
(),
(),
(),
(),
(),
();

INSERT INTO Turniri(Ime, DatumPocetka, DatumZavrsetka, MestoIgranja, NagradniFond, VeciTurnir) VALUES
(),
(),
(),
(),
(),
();
