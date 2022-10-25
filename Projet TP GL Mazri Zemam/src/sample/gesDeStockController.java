package sample;

import Classes.stockedBlood;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class gesDeStockController implements Initializable {

    @FXML
    private TableView<stockedBlood> table_stock;

    @FXML
    private TableColumn<stockedBlood, Integer> id_stock;

    @FXML
    private TableColumn<stockedBlood, String> nom_donneur_stock;

    @FXML
    private TableColumn<stockedBlood, String> groupSanguinStock;

    @FXML
    private TableColumn<stockedBlood, String> typeDon;

    @FXML
    private TableColumn<stockedBlood, LocalDate> dateDon;

    @FXML
    private TableColumn<stockedBlood, Integer> nbrStock;

    @FXML
    private ComboBox<String> comboGroupRech;

    @FXML
    private ComboBox<String> gs;

    @FXML
    private ComboBox<String> comboGroupe;

    @FXML
    private TextField rechText;

    @FXML
    private JFXButton valid_Recherche;

    @FXML
    private TextField stockText;

    @FXML
    private JFXButton affiche_stock;

    @FXML
    private ComboBox<String> comboGroupe1;

    @FXML
    private TextField stockText1;

    @FXML
    private JFXButton affiche_stock1;

    @FXML
    private JFXButton supprimStockButt;

    @FXML
    private JFXButton supprimStockButt1;

    @FXML
    void filtrerTable(ActionEvent event) {
        remplireTable();
    }
    @FXML
    private  JFXButton homeButton;
    @FXML
    private AnchorPane anchorRoot2;
    @FXML
    private StackPane parentContainer2;

    @FXML
    private Label warninglabele;
    @FXML
    private  JFXButton closeButton;




    @FXML
    void affiche_stockTd(ActionEvent event) {
        try {
            Query query = new Query();
            String typeD = comboGroupe1.getSelectionModel().getSelectedItem();
            ResultSet rs;

            rs = query.select("select SUM(nbr_sac) from stock where typeDon='"+typeD+"'");
            rs.next();
            int nbr = rs.getInt(1);
            stockText1.setText(String.valueOf(nbr));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void affiche_stockGs(ActionEvent event) {
        try {
            Query query = new Query();
            String groupSan = comboGroupe.getSelectionModel().getSelectedItem();
            ResultSet rs;

            rs = query.select("select SUM(nbr_sac) from stock where groupSanguin='"+groupSan+"'");
            rs.next();
            int nbr = rs.getInt(1);
            stockText.setText(String.valueOf(nbr));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gs.setItems(FXCollections.observableArrayList(
                "All","A+","A-","B+","B-","AB+","AB-","O+","O-"
        ));

        comboGroupe.setItems(FXCollections.observableArrayList(
                "A+","A-","B+","B-","AB+","AB-","O+","O-"
        ));

        comboGroupe1.setItems(FXCollections.observableArrayList(
                "sang total","globules rouges","plasma","plaquettes"
        ));

        comboGroupRech.setItems(FXCollections.observableArrayList(
                "All","sang total","globules rouges","plasma","plaquettes"
        ));

        afficheTable();


    }

    private void afficheTable(){
        ObservableList<stockedBlood> stockOblist = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from stock");

            while (rs.next()){

                stockOblist.add(new stockedBlood(rs.getInt("numPack"),rs.getString("nomDonneur"),rs.getString("groupSanguin"),
                        rs.getString("typeDon"),rs.getDate("dateDon").toLocalDate(),rs.getInt("nbr_sac")));
            }
            id_stock.setCellValueFactory(new PropertyValueFactory<>("num_pac"));
            nom_donneur_stock.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            groupSanguinStock.setCellValueFactory(new PropertyValueFactory<>("group_sang"));
            typeDon.setCellValueFactory(new PropertyValueFactory<>("type_don"));
            dateDon.setCellValueFactory(new PropertyValueFactory<>("date_don"));
            nbrStock.setCellValueFactory(new PropertyValueFactory<>("nbr_sac"));


            table_stock.setItems(stockOblist);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    private void remplireTable() {
        ObservableList<stockedBlood> stockOblist = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String groupSan = gs.getSelectionModel().getSelectedItem();
            String typeD = comboGroupRech.getSelectionModel().getSelectedItem();
            ResultSet rs;
            if (groupSan==null ){groupSan="All";}
            if (typeD==null){typeD="All";}
            if(groupSan=="All" && typeD=="All"){rs = query.select("select * from stock");}
            else if (groupSan=="All"){rs = query.select("select * from stock  where typeDon='"+typeD+"'");}
                else if (typeD == "All"){rs = query.select("select * from stock where groupSanguin='"+groupSan+"'");}
                    else rs = query.select("select * from stock where typeDon='"+typeD+"' and groupSanguin='"+groupSan+"'");

            while (rs.next()){

                stockOblist.add(new stockedBlood(rs.getInt("numPack"),rs.getString("nomDonneur"),rs.getString("groupSanguin"),
                        rs.getString("typeDon"),rs.getDate("dateDon").toLocalDate(),rs.getInt("nbr_sac")));
            }

            id_stock.setCellValueFactory(new PropertyValueFactory<>("num_pac"));
            nom_donneur_stock.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            groupSanguinStock.setCellValueFactory(new PropertyValueFactory<>("group_sang"));
            typeDon.setCellValueFactory(new PropertyValueFactory<>("type_don"));
            dateDon.setCellValueFactory(new PropertyValueFactory<>("date_don"));
            nbrStock.setCellValueFactory(new PropertyValueFactory<>("nbr_sac"));


            table_stock.setItems(stockOblist);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void loadHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/Menu.fxml"));
        Scene scene = homeButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer2.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer2.getChildren().remove(anchorRoot2);
        });
        timeline.play();
    }

    @FXML
    void suppStock(ActionEvent event) {
        supprimApplied();
        remplireTable();
    }

    int index = -1;
    String numeroPacket;
    int nombre_sac;

    @FXML
    void getSelected(MouseEvent event) {
        index = table_stock.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        numeroPacket= id_stock.getCellData(index).toString();
        nombre_sac=  nbrStock.getCellData(index);
    }

    private void supprimApplied(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry = "";
        if (nombre_sac==1){ querry ="DELETE FROM stock WHERE numPack="+numeroPacket+";";}
        else if (nombre_sac>1) {querry="UPDATE  Stock set  nbr_sac=nbr_sac-1 WHERE numPack="+numeroPacket+";";}
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setText(" DELETED   😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void suppStockPeri(ActionEvent event) {
        supprimerStockCondition("sang total",35);
        supprimerStockCondition("globules rouges",42);
        supprimerStockCondition("plaquettes",5);
        supprimerStockCondition("plasma",300);
        afficheTable();
    }

    private void supprimerStockCondition(String typeDon , int days){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="DELETE FROM stock WHERE typeDon='"+typeDon+"' AND DATEDIFF(UTC_DATE(),dateDon)>"+days+";";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setText(" DELETED   😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
