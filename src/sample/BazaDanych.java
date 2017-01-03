package sample;

import java.io.File;
import java.sql.*;

/**
 * Created by sebastian on 26.12.16.
 */
public class BazaDanych {
    String path = System.getProperty("user.home") + File.separator + "ZamowieniaFX.db";
    Connection polaczenie = null;
    Statement stmt;
    ResultSet wynik;

    public void PolaczBaze() {
        try {
            polaczenie = DriverManager.getConnection("jdbc:sqlite:" + path);
            stmt = polaczenie.createStatement();
            utworzTabele();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }
        System.out.println("Wczytano bazę danych.");
    }

    public void utworzTabele() {
        try {
            Wykonaj("CREATE TABLE IF NOT EXISTS OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon text, email CHAR,stanowisko CHAR)");
            //Wykonaj("CREATE TABLE IF NOT EXISTS TOWAR(id_towar INTEGER PRIMARY KEY AUTOINCREMENT, nazwa TEXT,rozmiar TEXT");
            //Wykonaj("CREATE TABLE IF NOT EXISTS MAGAZYN(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMERIC, email CHAR,stanowisko CHAR)");
            //Wykonaj("CREATE TABLE IF NOT EXISTS ZAMOWIENIA(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMERIC, email CHAR,stanowisko CHAR)");
            //Wykonaj("CREATE TABLE IF NOT EXISTS PRZYPOMNIENIA(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMERIC, email CHAR,stanowisko CHAR)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("stworzono tabele");

    }

    public void Wykonaj(String polecenie) {
        try {
            stmt.executeUpdate(polecenie);
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ResultSet Pobierz(String polecenie) {
        try {

            wynik = stmt.executeQuery(polecenie);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        return wynik;
    }

    public void zamknijPolaczenie() {
        try {
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Połączenie z bazą zostało zakończone");
    }

    public ResultSet getOsoby() {
        String polecenie = "SELECT * FROM OSOBY";
        ResultSet wynik = Pobierz(polecenie);
        return wynik;
    }

    public void dodajOsobe(NowaOsoba osoba) {

        //            Wykonaj("CREATE TABLE IF NOT EXIST OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie text,NAZWISKO text, telefon NUMEERIC, email CHAR,stanowisko CHAR)");
        Wykonaj("INSERT INTO OSOBY VALUES (NULL, '" + osoba.imie + "',' " + osoba.nazwisko + "',' " + osoba.telefon + "',' " + osoba.email + "',' " + osoba.stanowisko + "')");

    }

    public void UsunOsobe(int ID_osoba){
        try{
        Wykonaj("Delete from OSOBY where id_osoby="+ID_osoba);
    }catch (
            Exception e
                ){
        System.out.println("Błąd podczas usuwania osoby z bazy!");
        }
    }

    public void aktualizujOsoba(Osoby osoba) {
        Wykonaj("update osoby set Imie='"+osoba.getImie()+"',Nazwisko='"+osoba.getNazwisko()+"',telefon='"+osoba.getTelefon()+"',email='"+osoba.getEmail()+"',stanowisko='"+osoba.getStanowisko()+"' where id_osoby="+osoba.getId());
    }


}





