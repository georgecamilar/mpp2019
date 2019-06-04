package springclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bilet;
import model.Cursa;

import java.util.ArrayList;

public class BiletController {

    private Cursa cursaCurenta;
    private RequestBot bot;

    @FXML
    TableView<Bilet> biletTable;
    @FXML
    TableColumn<Bilet, String> numeColumn;
    @FXML
    TableColumn<Bilet, Integer> locColumn;

    @FXML
    TextField locField;
    @FXML
    TextField nameField;
    @FXML
    Label cursaLabel;

    public BiletController() {
        bot = new RequestBot();
    }

    public void setCursaCurenta(Cursa cursaCurenta) {
        this.cursaCurenta = cursaCurenta;
    }

    @FXML
    public void initialize() {
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        locColumn.setCellValueFactory(new PropertyValueFactory<>("loc"));
       // cursaLabel.setText(cursaCurenta.toString());
        setTableItems();
    }


    private void setTableItems() {
        ArrayList<Bilet> list = new ArrayList<>();
        try {

            Bilet[] l = this.bot.getAllBilet();
            for (Bilet b : l) {
                System.out.println(b.getLoc());
                list.add(b);
            }

        } catch (Exception e) {
            System.out.println();
        }

        ObservableList olist = FXCollections.observableArrayList(list);
        biletTable.setItems(olist);
    }


    public void handleBuy(ActionEvent event) {
        String nume = this.nameField.getText();
        Integer loc = Integer.parseInt(this.locField.getText());


        Bilet b = new Bilet();
        b.setCursa(this.cursaCurenta);
        b.setLoc(loc);
        b.setNume(nume);

        try {
            bot.addBilet(b);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
