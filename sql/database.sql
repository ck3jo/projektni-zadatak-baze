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

INSERT INTO Igraci(Ime, Nadimak, Prezime, DatumRodjenja, IDTima, Rejting, MajorTrofeji, MajorMVP) VALUES
(),
(),
(),
(),
(),
(),
(),
();

INSERT INTO Treneri(Ime, Nadimak, Prezime, IDTima) VALUES 
(),
(),
(),
(),
(),
();

INSERT INTO Timovi(ImeTima, LogoTima, RangPozicija, ProsecneGodine) VALUES
(),
(),
(),
();

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
