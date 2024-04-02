-- 1. upit: Svi timovi gde su prosecne godine igraca iznad 25.
SELECT * FROM timovi WHERE ProsecneGodine > 25;

-- 2. upit: Svi igraci koji trenutno igraju za neki tim.
SELECT * FROM igraci WHERE IDTima != 0;

-- 3. upit: Svi igraci koji trenutno ne igraju ni za jedan tim.
SELECT * FROM igraci WHERE IDTima = 0;

-- 4. upit: Svi treneri koji trenutno treniraju neki tim.
SELECT * FROM treneri WHERE IDTima != 0;

-- 5. upit: Svi treneri koji trenutno ne treniraju ni jedan tim.
SELECT * FROM treneri WHERE IDTima = 0;

-- 6. upit: Svi veliki turniri.
SELECT * FROM turniri WHERE VeciTurnir = 1;

-- 7. upit: Svi turniri sa nagradnim fondom preko 50 hiljada.
SELECT * FROM turniri WHERE NagradniFond > 50000;

-- 8. upit: Svi turniri sa vise od 8 timova.
SELECT * FROM turniri WHERE BrojTimova > 8;

-- 9. upit: Svi online turniri.
SELECT * FROM turniri WHERE MestoIgranja = "Online";

-- 10. upit: Svi LAN turniri.
SELECT * FROM turniri WHERE MestoIgranja != "Online";

-- 11. upit: Svi mecevi gde igra FaZe Clan.
SELECT * FROM mecevi WHERE PrviTim = (SELECT IDTima FROM timovi WHERE ImeTima = "FaZe Clan") OR DrugiTim = (SELECT IDTima FROM timovi WHERE ImeTima = "FaZe Clan");

-- 12. upit: Svi mecevi sa Thunderpick World Championship 2023.
SELECT * FROM mecevi WHERE IDTurnira = (SELECT IDTurnira FROM turniri WHERE ImeTurnira = "Thunderpick World Championship 2023");

-- 13. upit: Svi mecevi koji su trajali 3 ili vise mapa.
SELECT * FROM mecevi WHERE BrojMapa >= 3;

-- 14. upit: Svi transferi frozen-a.
SELECT * FROM transferi WHERE IDIgraca = (SELECT IDIgraca FROM igraci WHERE Nadimak = "frozen");

-- 15. upit: Svi transferi iz FaZe Clan-a.
SELECT * FROM transferi WHERE IDStarogTima = (SELECT IDTima FROM timovi WHERE ImeTima = "FaZe Clan");

-- 16. upit: Svi transferi u Vitality.
SELECT * FROM transferi WHERE IDNovogTima = (SELECT IDTima FROM timovi WHERE ImeTima = "Team Vitality");

-- 17. upit: Svi timovi sa svim podacima o igracima.
SELECT * FROM igraci CROSS JOIN timovi ON timovi.IDTima = igraci.IDTima;

-- 18. upit: Svi artikli iz 2023.
SELECT * FROM artikli WHERE YEAR(DatumObjavljivanja) = 2023;

-- 19. upit: Svi autori ciji nadimak pocinje sa S.
SELECT * FROM autori WHERE Nadimak LIKE "S%";

-- 20. upit: Najmladji G2 igrac.
SELECT * FROM igraci WHERE IDTima = (SELECT IDTima FROM timovi WHERE ImeTima = "G2 Esports");