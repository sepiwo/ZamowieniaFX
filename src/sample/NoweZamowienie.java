package sample;

/**
 * Created by Moniczka on 21.02.2017.
 */
public class NoweZamowienie {
    public String towar;
    public String ilosc;
    public Integer id_osoby;
    private Osoby selectedItem;


    public NoweZamowienie(String towar, String ilosc, Osoby selectedItem) {
        this.towar = towar;
        this.ilosc = ilosc;
        this.selectedItem = selectedItem;
        this.id_osoby = selectedItem.getId();
    }
}