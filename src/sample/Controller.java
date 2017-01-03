package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;

/*
//Controller odpowiada za obsługę interfejsu aplikacji wczytuje elementy z pliku fxml ( te z @FXML ) i dodaje im akcje
*/
public class Controller {
    final BazaDanych baza = new BazaDanych();

    @FXML
    private Label StatusBazy;

    //Wyswietla tabele z osobami:
    @FXML
    private TableView<?> Tabela_zamowienia;
    @FXML
    private TableView<Osoby> tableOsoby;
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
    private Button buttonZapiszZmianyOsoba;

    //Dopasowanie modelu do tabeli model musi byc zapisany w osobnej klasie jak np w Osoby.java
    @FXML
    private final ObservableList<Osoby> observableListosoby = FXCollections.observableArrayList();








    @FXML
    private Button buttonWyczyscOsoba;
// Inicjalizacja tutaj kontroler wykonuje funkcje poczatkowe
    @FXML
    public void initialize() {
        try {
            baza.PolaczBaze();
        } catch (Exception e) {
            zmienStatus(false);
        }
        zmienStatus(true);

        //wyrazenia lambda generujace puste komorki
        columnOsobyid.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnOsobyImie.setCellValueFactory(cellData -> cellData.getValue().imieProperty());
        columnOsobyNazwisko.setCellValueFactory(cellData -> cellData.getValue().nazwiskoProperty());

        tableOsoby.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> PokazOsobe(newValue));


        aktualizujTableOsoby();

    }



    // Ustawia label informujacy ze baza jest podlaczona
    @FXML
    void zmienStatus(boolean status) {
        if (status) {
            StatusBazy.setText("Połączono");
        } else {
            StatusBazy.setText("Błąd");
        }
    }
    //wyczysczenie pol w celu dodania nowej osoby
    @FXML
    void WyczyscOsobaButtonClicked(ActionEvent event) {
        WyczyscOsoba();
    }

    //Reakcja na przycisk dodaj nowa osobe
    @FXML
    void DodajNowaOsoba(ActionEvent event) {
        System.out.println("Dodaje nowa osobe do bazy...");
        NowaOsoba osoba = new NowaOsoba(textImie.getText(), textNazwisko.getText(), textTelefon.getText(), textEmail.getText(), textStanowisko.getText());
        baza.dodajOsobe(osoba);
        WyczyscOsoba();
        aktualizujTableOsoby();

    }
    //wyswietlanie danych dla kliknietrj osoby ns liscie
    void PokazOsobe(Osoby osoba){
        textImie.setText(osoba.getImie());
        textNazwisko.setText(osoba.getNazwisko());
        textEmail.setText(osoba.getEmail());
        textTelefon.setText(osoba.getTelefon());
        textStanowisko.setText(osoba.getStanowisko());
        labeIDOsoba.setText(osoba.getId().toString());


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
    // aktualizowanie tabeli po zmianie wartosci w bazie danych
    void aktualizujTableOsoby() {


        try{
        ResultSet resultOsoby = baza.getOsoby();
        observableListosoby.clear();
            System.out.println("Aktualizowanie tabeli Osoby...");

            while (resultOsoby.next()) {
            System.out.println("Wynik z bazy\n " + resultOsoby.getInt(1) + resultOsoby.getString(2) + resultOsoby.getString(3) + resultOsoby.getString(4) + resultOsoby.getString(5) + resultOsoby.getString(6));

            observableListosoby.add(new Osoby(resultOsoby.getInt(1), resultOsoby.getString(2), resultOsoby.getString(3), resultOsoby.getString(4), resultOsoby.getString(5), resultOsoby.getString(6)));


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
        Osoby zmienionaOsoba = tableOsoby.getSelectionModel().getSelectedItem();
        zmienionaOsoba.setEmail(textEmail.getText());
        zmienionaOsoba.setImie(textImie.getText());
        zmienionaOsoba.setNazwisko(textNazwisko.getText());
        zmienionaOsoba.setNazwisko(textNazwisko.getText());
        zmienionaOsoba.setTelefon((textTelefon.getText()));
        baza.aktualizujOsoba(zmienionaOsoba);
        aktualizujTableOsoby();

    }


    }


