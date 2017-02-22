package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * Created by Moniczka on 21.02.2017.
 */
public class Zamowienie {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty towar;
    private final SimpleStringProperty ilosc;
    private final SimpleStringProperty pracownik;
    private final SimpleStringProperty nazwaTowaru;
    private final SimpleIntegerProperty koszt;

    //            Wy(id_osoby INTEGER PRIMARY KEY AUTOINCREMENT, Imie TEXT,NAZWISKO CHAR, telefon NUMEERIC, email CHAR,stanowisko CHAR)");

    public Zamowienie(Integer id, String towar, String ilosc, String pracownik, String nazwaTowaru, Integer koszt) {
        this.id = new SimpleIntegerProperty(id);
        this.towar = new SimpleStringProperty(towar);
        this.ilosc = new SimpleStringProperty(ilosc);
        this.pracownik = new SimpleStringProperty(pracownik);
        this.nazwaTowaru = new SimpleStringProperty(nazwaTowaru);
        this.koszt = new SimpleIntegerProperty(koszt);
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

    public String getTowar() {return towar.get();}

    public SimpleStringProperty towarProperty() {
        return towar;
    }

    public void setTowar(String towar) {
        this.towar.set(towar);
    }

    public String getIlosc() {
        return ilosc.get();
    }

    public SimpleStringProperty IloscProperty() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {this.ilosc.set(ilosc);}



    public SimpleStringProperty PracownikProperty()
    {
        return pracownik;
    }

    public SimpleStringProperty NazwaTowaruProperty()
    {
        return nazwaTowaru;
    }
    public SimpleIntegerProperty KosztProperty()
    {
        return koszt;
    }

}
