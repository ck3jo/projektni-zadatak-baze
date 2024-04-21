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

INSERT INTO igraci(Ime, Nadimak, Prezime, DatumRodjenja, Nacionalnost, IDTima, Rejting, MajorTrofeji, MajorMVP) VALUES
("Finn", "karrigan", "Andersen", '1990-04-14', "Danska", 1, '0.91', 1, 0),
("Håvard", "rain", "Nygaard", '1994-08-27', "Norveška", 1, '1.06', 1, 1),
("David", "frozen", "Čerňanský", '2002-07-18', "Slovačka", 1, '1.19', 0, 0),
("Robin", "ropz", "Kool", '1999-12-22', "Estonija", 1, '1.12', 1, 0),
("Helvijs", "broky", "Saukants", '2001-12-22', "Letonija", 1, '1.18', 1, 0),
("Aleksi", "Aleksib", "Virolainen", '1997-03-30', "Finska", 2, '0.9', 1, 0),
("Mihai", "iM", "Ivan", '1999-07-29', "Rumunija", 2, '1.00', 1, 0),
("Valeriy", "b1t", "Vakhovskiy", '2003-05-01', "Ukrajina", 2, '1.08', 2, 0),
("Justinas", "jL", "Lekavicius", '1999-09-29', "Litvanija", 2, '1.16', 1, 1),
("Ihor", "w0nderful", "Zhdanov", '2004-12-14', "Ukrajina", 2, '1.14', 1, 0),
("Ludvig", "Brollan", "Brolin", '2002-06-17', "Švedska", 3, '1.11', 0, 0),
("Kamil", "siuhy", "Szkaradek", '2002-08-26', "Poljska", 3, '0.91', 0, 0),
("Ádám", "torzsi", "Torzsás", '2002-05-17', "Mađarska", 3, '1.13', 0, 0),
("Jimi", "Jimpphat", "Salo", '2006-09-09', "Finska", 3, '1.19', 0, 0),
("Dorian", "xertioN", "Berman", '2004-07-22', "Izrael", 3, '1.13', 0, 0),
("Leonid", "chopper", "Vishnyakov", '1997-02-04', "Rusija", 4, '1.02', 0, 0),
("Dmitry", "sh1ro", "Sokolov", '2001-07-15', "Rusija", 4, '1.22', 0, 0),
("Boris", "magixx", "Vorobiev", '2003-06-13', "Rusija", 4, '1.10', 0, 0),
("Danil", "donk", "Kryshkovets", '2007-01-25', "Rusija", 4, '1.50', 0, 0),
("Myroslav", "zont1x", "Plakhotia", '2005-07-20', "Ukrajina", 4, '1.12', 0, 0),
("Nikola", "NiKo", "Kovač", '1997-02-16', "Bosna i Hercegovina", 5, '1.18', 0, 0),
("Nemanja", "huNter-", "Kovač", '1996-01-03', "Bosna i Hercegovina", 5, '1.02', 0, 0),
("Nemanja", "nexa", "Isaković", '1997-04-25', "Bosna i Hercegovina", 5, '1.01', 0, 0),
("Rasmus", "HooXi", "Nielsen", '1995-05-21', "Danska", 5, '0.93', 0, 0),
("Ilya", "m0NESY", "Osipov", '2006-05-01', "Rusija", 5, '1.37', 0, 0),
("Dan", "apEX", "Madesclaire", '1993-02-22', "Francuska", 6, '0.99', 2, 0),
("Mathieu", "ZywOo", "Herbaut", '2000-11-09', "Francuska", 6, '1.35', 1, 1),
("Shahar", "flameZ", "Shushan", '2003-06-22', "Izrael", 6, '1.14', 0, 0),
("Lotan", "Spinx", "Giladi", '2000-09-13', "Izrael", 6, '1.15', 1, 0),
("William", "mezii", "Merriman", '1998-10-15', "Sjedinjeno Kraljevstvo", 6, '1.00', 0, 0),
("Engin", "MAJ3R", "Küpeli", '1991-01-25', "Turska", 7, '1.01', 0, 0),
("Ismailcan", "XANTARES", "Dörtkardeş", '1995-08-07', "Turska", 7, '1.25', 0, 0),
("Özgür", "woxic", "Eker", '1998-09-02', "Turska", 7, '1.10', 0, 0),
("Buğra", "Calyx", "Arkın", '1998-07-28', "Turska", 7, '1.03', 0, 0),
("Ali Haydar", "Wicadia", "Yalçın", '2005-03-04', "Turska", 7, '1.10', 0, 0),
("Abay", "HObbit", "Khassenov", '1994-05-18', "Kazakstan", 8, '1.10', 1, 0),
("Kirill", "Boombl4", "Mikhailov", '1998-12-20', "Rusija", 8, '1.03', 1, 0),
("Sergey", "Ax1le", "Rykhtorov", '2002-04-29', "Rusija", 8, '1.09', 0, 0),
("Ilya", "Perfecto", "Zalutskiy", '1999-11-24', "Rusija", 8, '1.08', 0, 0),
("Timur", "buster", "Tulepov", '1999-12-17', "Kazakstan", NULL, '1.00', 0, 0),
("Guy", "NertZ", "Iluz", '1999-07-12', "Izrael", 9, '1.10', 0, 0),
("René", "TeSeS", "Madsen", '2000-12-12', "Danska", 9, '1.06', 0, 0),
("Nico", "nicoodoz", "Tamjidi", '2000-08-02', "Danska", 9, '1.01', 0, 0),
("Rasmus", "sjuush", "Beck", '1999-01-03', "Danska", 9, '1.04', 0, 0),
("Damjan", "kyxsan", "Stoilkovski", '2000-05-25', "Severna Makedonija", 9, '0.99', 0, 0),
("Denis", "electroNic", "Sharipov", '1998-09-02', "Rusija", 10, '1.12', 1, 0),
("Evgenii", "FL1T", "Lebedev", '2000-12-21', "Rusija", 10, '1.21', 1, 0),
("Dzhami", "Jame", "Ali", '1998-09-23', "Rusija", 10, '1.12', 1, 1),
("David", "n0rb3r7", "Danielyan", '2001-03-11', "Rusija", 10, '1.07', 1, 0),
("Petr", "fame", "Bolyshev", '2003-03-03', "Rusija", 10, '1.14', 1, 0),
("", "", "", '', "", 11, '', , ),
("", "", "", '', "", 11, '', , ),
("", "", "", '', "", 11, '', , ),
("", "", "", '', "", 11, '', , ),
("", "", "", '', "", 11, '', , ),
("", "", "", '', "", 12, '', , ),
("", "", "", '', "", 12, '', , ),
("", "", "", '', "", 12, '', , ),
("", "", "", '', "", 12, '', , ),
("", "", "", '', "", 12, '', , ),
("", "", "", '', "", 13, '', , ),
("", "", "", '', "", 13, '', , ),
("", "", "", '', "", 13, '', , ),
("", "", "", '', "", 13, '', , ),
("", "", "", '', "", 13, '', , ),
("", "", "", '', "", 14, '', , ),
("", "", "", '', "", 14, '', , ),
("", "", "", '', "", 14, '', , ),
("", "", "", '', "", 14, '', , ),
("", "", "", '', "", 14, '', , ),
("", "", "", '', "", 15, '', , ),
("", "", "", '', "", 15, '', , ),
("", "", "", '', "", 15, '', , ),
("", "", "", '', "", 15, '', , ),
("", "", "", '', "", 15, '', , ),
("", "", "", '', "", 16, '', , ),
("", "", "", '', "", 16, '', , ),
("", "", "", '', "", 16, '', , ),
("", "", "", '', "", 16, '', , ),
("", "", "", '', "", 16, '', , ),
("", "", "", '', "", 17, '', , ),
("", "", "", '', "", 17, '', , ),
("", "", "", '', "", 17, '', , ),
("", "", "", '', "", 17, '', , ),
("", "", "", '', "", 17, '', , ),
("", "", "", '', "", 18, '', , ),
("", "", "", '', "", 18, '', , ),
("", "", "", '', "", 18, '', , ),
("", "", "", '', "", 18, '', , ),
("", "", "", '', "", 18, '', , ),
("", "", "", '', "", 19, '', , ),
("", "", "", '', "", 19, '', , ),
("", "", "", '', "", 19, '', , ),
("", "", "", '', "", 19, '', , ),
("", "", "", '', "", 19, '', , ),
("", "", "", '', "", 20, '', , ),
("", "", "", '', "", 20, '', , ),
("", "", "", '', "", 20, '', , ),
("", "", "", '', "", 20, '', , ),
("", "", "", '', "", 20, '', , );
 
INSERT INTO treneri(Ime, Nadimak, Prezime) VALUES
("Filip", "NEO", "Kubski", 1),
("Andrej", "B1ad3", "Gorodskij", 2),
("Denis", "sycrone", "Nieln", 3),
("Sergej", "hally", "Šavaj", 4),
("Viktor", "TAZ", "Vojtas", 5),
("Remi", "XTQZZZ", "Kunjam", 6),
("Sezgin", "Fabre", "Kalaj", 7),
("Konstantin", "groove", "ikiner", 8),
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
