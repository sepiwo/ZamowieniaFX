package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    final BazaDanych baza = new BazaDanych();

    @FXML
    private Label StatusBazy;
    @FXML
    private TableView<Towar> Tabela_towar;
    @FXML
    private TableColumn<Towar, Integer> columnTowaryid;
    @FXML
    private TableColumn<Towar, String> columnTowaryNazwa;
    @FXML
    private TableColumn<Towar, String> columnTowaryRozmiar;
    @FXML
    private TableColumn<Towar, Integer> columnTowaryCena;
    @FXML
    private Button buttonDodajNowyTowar;
    @FXML
    private TextField textNazwa;
    @FXML
    private TextField textRozmiar;
    @FXML
    private TextField textCena;
    @FXML
    private Label labeIDTowar;
    @FXML
    private Button buttonZapiszZmianyTowar;
    @FXML
    private final ObservableList<Towar> observableListtowar = FXCollections.observableArrayList();
    @FXML
    private final ObservableList<Zamowienie> observableListZamowienie = FXCollections.observableArrayList();
    @FXML
    protected TableView<Osoby> tableOsoby;
    @FXML
    protected TableView<Zamowienie> tableZamowienia;
    @FXML
    private TableColumn<Osoby, Integer> columnOsobyid;
    @FXML
    private TableColumn<Osoby, String> columnOsobyImie;
    @FXML
    private TableColumn<Osoby, String> columnOsobyNazwisko;
    @FXML
    private Button buttonDodajNowaOsoba;
    @FXML
    private TextField textImie;
    @FXML
    private TextField textNazwisko;
    @FXML
    private TextField textStanowisko;
    @FXML
    private TextField textTelefon;
    @FXML
    private TextField textEmail;
    @FXML
    private Label labeIDOsoba;
    @FXML
   // private TextField textTowar;
    private ComboBox<Towar> towarComboBox;
    @FXML
    private ComboBox<String> pracownicy;
    @FXML
    private TextField textIlosc;
    @FXML
    private Button buttonZapiszZmianyOsoba;
    @FXML
    private final ObservableList<Osoby> observableListosoby = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Zamowienie, Integer> columnZamowieniaID;
    @FXML
    private TableColumn<Zamowienie, String> columnZamowieniaTowar;
    @FXML
    private TableColumn<Zamowienie, String> columnZamowieniaIlosc;
    @FXML
    private TableColumn<Zamowienie, String> columnZamowieniaPracownik;
    @FXML
    private TableColumn<Zamowienie, Integer> columnZamowieniaKoszt;
    @FXML
    private ComboBox<Osoby> pracownicyComboBox;
    @FXML
    private Button buttonWyczyscOsoba;


    @FXML
    public void initialize() {
        try {
            baza.PolaczBaze();
        } catch (Exception e) {
            zmienStatus(false);
        }
        zmienStatus(true);
        columnOsobyid.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnOsobyImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        columnOsobyNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());

        tableOsoby.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> PokazOsobe(newValue));


        aktualizujTableOsoby();

        columnTowaryid.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnTowaryNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        columnTowaryRozmiar.setCellValueFactory(cellData -> cellData.getValue().rozmiarProperty());
        columnTowaryCena.setCellValueFactory(cellData -> cellData.getValue().getCenaProperty().asObject());

        Tabela_towar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> PokazTowar(newValue));


        aktualizujTableTowar();

        columnZamowieniaID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnZamowieniaTowar.setCellValueFactory(cellData -> cellData.getValue().NazwaTowaruProperty());
        columnZamowieniaIlosc.setCellValueFactory(cellData -> cellData.getValue().IloscProperty());
        columnZamowieniaPracownik.setCellValueFactory(cellData -> cellData.getValue().PracownikProperty());
        columnZamowieniaKoszt.setCellValueFactory(c -> c.getValue().KosztProperty().asObject());

        aktualizujComboBoxy();

        aktualizujTabeleZamowienia();

        tableZamowienia.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> PokazZamowienia(newValue));

    }




    @FXML
    void zmienStatus(boolean status) {
        if (status) {
            StatusBazy.setText("Połączono");
        } else {
            StatusBazy.setText("Błąd");
        }
    }

    @FXML
    void WyczyscOsobaButtonClicked(ActionEvent event) {
        WyczyscOsoba();
    }


    @FXML
    void DodajNowaOsoba(ActionEvent event) {

        boolean isTelefonCorrect = false;
        boolean isTelefonHasCorrectCount = false;

       if (textImie.getText().trim().isEmpty() ||textImie.getText() == null  ||
                textNazwisko.getText().trim().isEmpty() || textNazwisko.getText() == null ||
                textTelefon.getText().trim().isEmpty() || textTelefon.getText() == null ||
                textEmail.getText().trim().isEmpty() || textEmail.getText() == null ||
                textStanowisko.getText().trim().isEmpty() || textStanowisko.getText() == null)
            {
                 new Alert(Alert.AlertType.ERROR, "Uzupełnij wszystkie pola").showAndWait();
            }
                else {
            if (textTelefon.getText().length() == 9) {
                isTelefonHasCorrectCount = true;
            }

            try {
                int foo = Integer.parseInt(textTelefon.getText());
                isTelefonCorrect = true;
            } catch (Exception e) {
            }

            if (!isTelefonHasCorrectCount) {
                new Alert(Alert.AlertType.ERROR, "Niepoprawna ilość cyfr w numerze telefonu").showAndWait();

            } else if (!isTelefonCorrect) {
                new Alert(Alert.AlertType.ERROR, "Numer telefonu musi składać się z cyfr").showAndWait();
            } else {
                System.out.println("Dodaje nowa osobe do bazy...");
                NowaOsoba osoba = new NowaOsoba(textImie.getText(), textNazwisko.getText(), textTelefon.getText(), textEmail.getText(), textStanowisko.getText());
                baza.dodajOsobe(osoba);
                WyczyscOsoba();
                aktualizujTableOsoby();
                aktualizujComboBoxy();

            }
        }
    }
    private void aktualizujComboBoxy() {
//        final ObservableList<String> pracownicy = FXCollections.observableArrayList();
//        for (Osoby osoba : getOsobydane()) {
//            pracownicy.add(osoba.toString());
//        }
        pracownicyComboBox.setItems(getOsobydane());
        towarComboBox.setItems(getTowardane());
    }


    void PokazOsobe(Osoby osoba){
        textImie.setText(osoba.getImie());
        textNazwisko.setText(osoba.getNazwisko());
        textEmail.setText(osoba.getEmail());
        textTelefon.setText((osoba.getTelefon().toString()));
        textStanowisko.setText(osoba.getStanowisko());
        labeIDOsoba.setText(osoba.getId().toString());


    }
    void PokazTowar(Towar towary){
     textNazwa.setText(towary.getNazwa());
     textRozmiar.setText(towary.getrozmiar());
     textCena.setText(towary.getcena().toString());

    }

    @FXML
    void UsunOsobeButtonClicked(ActionEvent event){
        Integer wybranaOsobaID = tableOsoby.getSelectionModel().getSelectedItem().getId();
        baza.UsunOsobe(wybranaOsobaID);
        WyczyscOsoba();
        aktualizujTableOsoby();

    }

    void WyczyscOsoba() {
        textImie.clear();
        textNazwisko.clear();
        textEmail.clear();
        textTelefon.clear();
        textStanowisko.clear();
        labeIDOsoba.setText("---");
        System.out.println("Czyszczenie TextField w tabeli osoby");
    }

    void aktualizujTableOsoby() {


        try{
        ResultSet resultOsoby = baza.getOsoby();
        observableListosoby.clear();
            System.out.println("Aktualizowanie tabeli Osoby...");

            while (resultOsoby.next()) {
            System.out.println("Wynik z bazy\n " + resultOsoby.getInt(1) + resultOsoby.getString(2) + resultOsoby.getString(3) + resultOsoby.getString(4) + resultOsoby.getString(5) + resultOsoby.getString(6));

            observableListosoby.add(new Osoby(resultOsoby.getInt(1), resultOsoby.getString(2), resultOsoby.getString(3), resultOsoby.getInt(4), resultOsoby.getString(5), resultOsoby.getString(6)));


            }

            tableOsoby.setItems(getOsobydane());

        }catch(Exception e){
            System.out.println(e);

            }



        }

    public ObservableList<Osoby> getOsobydane() {
        return observableListosoby;
    }

    @FXML
    void zapiszZmianyOsoba(ActionEvent event){
        Integer wybranaOsobaID = tableOsoby.getSelectionModel().getSelectedItem().getId();
        baza.aktualizujOsoba();
        aktualizujTableOsoby();

    }
    @FXML
    void WyczyscTowarButtonClicked(ActionEvent actionEvent) {
        WyczyscTowar();
    }

    @FXML
    void DodajNowyTowar(ActionEvent actionEvent) {

        if (textNazwa.getText().trim().isEmpty() || textNazwa.getText() == null || textRozmiar.getText().trim().isEmpty() || textRozmiar.getText() == null ||
                textCena.getText() == null || textCena.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Uzupełnij wszystkie pola").showAndWait();

        }
        else
        {
            boolean isIloscCorrect = false;
            boolean isCenaCorrect = false;

            try {
                int foo = Integer.parseInt(textRozmiar.getText());
                isIloscCorrect = true;

            }
            catch (Exception e)
            {}

            try {
                int foo2 = Integer.parseInt(textCena.getText());
                isCenaCorrect = true;

            }
            catch (Exception e)
            {}



            if (!isCenaCorrect)
            {
                new Alert(Alert.AlertType.ERROR, "Niepoprawny format ceny").showAndWait();
            }
            else if (!isIloscCorrect)
            {
                new Alert(Alert.AlertType.ERROR, "Niepoprawny format ilosci").showAndWait();
            }
            else
            {


                System.out.println("Dodaje nowy towar do bazy...");
                NowyTowar towar = new NowyTowar(textNazwa.getText(), textRozmiar.getText(), textCena.getText());
                baza.dodajTowar(towar);
                WyczyscTowar();
                aktualizujTableTowar();
            }

        }
    }

    @FXML
    void UsunTowarButtonClicked(ActionEvent actionEvent) {
        Integer wybranaTowarID = Tabela_towar.getSelectionModel().getSelectedItem().getId();
        baza.UsunTowar(wybranaTowarID);
        WyczyscTowar();
        aktualizujTableTowar();
    }

    void WyczyscTowar() {
        textNazwa.clear();
        textRozmiar.clear();
        textCena.clear();
        labeIDTowar.setText("---");
        System.out.println("Czyszczenie TextField w tabeli towar");
    }

    void aktualizujTableTowar() {

        try{
            ResultSet resultTowar = baza.getTowary();
            observableListtowar.clear();
            System.out.println("Aktualizowanie tabeli Towar...");

            while (resultTowar.next()) {
                System.out.println("Wynik z bazy\n " + resultTowar.getInt(1) +
                        resultTowar.getString(2) +
                        resultTowar.getString(3) +
                        resultTowar.getString(4));


                observableListtowar.add(new Towar(resultTowar.getInt(1),
                        resultTowar.getString(2), resultTowar.getString(3), resultTowar.getInt(4)));


            }

            Tabela_towar.setItems(getTowardane());

        }catch(Exception e){
            System.out.println(e);
            System.out.println(e);

        }



    }

    public ObservableList<Towar> getTowardane() {
        return observableListtowar;
    }

    public ObservableList<Zamowienie> getZamowieniedane() {
        return observableListZamowienie;
    }
    @FXML
    void zapiszZmianyTowar(ActionEvent actionEvent) {
        Integer wybranaTowarID = Tabela_towar.getSelectionModel().getSelectedItem().getId();
        baza.aktualizujTowar();
        aktualizujTableTowar();
    }

    void WyczyscZamowienie() {
       //textTowar.clear();
        textIlosc.clear();
        System.out.println("Czyszczenie TextField w tabeli ZAMOWIENIA");
    }

    @FXML
    void DodajNoweZamowienie(ActionEvent event) {
        if (textIlosc.getText().trim().isEmpty() ||
                textIlosc.getText() == null ||
               // textTowar.getText().trim().isEmpty() ||
               // textTowar.getText() == null ||
                towarComboBox.getSelectionModel().getSelectedItem() == null ||
                pracownicyComboBox.getSelectionModel().getSelectedItem() == null)
        {
            new Alert(Alert.AlertType.ERROR, "Uzueplnij wszystkie pola przed dodaniem zamowienia!").showAndWait();

        }
        else {
            System.out.println("Dodaje nowe zamowienie do bazy...");
            NoweZamowienie zamowienie = new NoweZamowienie("",
                                                            textIlosc.getText(),
                                                            pracownicyComboBox.getSelectionModel().getSelectedItem(),
                                                            towarComboBox.getSelectionModel().getSelectedItem(),
                                                            Integer.parseInt(textIlosc.getText())* towarComboBox.getSelectionModel().getSelectedItem().getcena());
            baza.dodajZamowienie(zamowienie);
            WyczyscZamowienie();
            aktualizujTabeleZamowienia();
            aktualizujTableTowar();

        }
    }

    private void aktualizujTabeleZamowienia() {
        try{
            ResultSet resultZamowienia = baza.getZamowienia();

            observableListZamowienie.clear();
            System.out.println("Aktualizowanie tabeli Zamowienie...");

            while (resultZamowienia.next()) {
                System.out.println("Wynik z bazy\n " + resultZamowienia.getInt(1) + resultZamowienia.getString(2) + resultZamowienia.getString(3));

                observableListZamowienie.add(
                        new Zamowienie(
                                resultZamowienia.getInt(1),
                                resultZamowienia.getString(2),
                                resultZamowienia.getString(3),
                               // baza.imieNazwiskoPracownika(resultZamowienia.getInt(4)).toString()
                                (observableListosoby.stream()
                                    .filter(p -> {
                                        try {
                                            return p.getId() == resultZamowienia.getInt(4);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                            return false;
                                        }
                                    })
                                    .findFirst()
                                    .get().getImieNazwisko()),

                                    observableListtowar.stream()
                                            .filter(p -> {
                                                try {
                                                    return p.getId() == resultZamowienia.getInt(5);
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                    return false;
                                                }
                                            })
                                            .findFirst()
                                            .get().getNazwa(),resultZamowienia.getInt(6)));


            }

            tableZamowienia.setItems(getZamowieniedane());

        }catch(Exception e){
            System.out.println(e);

        }
    }


    void PokazZamowienia(Zamowienie zamowienie){
        //textTowar.setText(zamowienie.getTowar());
        textIlosc.setText(zamowienie.getIlosc());
    }

    @FXML
    void UsunZamowienieButtonClicked(ActionEvent event){
        Integer wybraneZamowienieID = tableZamowienia.getSelectionModel().getSelectedItem().getId();
        baza.UsunZamowienie(wybraneZamowienieID);
        System.out.println("Usunięto zamowienie z bazy danych");
        WyczyscZamowienie();
        aktualizujTabeleZamowienia();
    }

    public void EdytujZamowienie(ActionEvent actionEvent) {
    }
}


