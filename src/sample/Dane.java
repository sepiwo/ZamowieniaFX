package sample;

import java.sql.ResultSet;

/**
 * Created by sebastian on 27.12.16.
 */
public class Dane{

    BazaDanych baza = new BazaDanych();

    public void init(){
        baza.PolaczBaze();
        baza.utworzTabele();
    }

    public ResultSet getOsoby(){
        String polecenie="SELECT * FROM OSOBY";
        ResultSet result = baza.Pobierz(polecenie);
        return result;
    }

    public void dodajOsobe(NowaOsoba osoba){

        //            Wykonaj("CREATE TABLE IF NOT EXIST OSOBY(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMEERIC, email CHAR,stanowisko CHAR)");
        baza.Wykonaj("INSERT INTO OSOBY VALUES (NULL, '"+ osoba.imie +"',' "+osoba.nazwisko+"',' "+osoba.telefon+"',' "+osoba.email+"',' "+osoba.stanowisko+"')");

    }
}
