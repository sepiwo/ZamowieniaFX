package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by sebastian on 29.12.16.
 */
public class Osoby {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty imie;
    private final SimpleStringProperty nazwisko;
    private final SimpleStringProperty stanowisko;
    private final SimpleStringProperty email;
    private final SimpleIntegerProperty telefon;
    //            Wy(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMEERIC, email CHAR,stanowisko CHAR)");

        public Osoby(Integer id, String imie, String nazwisko,Integer telefon, String email, String stanowisko) {
        this.id = new SimpleIntegerProperty(id);
        this.imie =  new SimpleStringProperty(imie);
        this.nazwisko =  new SimpleStringProperty(nazwisko);
        this.stanowisko = new SimpleStringProperty(stanowisko);
        this.email = new SimpleStringProperty(email);
        this.telefon = new SimpleIntegerProperty(telefon);
    }

    public Integer getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getImie() {
        return imie.get();
    }

    public SimpleStringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() {
        return nazwisko.get();
    }

    public String getImieNazwisko() {
        return imie.get() + " " + nazwisko.get();
    }

    public SimpleStringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getStanowisko() {
        return stanowisko.get();
    }

    public SimpleStringProperty stanowiskoProperty() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko.set(stanowisko);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Integer getTelefon() {
        return telefon.get();
    }

    public SimpleIntegerProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(Integer telefon) {
        this.telefon.set(telefon);
    }

    @Override
    public String toString() {
        return getImie() + " " + getNazwisko();
    }
}