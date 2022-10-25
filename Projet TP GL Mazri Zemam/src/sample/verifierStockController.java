package sample;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class verifierStockController implements Initializable {

    @FXML
    private TextField stockText1;

    @FXML
    private JFXComboBox<String> typefield;

    @FXML
    private JFXButton affiche_stock1;

    @FXML
    private JFXComboBox<String> grpsang;
    @FXML
    private JFXButton closeButton;





    @FXML
    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        grpsang.setItems(FXCollections.observableArrayList(
                "All", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        ));
        typefield.setItems(FXCollections.observableArrayList(
                "sang total","globules rouges","plasma","plaquettes"
        ));
    }

    @FXML
    void affiche_stockGs(ActionEvent event) {
        try {
            Query query = new Query();
            String groupSan = grpsang.getSelectionModel().getSelectedItem();
            String typeSan = typefield.getSelectionModel().getSelectedItem();
            ResultSet rs;

            if (groupSan=="All"){rs = query.select("select SUM(nbr_sac) from stock  where typeDon='"+typeSan+"'");}
            else{ rs = query.select("select SUM(nbr_sac) from stock where groupSanguin='"+groupSan+"' and typeDon = '"+typeSan+"'");}

            rs.next();
            int nbr = rs.getInt(1);
            stockText1.setText(String.valueOf(nbr));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
