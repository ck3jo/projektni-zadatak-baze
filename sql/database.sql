    CREATE DATABASE `database-project`;
    USE `database-project`;

    CREATE TABLE Autori (
        IDAutora INT PRIMARY KEY AUTO_INCREMENT,
        Ime VARCHAR(45) NOT NULL,
        Nadimak VARCHAR(45) NOT NULL,
        Prezime VARCHAR(45) NOT NULL
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

    CREATE TABLE Timovi (
        IDTima INT PRIMARY KEY AUTO_INCREMENT,
        ImeTima VARCHAR(45) NOT NULL,
        RangPozicija INT,
        MajorTrofeji INT,
        Region ENUM("Evropa", "Severna Amerika", "Južna Amerika", "Azija", "Okeanija", "Afrika")
    ) ENGINE=INNODB;

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
        ON DELETE CASCADE,
        FOREIGN KEY (DrugiTim) REFERENCES Timovi(IDTima)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
        FOREIGN KEY (IDTurnira) REFERENCES Turniri(IDTurnira)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    ) ENGINE=INNODB;

    CREATE TABLE Igraci (
        IDIgraca INT PRIMARY KEY AUTO_INCREMENT,
        Ime VARCHAR(45) NOT NULL,
        Nadimak VARCHAR(45) NOT NULL,
        Prezime VARCHAR(45) NOT NULL,
        DatumRodjenja DATE,
        Nacionalnost VARCHAR(45),
        IDTima INT,
        Rejting DECIMAL(10, 2),
        MajorTrofeji INT,
        MajorMVP INT,
        FOREIGN KEY (IDTima) REFERENCES Timovi(IDTima)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    ) ENGINE=INNODB;

    CREATE TABLE Transferi (
        IDTransfera INT PRIMARY KEY AUTO_INCREMENT,
        IDIgraca INT NOT NULL,
        IDStarogTima INT NOT NULL,
        IDNovogTima INT NOT NULL,
        DatumTransfera DATETIME NOT NULL,
        FOREIGN KEY (IDIgraca) REFERENCES Igraci(IDIgraca)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
        FOREIGN KEY (IDStarogTima) REFERENCES Timovi(IDTima)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
        FOREIGN KEY (IDNovogTima) REFERENCES Timovi(IDTima)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    ) ENGINE=INNODB;

    CREATE TABLE Treneri (
        IDTrenera INT PRIMARY KEY AUTO_INCREMENT,
        Ime VARCHAR(45) NOT NULL,
        Nadimak VARCHAR(45) NOT NULL,
        Prezime VARCHAR(45) NOT NULL,
        IDTima INT,
        FOREIGN KEY (IDTima) REFERENCES Timovi(IDTima)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    ) ENGINE=INNODB;

    CREATE TABLE Vesti (
        IDVesti INT PRIMARY KEY AUTO_INCREMENT,
        Naslov VARCHAR(45),
        DatumObjavljivanja DATE,
        IDAutora INT,
        FOREIGN KEY (IDAutora) REFERENCES Autori(IDAutora)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    ) ENGINE=INNODB;

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
    ("TheMongolz", 20, 0, "Azija"),
    ("SAW Espors", 21, 0, "Evropa"),
    ("Aurora Gaming", 22, 0, "Evropa"),
    ("Apeks Esports", 23, 0, "Evropa"),
    ("FlyQuest", 24, 0, "Okeanija"),
    ("GamerLegion", 25, 0, "Evropa"),
    ("Legacy Esports", 26, 0, "Južna Amerika"),
    ("Monte Esports", 27, 0, "Evropa"),
    ("Lynn Vision", 28, 0, "Azija"),
    ("9 Pandas", 29, 0, "Evropa"),
    ("MIBR", 30, 0, "Južna Amerika");

    INSERT INTO Turniri(Ime, DatumPocetka, DatumZavrsetka, MestoIgranja, NagradniFond, VeciTurnir) VALUES
    ("ESL Pro League Season 19", '2024-04-23', '2024-05-12', "Malta", 750000, 1),
    ("Global Esports Tour Rio 2024", '2024-04-18', '2024-04-20', "Brazil", 200000, 1),
    ("Skyesports Masters 2024", '2024-04-08', '2024-04-14', "Onlajn", 345000, 0),
    ("IEM Chengdu 2024", '2024-04-08', '2024-04-14', "Kina", 250000, 1),
    ("PGL CS2 Major Copenhagen 2024", '2024-03-21', '2024-03-31', "Danska", 1250000, 1),
    ("BLAST Premier Spring Showdown 2024", '2024-03-06', '2024-03-10', "Onlajn", 135000, 0),
    ("IEM Katowice 2024", '2024-02-03', '2024-02-11', "Poljska", 1000000, 1),
    ("BLAST Premier Spring Groups 2024", '2024-01-22', '2024-01-28', "Danska", 190000, 1);

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
    ("Nicolai", "device", "Reedtz", '1995-09-08', "Danska", 11, '1.05', 4, 2),
    ("Martin", "stavn", "Lund", '2002-03-26', "Danska", 11, '1.08', 0, 0),
    ("Alexander", "br0", "Bro", '2002-05-04', "Danska", 11, '1.00', 0, 0),
    ("Jakob", "jabbi", "Nygaard", '2003-07-23', "Danska", 11, '1.04', 0, 0),
    ("Victor", "Staehr", "Staehr", '2004-07-19', "Danska", 11, '1.13', 0, 0),
    ("Marco", "Snappi", "Pfeiffer", '1990-06-09', "Danska", 12, '0.80', 0, 0),
    ("Muhammed", "BOROS", "Boros", '2004-05-04', "Jordan", NULL, '1.04', 0, 0),
    ("Emil", "Magisk", "Reif", '1998-03-05', "Danska", 12, '1.06', 3, 1),
    ("Pavle", "Maden", "Bošković", '1998-09-12', "Crna Gora", 12, '1.06', 0, 0),
    ("Álvaro", "SunPayus", "García", '1998-09-19', "Španija", 12, '1.09', 0, 0),
    ("Jonathan", "EliGE", "Jablonowski", '1997-07-16', "Sjedinjene Države", 13, '1.12', 0, 0),
    ("Johnny", "JT", "Theodosiou", '1999-04-09', "Južna Afrika", 13, '0.94', 0, 0),
    ("Ricky", "floppy", "Kemery", '1999-12-31', "Sjedinjene Države", 13, '0.92', 0, 0),
    ("Håkon", "hallzerk", "Fjærli", '2000-07-14', "Norveška", 13, '1.11', 0, 0),
    ("Michael", "Grim", "Wince", '2000-11-22', "Sjedinjene Države", 13, '1.00', 0, 0),
    ("Gabriel", "FalleN", "Toledo", '1991-05-30', "Brazil", 14, '1.07', 2, 0),
    ("Marcelo", "chelo", "Cespedes", '1998-06-08', "Brazil", 14, '1.01', 0, 0),
    ("Andrei", "arT", "Piovezan", '1996-03-27', "Brazil", NULL, '1.01', 0, 0),
    ("Yuri", "yuurih", "Santos", '1999-12-22', "Brazil", 14, '1.12', 0, 0),
    ("Kaike", "KSCERATO", "Cerato", '1999-09-12', "Brazil", 14, '1.15', 0, 0),
    ("Lukas", "gla1ve", "Rossander", '1995-06-07', "Danska", 15, '0.99', 4, 0),
    ("Krzysztof", "Goofy", "Górski", '2000-08-29', "Poljska", 15, '1.02', 0, 0),
    ("Paweł", "dycha", "Dycha", '1997-08-11', "Poljska", 15, '0.96', 0, 0),
    ("Olek", "hades", "Miskiewicz", '2000-01-01', "Poljska", 15, '1.09', 0, 0),
    ("Kacper", "Kylar", "Walukiewicz", '1999-09-22', "Poljska", 15, '1.13', 0, 0),
    ("Rodrigo", "biguzera", "Bittencourt", '1997-02-18', "Brazil", 16, '1.14', 0, 0),
    ("Lucas", "nqz", "Soares", '2005-01-18', "Brazil", 16, '1.10', 0, 0),
    ("Kaue", "kauez", "Kaschuk", '2003-05-8', "Brazil", 16, '1.03', 0, 0),
    ("Lucas", "lux", "Meneghini", '2002-04-18', "Brazil", 16, '1.11', 0, 0),
    ("Marcelo", "nyezin", "Ramos", '2003-09-18', "Brazil", 16, '0.98', 0, 0),
    ("Casper", "cadiaN", "Møller", '1995-06-26', "Danska", 17, '1.07', 0, 0),
    ("Keith", "NAF", "Marković", '1997-11-24', "Kanada", 17, '1.22', 0, 0),
    ("Russel", "Twistzz", "Van Dulken", '1999-11-14', "Kanada", 17, '1.20', 0, 0),
    ("Mareks", "YEKINDAR", "Gaļinskis", '1999-10-04', "Letonija", 17, '1.04', 0, 0),
    ("Felipe", "skullz", "Medeiros", '2002-04-20', "Brazil", 17, '1.09', 0, 0),
    ("Henrique", "HEN1", "Teles", '1995-07-14', "Brazil", 18, '1.09', 0, 0),
    ("João", "felps", "Vasconcellos", '1996-12-16', "Brazil", 18, '1.16', 0, 0),
    ("Vinicius", "VINI", "Figuerido", '1999-05-20', "Brazil", 18, '0.99', 0, 0),
    ("Lucas", "decenty", "Bacelar", '2004-03-01', "Brazil", 18, '1.08', 0, 0),
    ("Kaiky", "noway", "Santos", '2005-05-12', "Brazil", 18, '1.11', 0, 0),
    ("Magnus", "Nodios", "Olsen", '1999-05-07', "Danska", 19, '1.07', 0, 0),
    ("Patrick", "Patti", "Larsen", '1998-03-25', "Danska", 19, '1.04', 0, 0),
    ("Jonas", "Queenix", "Dideriksen", '1999-07-15', "Danska", 19, '1.06', 0, 0),
    ("Tobias", "kraghen", "Kragh Jensen", '2002-07-02', "Danska", 19, '1.11', 0, 0),
    ("Jason", "salazar", "Salazar", '2004-08-27', "Danska", 19, '1.02', 0, 0),
    ("Garidmagnai", "bLitz", "Byambarusen", '2001-06-26', "Mongolija", 20, '1.20', 0, 0),
    ("Sodbayar", "Techno", "Munkhbold", '2005-05-20', "Mongolija", 20, '1.07', 0, 0),
    ("Munkhbold", "Senzu", "Azbayar", '2006-08-11', "Mongolija", 20, '1.10', 0, 0),
    ("Ayush", "mzinho", "Batbold", '2007-06-28', "Mongolija", 20, '1.02', 0, 0),
    ("Usukhbayar", "910", "Banzragch", '2002-07-05', "Mongolija", 20, '1.11', 0, 0),
    ("Christopher", "MUTiRiS", "Fernandes", '1992-12-16', "Portugal", 21, '1.05', 0, 0),
    ("Ricardo", "roman", "Oliveira", '1992-05-14', "Portugal", 21, '0.99', 0, 0),
    ("João", "story", "Vieira", '2002-04-23', "Portugal", 21, '1.12', 0, 0),
    ("Michel", "ewjerkz", "Pinto", '2000-09-25', "Portugal", 21, '1.14', 0, 0),
    ("Rafael", "arrozdoce", "Wing", '2002-07-02', "Portugal", 21, '1.14', 0, 0),
    ("Viktor", "Lack1", "Boldyrev", '1999-08-20', "Kazakstan", 22, '1.12', 0, 0),
    ("Aleksandr", "KENSI", "Gurkin", '2001-11-13', "Rusija", 22, '1.23', 0, 0),
    ("Evgeny", "Norwi", "Ernolin", '2001-03-20', "Rusija", 22, '1.08', 0, 0),
    ("Denis", "deko", "Zhukov", '2001-07-10', "Rusija", 22, '1.22', 0, 0),
    ("Evgeny", "r3salt", "Frolov", '2005-02-14', "Rusija", 22, '1.20', 0, 0),
    ("Martin", "STYKO", "Styk", '1996-02-23', "Slovačka", 23, '1.00', 0, 0),
    ("Joakim", "jkaem", "Myrbostad", '1994-02-27', "Norveška", 23, '1.16', 0, 0),
    ("Tim", "nawwk", "Jonasson", '1997-10-30', "Švedska", 23, '1.10', 0, 0),
    ("Ådne", "sense", "Fredriksen", '2003-09-30', "Norveška", 23, '0.89', 0, 0),
    ("Aleksandar", "CacaNito", "Kjulukoski", '2000-11-09', "Severna Makedonija", 23, '1.02', 0, 0),
    ("Christopher", "dexter", "Nong", '1994-08-15', "Australija", 24, '1.19', 0, 0),
    ("Jay", "Liazz", "Tregillgas", '1997-08-30', "Australija", 24, '1.16', 0, 0),
    ("Alistair", "aliStair", "Johnston", '1998-04-07', "Australija", 24, '1.16', 0, 0),
    ("Joshua", "INS", "Potter", '1998-09-22', "Australija", 24, '1.30', 0, 0),
    ("Declan", "Vexite", "Portelli", '2004-09-05', "Australija", 24, '1.25', 0, 0),
    ("Janusz", "Snax", "Pogorzelski", '1993-07-05', "Poljska", 25, '1.04', 1, 0),
    ("Isak", "isak", "Fahlén", '2001-09-02', "Švedska", 25, '1.00', 0, 0),
    ("Sebastien", "volt", "Maloș", '2001-10-18', "Rumunija", 25, '1.06', 0, 0),
    ("Henrich", "sl3nd", "Helesi", '2004-08-31', "Mađarska", 25, '1.22', 0, 0),
    ("Andreas", "andu", "Maasing", '2005-01-22', "Estonija", 25, '1.21', 0, 0),
    ("Jake", "Stewie2k", "Yip", '1998-01-07', "Sjedinjene Države", 26, '0.95', 1, 0),
    ("Gabriel", "NEKIZ", "Schenato", '1995-11-17', "Brazil", 26, '0.84', 0, 0),
    ("Eduardo", "dumau", "Wolkmer", '2003-11-09', "Brazil", 26, '1.16', 0, 0),
    ("Bruno", "b4rtiN", "Câmara", '2002-01-11', "Brazil", 26, '1.03', 0, 0),
    ("Bruno", "latto", "Rebelatto", '2002-12-21', "Brazil", 26, '1.14', 0, 0),
    ("Volodymyr", "Woro2k", "Veletniuk", '2001-08-08', "Ukrajina", 27, '1.14', 0, 0),
    ("Sergiy", "DemQQ", "Demchenko", '2001-05-23', "Ukrajina", 27, '1.14', 0, 0),
    ("Szymon", "kRasnaL", "Mrozek", '2004-04-02', "Poljska", 27, '1.01', 0, 0),
    ("Gytis", "ryu", "Glušauskas", '2004-07-25', "Litvanija", 27, '0.94', 0, 0),
    ("Jack", "Gizmy", "von Spreckelsen", '2004-02-20', "Sjedinjeno Kraljevstvo", 27, '1.04', 0, 0),
    ("Zhe", "Westmelon", "Niu", '2001-02-04', "Kina", 28, '1.11', 0, 0),
    ("Sike", "z4kr", "Zhang", '2002-11-14', "Kina", 28, '1.00', 0, 0),
    ("Lizhi", "Starry", "Ye", '2005-01-03', "Kina", 28, '1.14', 0, 0),
    ("Dongkai", "Jee", "Ji", '2004-12-21', "Kina", 28, '1.10', 0, 0),
    ("Junjie", "EmiliaQAQ", "Tang", '2004-08-23', "Kina", 28, '1.01', 0, 0),
    ("Denis", "seized", "Kostin", '1994-09-09', "Rusija", 29, '0.93', 0, 0),
    ("Timur", "clax", "Sabirov", '2002-06-08', "Rusija", 29, '1.15', 0, 0),
    ("Artem", "iDISBALANCE", "Egorov", '1997-12-17', "Rusija", 29, '1.08', 0, 0),
    ("Aleksandr", "glowiing", "Matsievich", '1993-12-06', "Rusija", 29, '1.10', 0, 0),
    ("Daniil", "d1ledez", "Kustov", '2003-07-03', "Rusija", 29, '1.12', 0, 0),
    ("Raphael", "exit", "Lacerda", '1996-08-26', "Brazil", 30, '1.16', 0, 0),
    ("Rafael", "saffee", "Costa", '1994-12-19', "Brazil", 30, '1.15', 0, 0),
    ("André", "drop", "Abreu", '2004-01-15', "Brazil", 30, '1.13', 0, 0),
    ("Breno", "brnz4n", "Poletto", '2003-08-26', "Brazil", 30, '1.15', 0, 0),
    ("Felipe", "insani", "Yuji", '2004-04-25', "Brazil", 30, '1.23', 0, 0);
    
    INSERT INTO treneri(Ime, Nadimak, Prezime, IDTima) VALUES
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
    ("Erdenedalaji", "maaRaa", "Bajanbat", 20),
    ("Daniel", "NABOWOW", "Brito", 21),
    ("Stepan", "brain", "Sivoronov", 22),
    ("Torbjørn ", "mithR", "Nyborg", 23),
    ("Erdenetsogt", "erkaSt", "Gantulga", 24),
    ("Ashley", "ash", "Battye", 25),
    ("Olavo", "chucky", "Napoleão", 26),
    ("Sergey", "LMBT", "Bezhanov", 27),
    ("Yifei", "GUM", "Dai", 28),
    ("Andrey", "Xoma", "Mironenko", 29),
    ("Renato", "nak", "Nakano", 30);

    INSERT INTO mecevi(IDMeca, PrviTim, DrugiTim, IDTurnira, BrojMapa, Rezultat, DatumMeca) VALUES
    (1, 15, 25, 1, 2, '0:2', '2024-05-03'),
    (2, 13, 9, 1, 3, '2:1', '2024-05-02'),
    (3, 14, 17, 1, 3, '1:2', '2024-04-30'),
    (4, 12, 5, 1, 3, '1:2', '2024-04-27'),
    (5, 1, 7, 1, 3, '2:1', '2024-04-26'),
    (6, 10, 1, 1, 2, '0:2', '2024-04-25'),
    (7, 7, 18, 1, 3, '2:1', '2024-04-24'),
    (8, 11, 1, 1, 2, '2:0', '2024-04-24');

    INSERT INTO Autori(Ime, Nadimak, Prezime) VALUES
    ("Arron", "KingDempz", "Dempsey"),
    ("Luis", "MIRAA", "Mira"),
    ("Zvonimir", "Proffeseur", "Burazin"),
    ("Milan", "Striker", "Švejda"),
    ("Michal", "stich", "Malachowski"),
    ("Danish", "Nohte", "Allana"),
    ("Lucas", "LucasAM", "Aznar Miles"),
    ("Žan", "sumljiv", "Zupanič"),
    ("Harry", "NER0", "Richards");

    INSERT INTO Vesti(Naslov, DatumObjavljivanja, IDAutora) VALUES 
    ("Eternal Fire, fnatic, TYLOO eliminisani nakon 5. dana EPL-a", '2024-04-27', 1),
    ("G2 preživeo eliminacioni meč protiv Falcons-a u Malti", '2024-04-27', 5),
    ("Zajednica reaguje: Dust 2 menja Overpass u map pulu", '2024-04-27', 3),
    ("Endpoint potpisao CRUC1AL, cej0t", '2024-04-26', 7),
    ("FiReLEAGUE globalno finale pomereno za jun", '2024-04-26', 1),
    ("REZ propušta ESL pro ligu", '2024-04-25', 2),
    ("Astralis prolazi na EPL plejofove, FaZe protiv Virtus.pro u donjem breketu", '2024-04-25', 8),
    ("iNation najavio povratak sa novim timom", '2024-04-25', 4),
    ("3DMAX pobedio fnatic na putu ka EPL plejofu", '2024-04-25', 9),
    ("ZywOo napnuo Vitality na leđa do EPL plejof mesta", '2024-04-25', 7),
    ("Wonderful_Y dobio ban na FACEIT-u zbog hakovanja", '2024-04-25', 6),
    ("BLEED kvalifikovan za Thunderpick World Championship", '2024-04-22', 9),
    ("Virtus.pro otpustio ženski tim", '2024-04-21', 2),
    ("paiN diže GET 2024 trofej preko Imperial-a", '2024-04-21', 6),
    ("Sashi pobedio Cloud9 i Eternal Fire u šok kvalifikaciji za Esports World Cup", '2024-04-20', 1),
    ("FlyQuest kvalifikovan za EWC", '2024-04-20', 3),
    ("Imperial prošao na GET 2024 finale nakon 19 sati kantera", '2024-04-20', 4),
    ("coldzera napustio Legacy", '2024-04-19', 3),
    ("BLAST Premier finala će se igrati u BO5 formatu", '2024-04-18', 7),
    ("B1ad3: 'Nismo očekivali da ćemo osvojiti Major ovoliko brzo'", '2024-04-18', 8),
    ("OG promovisao Lamberta na poziciju glavnog trenera", '2024-04-18', 6);

    INSERT INTO Transferi (IDTransfera, IDIgraca, IDStarogTima, IDNovogTima, DatumTransfera) VALUES
    (1, 3, 3, 1, '2023-12-04'),
    (2, 46, 8, 10, '2024-04-15'),
    (3, 53, 27, 11, '2024-02-28'),
    (4, 45, 23, 9, '2023-12-21'),
    (5, 17, 8, 4, '2023-12-17'),
    (6, 59, 15, 12, '2023-12-16'),
    (7, 60, 15, 12, '2023-12-16'),
    (8, 81, 9, 17, '2023-12-06');