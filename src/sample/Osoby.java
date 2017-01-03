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
    private final SimpleStringProperty telefon;

        public Osoby(Integer id, String imie, String nazwisko,String telefon, String email, String stanowisko) {
        this.id = new SimpleIntegerProperty(id);
        this.imie =  new SimpleStringProperty(imie);
        this.nazwisko =  new SimpleStringProperty(nazwisko);
        this.stanowisko = new SimpleStringProperty(stanowisko);
        this.email = new SimpleStringProperty(email);
            this.telefon = new SimpleStringProperty(telefon);

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

    public String getTelefon() {
        return telefon.get();
    }

    public SimpleStringProperty telefonProperty() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }
}