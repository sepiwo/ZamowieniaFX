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
           // Wykonaj("CREATE TABLE IF NOT EXISTS OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMERIC, email CHAR,stanowisko CHAR)");
            //Wykonaj("CREATE TABLE IF NOT EXISTS TOWAR(id_towar INTEGER PRIMARY KEY AUTOINCREMENT, nazwa TEXT,rozmiar TEXT)");
            //Wykonaj("CREATE TABLE IF NOT EXISTS MAGAZYN(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMERIC, email CHAR,stanowisko CHAR)");
            Wykonaj("CREATE TABLE IF NOT EXISTS ZAMOWIENIA(id_zamowienia INTEGER PRIMARY KEY AUTOINCREMENT, Towar TEXT, Ilosc TEXT, id_osoby INTEGER)");
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

    public ResultSet getZamowienia(){
        String polecenie = "SELECT * FROM ZAMOWIENIA";
        ResultSet wynik = Pobierz(polecenie);
        return wynik;

    }
    public void dodajOsobe(NowaOsoba osoba) {

        //            Wykonaj("CREATE TABLE IF NOT EXIST OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMEERIC, email CHAR,stanowisko CHAR)");
        Wykonaj("INSERT INTO OSOBY VALUES (NULL, '" + osoba.imie + "',' " + osoba.nazwisko + "',' " + osoba.telefon + "',' " + osoba.email + "',' " + osoba.stanowisko + "')");

    }



    public void dodajZamowienie(NoweZamowienie zamowienie) {

        //            Wykonaj("CREATE TABLE IF NOT EXIST OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMEERIC, email CHAR,stanowisko C +HAR)");
        Wykonaj("INSERT INTO ZAMOWIENIA VALUES (NULL, '" + zamowienie.towar + "',' " + zamowienie.ilosc + "','" + zamowienie.id_osoby + "')");

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

    public void UsunZamowienie(int ID_zamowienie){
        try{
            Wykonaj("Delete from ZAMOWIENIA where id_zamowienia="+ID_zamowienie);
        }catch (
                Exception e
                ){
            System.out.println("Błąd podczas usuwania zamowienia z bazy!");
        }
    }
    public void aktualizujtabelezamowienia() {
        //Wykonaj();
    }
    public void aktualizujOsoba() {
        //Wykonaj();
    }

    public ResultSet getTowary() {
        String polecenie = "SELECT * FROM TOWAR";
        ResultSet wynik = Pobierz(polecenie);
        return wynik;
    }
    public void dodajTowar(NowyTowar towar) {

        //            Wykonaj("CREATE TABLE IF NOT EXISTS TOWAR(id_towar INTEGER PRIMARY KEY AUTOINCREMENT, nazwa TEXT,rozmiar TEXT");
        Wykonaj("INSERT INTO TOWAR VALUES (NULL, '" + towar.nazwa + "',' " + towar.rozmiar + "')");

    }

    public void UsunTowar(int ID_towar){
        try{
            Wykonaj("Delete from TOWAR where id_towar="+ID_towar);
        }catch (
                Exception e
                ){
            System.out.println("Błąd podczas usuwania towaru z bazy!");
        }
    }
    public void aktualizujTowar() {
        //Wykonaj();
    }


    public String imieNazwiskoPracownika(int id_osoby) throws SQLException {
        String polecenie = "SELECT * FROM OSOBY where id_osoby =" +id_osoby;
        ResultSet wynik = Pobierz(polecenie);

        return wynik.getString("Imie") + " " + wynik.getString("NAZWISKO");
    }
}






