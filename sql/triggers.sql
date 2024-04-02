-- 1. triger: Proveriti da tim u tabeli 'Mecevi' ne igra protiv samog sebe.
CREATE TRIGGER NeponavljajuciTimovi
BEFORE INSERT ON Mecevi
FOR EACH ROW
BEGIN
    IF NEW.PrviTim = NEW.DrugiTim THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'PrviTim and DrugiTim cannot have the same values';
    END IF;
END;

-- 2. triger: Na promeni igraca, upisati transfer u tabelu 'Transferi'.
CREATE TRIGGER AutomatskiTransfer
AFTER UPDATE ON Igraci
FOR EACH ROW
BEGIN
    IF NEW.IDTima <> OLD.IDTima THEN
        INSERT INTO Transferi (IDIgraca, IDStarogTima, IDNovogTima, DatumTransfera)
        VALUES (NEW.IDIgraca, OLD.IDTima, NEW.IDTima, CURRENT_DATE());
    END IF;
END;