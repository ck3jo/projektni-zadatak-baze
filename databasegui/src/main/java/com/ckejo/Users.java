package com.ckejo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users implements Runnable
{
    @Override
    public void run()
    {
        String sqlQuery = "SELECT * FROM korisnici";
        String url = "jdbc:mysql://localhost:3306/db-link";
        String user = "root";
        String pass = "";

        System.out.println("Konekcija ka serveru...");

        try (Connection conn = DriverManager.getConnection(url, user, pass))
        {
            System.out.println("Uspesna konekcija ka bazi!\n");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);

            String insert = "INSERT INTO korisnici(korisnickoime, lozinka, email) VALUES(?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(insert);

            prep.setString(1, "Mika");
            prep.setString(2, "Mika123");
            prep.setString(3, "mika123@gmail.com");

            int insertedRows = prep.executeUpdate();

            if (insertedRows > 0) { System.out.println("Dodat je korisnik.\n"); }
            else { System.out.println("Nije dodat korisnik.\n");}

            String update = "UPDATE korisnici SET lozinka = ?, email = ? WHERE korisnickoime = ?";
            PreparedStatement prepUpdate = conn.prepareStatement(update);

            prepUpdate.setString(1, "Mika124");
            prepUpdate.setString(2, "mika124@hotmail.com");
            prepUpdate.setString(3, "Mika");

            int updatedRows = prepUpdate.executeUpdate();

            if (updatedRows > 0) { System.out.println("Promenjen je korisnik.\n"); }
            else { System.out.println("Nije promenjen korisnik.\n");}

            String delete = "DELETE FROM korisnici WHERE korisnickoime = ?";
            PreparedStatement prepDelete = conn.prepareStatement(delete);

            prepDelete.setString(1, "Mika");

            int deletedRows = prepDelete.executeUpdate();

            if (deletedRows > 0) { System.out.println("Izbrisan je korisnik.\n"); }
            else { System.out.println("Nije izbrisan korisnik.\n");}

            while (rs.next())
            {
                String un = rs.getString(2);
                String ps = rs.getString(3);
                String em = rs.getString(4);

                String res = "Korisnicko ime: " + un + "\n" +
                        "Sifra: " + ps + "\n" +
                        "Email: " + em + "\n";

                System.out.println(res);
            }
        }
        catch (SQLException se) { System.out.println("Uhvacena SQL greska: " + se.getMessage() + "\nOznaka greske: " + se.getErrorCode()); }
        catch (Exception e) { System.out.println("Uhvacena greska: " + e.getMessage()); }
    }
}
