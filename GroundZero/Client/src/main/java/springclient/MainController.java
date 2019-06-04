package springclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cursa;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    private RequestBot requestBot;

    @FXML
    TableView<Cursa> cursaTableView;

    @FXML
    Button addButton;

    @FXML
    Button removeButton;

    @FXML
    TextField destinatieField;
    @FXML
    TextField plecareField;
    @FXML
    TextField dataField;

    @FXML
    TableColumn<Cursa, String> destinatieColumn;
    @FXML
    TableColumn<Cursa, String> plecareColumn;
    @FXML
    TableColumn<Cursa, String> dataColumn;

    public MainController() {
        requestBot = new RequestBot();
        setTableItems();
    }

    @FXML
    public void initialize() {

        destinatieColumn.setCellValueFactory(new PropertyValueFactory<>("destinatie"));
        plecareColumn.setCellValueFactory(new PropertyValueFactory<>("plecare"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataPlecare"));
        cursaTableView.setRowFactory(tr -> {
            TableRow<Cursa> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    try {
                        Cursa cursa1 = row.getItem();
                        buyTicketWindow(cursa1);
                    } catch (Exception e) {
//                        System.err.println("Buy window not available");
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });
        setTableItems();

    }

    private void buyTicketWindow(Cursa cursa) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/BiletWindow.fxml"));
        Parent root = loader.load();
        BiletController ctrl = loader.getController();
        ctrl.setCursaCurenta(cursa);
        Scene scene = new Scene(root);
        stage.setTitle("Buy ticket");
        stage.setScene(scene);
        stage.show();
    }


    private void setTableItems() {
        ArrayList<Cursa> cursaList = new ArrayList<>();
        try {
            Cursa[] l = requestBot.getAllCursa();
            for (Cursa c : l) {
                System.out.println(c.getDestinatie());
                cursaList.add(c);
            }
            ObservableList<Cursa> list = FXCollections.observableArrayList(cursaList);
            cursaTableView.setItems(list);
            cursaTableView.refresh();
        } catch (Exception e) {
            System.out.println();
        }
    }


    public void handleAdd(ActionEvent actionEvent) {
        String destinatie = destinatieField.getText();
        String data = dataField.getText();
        String plecare = plecareField.getText();

        Cursa toAdd = new Cursa();
        toAdd.setLocuriDisponibile(18);
        toAdd.setDataPlecare(data);
        toAdd.setPlecare(plecare);
        toAdd.setDestinatie(destinatie);

        try {
            requestBot.saveCursa(toAdd);
        } catch (Exception e) {
            System.out.println(e);
        }


        setTableItems();
    }


    public void handleRemove(ActionEvent actionEvent) {
        String destinatie = destinatieField.getText();
        String data = dataField.getText();
        String plecare = plecareField.getText();


        // find id by destination and then delete by that field


    }


}
