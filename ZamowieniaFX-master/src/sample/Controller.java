package sample;

import com.sun.corba.se.impl.orb.ParserTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.util.PriorityQueue;

public class Controller {
    final BazaDanych baza = new BazaDanych();

    @FXML
    private Label StatusBazy;
    @FXML
    private TableView<Towar> tableTowar;
    @FXML
    private TableColumn<Towar, Integer> columnTowarid;
    @FXML
    private TableColumn<Towar, String> columnTowarNazwa;
    @FXML
    private TableColumn<Towar, String> columnTowarRozmiar;
    @FXML
    private Button buttonDodajNowyTowar;
    @FXML
    private TextField textNazwa;
    @FXML
    private TextField textRozmiar;
    @FXML
    private Label labeIDTowar;
    @FXML
    private Button buttonZapiszZmianyTowar;
    @FXML
    private final ObservableList<Towar> observableListtowar = FXCollections.observableArrayList();
    @FXML
    protected TableView<Osoby> tableOsoby;
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
    @FXML
    private final ObservableList<Osoby> observableListosoby = FXCollections.observableArrayList();








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
        columnTowarid.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        columnTowarNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        columnTowarRozmiar.setCellValueFactory(cellData -> cellData.getValue().rozmiarProperty());

        tableTowar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> PokazTowar(newValue));


        aktualizujTableTowar();
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
        System.out.println("Dodaje nowa osobe do bazy...");
        NowaOsoba osoba = new NowaOsoba(textImie.getText(), textTelefon.getText(), textNazwisko.getText(), textEmail.getText(), textStanowisko.getText());
        baza.dodajOsobe(osoba);
        WyczyscOsoba();
        aktualizujTableOsoby();

    }
    void PokazOsobe(Osoby osoba){
        textImie.setText(osoba.getImie());
        textNazwisko.setText(osoba.getNazwisko());
        textEmail.setText(osoba.getEmail());
        textTelefon.setText((osoba.getTelefon().toString()));
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
        System.out.println("Dodaje nowy towar do bazy...");
        NowyTowar towar = new NowyTowar(textNazwa.getText(), textRozmiar.getText());
        baza.dodajTowar(towar);
        WyczyscTowar();
        aktualizujTableTowar();
    }
    void PokazTowar(Towar towar){
        textImie.setText(towar.getNazwa());
        textNazwisko.setText(towar.getrozmiar());
    }
    @FXML
    void UsunTowarButtonClicked(ActionEvent actionEvent) {
        Integer wybranaTowarID = tableTowar.getSelectionModel().getSelectedItem().getId();
        baza.UsunTowar(wybranaTowarID);
        WyczyscTowar();
        aktualizujTableTowar();
    }

    void WyczyscTowar() {
        textNazwa.clear();
        textRozmiar.clear();
        labeIDTowar.setText("---");
        System.out.println("Czyszczenie TextField w tabeli towar");
    }

    void aktualizujTableTowar() {

        try{
            ResultSet resultTowar = baza.getTowary();
            observableListtowar.clear();
            System.out.println("Aktualizowanie tabeli Towar...");

            while (resultTowar.next()) {
                System.out.println("Wynik z bazy\n " + resultTowar.getInt(1) + resultTowar.getString(2) + resultTowar.getString(3));

                observableListtowar.add(new Towar(resultTowar.getInt(1), resultTowar.getString(2), resultTowar.getString(3)));


            }

            tableTowar.setItems(getTowardane());

        }catch(Exception e){
            System.out.println(e);

        }



    }

    public ObservableList<Towar> getTowardane() {
        return observableListtowar;
    }
    @FXML
    void zapiszZmianyTowar(ActionEvent actionEvent) {
        Integer wybranaTowarID = tableTowar.getSelectionModel().getSelectedItem().getId();
        baza.aktualizujTowar();
        aktualizujTableTowar();
    }









}

