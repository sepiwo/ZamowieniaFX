package sample;

/**
 * Created by Moniczka on 21.02.2017.
 */
public class NoweZamowienie {
    public String towar;
    public String ilosc;
    public Integer id_osoby;
    private Osoby selectedItem;
    public Integer id_towaru;
    public Integer koszt;

    public NoweZamowienie(String towar, String ilosc, Osoby selectedItem, Towar selectedTowar, Integer koszt) {
        this.towar = towar;
        this.ilosc = ilosc;
        this.selectedItem = selectedItem;
        this.id_osoby = selectedItem.getId();
        this.id_towaru = selectedTowar.getId();
        this.koszt = koszt;
    }
}