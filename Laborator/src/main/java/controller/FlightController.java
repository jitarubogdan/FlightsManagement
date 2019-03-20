package controller;

import domain.Zbor;
import domain.ZborDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.BiletService;
import service.ClientService;
import service.ZborService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FlightController {

    private ZborService srvZ;
    private ClientService srvC;
    private BiletService srvB;
    private ObservableList<Zbor> modelZbor = FXCollections.observableArrayList();
    private ObservableList<ZborDTO> modelZborFilter = FXCollections.observableArrayList();
    private Stage primaryStageFlight;

    @FXML
    TableView<Zbor> tableView;

    @FXML
    TableColumn<Zbor, String> tableColumnDestinatie;

    @FXML
    TableColumn<Zbor, LocalDateTime> tableColumnPlecare;

    @FXML
    TableColumn<Zbor, String> tableColumnAeroport;

    @FXML
    TableColumn<Zbor, Integer> tableColumnLocuri;

    @FXML
    TableView<ZborDTO> tableViewFilter;

    @FXML
    TableColumn<ZborDTO,String> tableColumnOraFilter;

    @FXML
    TableColumn<ZborDTO, LocalTime> tableColumnLocuriFilter;

    @FXML
    TextField textFieldDestinatie;

    @FXML
    TextField textFieldData;

    @FXML
    TextField textFieldOra;

    @FXML
    TextField textFieldNume;

    @FXML
    TextField textFieldAdresa;

    @FXML
    TextField textFieldLocuri;

    @FXML
    TextArea textAreaTuristi;

    @FXML
    public void initialize(){
        tableColumnDestinatie.setCellValueFactory(new PropertyValueFactory<Zbor, String>("destinatie"));
        tableColumnPlecare.setCellValueFactory(new PropertyValueFactory<Zbor, LocalDateTime>("plecare"));
        tableColumnAeroport.setCellValueFactory(new PropertyValueFactory<Zbor, String>("aeroport"));
        tableColumnLocuri.setCellValueFactory(new PropertyValueFactory<Zbor, Integer>("locuri"));

        tableView.setItems(modelZbor);
    }

    public void setService(ZborService srvZ, ClientService srvC, BiletService srvB){
        this.srvZ = srvZ;
        this.srvC = srvC;
        this.srvB = srvB;

        List<Zbor> list = StreamSupport.stream(srvZ.findAll().spliterator(),false).collect(Collectors.toList());

        modelZbor.setAll(list);
    }

    public void handleFilterButtonAction(ActionEvent actionEvent) {
        String destinatie = textFieldDestinatie.getText();
        String data = textFieldData.getText();

        List<ZborDTO> listFiltered = StreamSupport.stream(srvZ.findByDestinatieData(destinatie,data).spliterator(),false).collect(Collectors.toList());

        modelZborFilter.setAll(listFiltered);

        tableColumnOraFilter.setCellValueFactory((new PropertyValueFactory<ZborDTO,String>("ora")));
        tableColumnLocuriFilter.setCellValueFactory((new PropertyValueFactory<ZborDTO,LocalTime>("locuri")));

        tableViewFilter.setItems(modelZborFilter);
    }

    public void handleAddButtonAction(ActionEvent actionEvent) {
        String destinatie = textFieldDestinatie.getText();
        String data = textFieldData.getText();
        String ora = textFieldOra.getText();
        String nume = textFieldNume.getText();
        String adresa = textFieldAdresa.getText();
        String locuri = textFieldLocuri.getText();
        String turisti = textAreaTuristi.getText();

        srvB.addBilet(destinatie,data,ora,nume,adresa,locuri,turisti);

        modelZbor.setAll(StreamSupport.stream(srvZ.findAll().spliterator(),false).collect(Collectors.toList()));
        modelZborFilter.setAll(StreamSupport.stream(srvZ.findByDestinatieData(destinatie,data).spliterator(),false).collect(Collectors.toList()));
    }

    public void setStage(Stage primaryStageFlight) {
        this.primaryStageFlight = primaryStageFlight;
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        primaryStageFlight.close();
    }
}
