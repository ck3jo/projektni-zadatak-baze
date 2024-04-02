-- 1. funkcija: Vraca prosecne godine svih igraca po timu.
CREATE FUNCTION ProsecneGodine()
RETURNS TABLE
AS
RETURN (
    SELECT IDTima,
           AVG(YEAR(CURRENT_DATE()) - YEAR(DatumRodjenja)) AS prosek
    FROM Igraci
    GROUP BY IDTima
);
SELECT * FROM ProsecneGodine();