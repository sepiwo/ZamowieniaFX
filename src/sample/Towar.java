package sample;

/**
 * Created by Marysia on 2017-02-17.
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Towar {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty rozmiar;
    private final SimpleIntegerProperty cena;

    public Towar(Integer id, String nazwa, String rozmiar, Integer cena ) {
        this.id = new SimpleIntegerProperty(id);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.rozmiar = new SimpleStringProperty(rozmiar);
        this.cena = new SimpleIntegerProperty(cena);
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


    public String getNazwa() {
        return nazwa.get();
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }


    public String getrozmiar() {
        return rozmiar.get();
    }

    public SimpleStringProperty rozmiarProperty() {
        return rozmiar;
    }

    public void setrozmiar(String rozmiar) {
        this.rozmiar.set(rozmiar);
    }


    public Integer getcena()
    {
        return cena.get();
    }

    public SimpleIntegerProperty getCenaProperty()
    {
        return cena;
    }

    public void setcena(Integer cena)
    {
        this.cena.set(cena);
    }

    @Override
    public String toString() {
        return nazwa.get();
    }
}
