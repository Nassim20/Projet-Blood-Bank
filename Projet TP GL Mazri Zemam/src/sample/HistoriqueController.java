package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Classes.dateHistoriques;
import com.jfoenix.controls.JFXTextField;
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
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
public class HistoriqueController implements Initializable{
    @FXML
    private  JFXButton homeButton;
    @FXML
    private AnchorPane anchorRoot2;
    @FXML
    private StackPane parentContainer2;

    @FXML
    private JFXTextField rechdateField;

    @FXML
    private JFXButton RecherechD;


    @FXML
    private TableView<dateHistoriques> hisDon;

    @FXML
    private TableColumn<dateHistoriques, Integer> id_donneur;

    @FXML
    private TableColumn<dateHistoriques, String> nomdonneur;

    @FXML
    private TableColumn<dateHistoriques, String> Hopital;

    @FXML
    private TableColumn<dateHistoriques, String> GroupS;

    @FXML
    private TableColumn<dateHistoriques, String> typedon;

    @FXML
    private TableColumn<dateHistoriques, LocalDate> DateDon;
    @FXML
    private  JFXButton closeButton;

    @FXML
    private JFXButton afficheButt;

    @FXML
    private Label warninglabele;

    @FXML
    void afficherTab(ActionEvent event) {
        afficherTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherTable();
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
    void rechercheHist(ActionEvent event) {
        if (rechdateField.getText().isBlank()==false){
        remplireTable();}
        else{warninglabele.setText("Please fill the Field");}
    }

    private void remplireTable() {
        ObservableList<dateHistoriques> dateHistoriques = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String date = rechdateField.getText();
            ResultSet rs= query.select("select id_Donnneur,nom_donneur,Hopital,groupe_sanguin,typeDon,date_collecte from don where date_collecte Like'"+date+"%'");


            while (rs.next()){

                dateHistoriques.add(new dateHistoriques(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getString("Hopital"),
                        rs.getString("groupe_sanguin"),rs.getString("typeDon"),rs.getDate("date_collecte").toLocalDate()));
            }
            id_donneur.setCellValueFactory(new PropertyValueFactory<>("id_donneur"));
            nomdonneur.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            Hopital.setCellValueFactory(new PropertyValueFactory<>("Hopital"));
            GroupS.setCellValueFactory(new PropertyValueFactory<>("groupe_sanguin"));
            typedon.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
            DateDon.setCellValueFactory(new PropertyValueFactory<>("date_collecte"));

            hisDon.setItems(dateHistoriques);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void afficherTable(){
        ObservableList<dateHistoriques> dateHistoriques = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String date = rechdateField.getText();
            ResultSet rs= query.select("select id_Donnneur,nom_donneur,Hopital,groupe_sanguin,typeDon,date_collecte from don");


            while (rs.next()){

                dateHistoriques.add(new dateHistoriques(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getString("Hopital"),
                        rs.getString("groupe_sanguin"),rs.getString("typeDon"),rs.getDate("date_collecte").toLocalDate()));
            }
            id_donneur.setCellValueFactory(new PropertyValueFactory<>("id_donneur"));
            nomdonneur.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            Hopital.setCellValueFactory(new PropertyValueFactory<>("Hopital"));
            GroupS.setCellValueFactory(new PropertyValueFactory<>("groupe_sanguin"));
            typedon.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
            DateDon.setCellValueFactory(new PropertyValueFactory<>("date_collecte"));


            hisDon.setItems(dateHistoriques);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    }


