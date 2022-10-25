package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.dons;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
public class DonsConterller implements Initializable {

    @FXML
    private StackPane parentContainer2;

    @FXML
    private AnchorPane anchorRoot2;

    @FXML
    private JFXTextField idfield;

    @FXML
    private JFXTextField docnamefield;

    @FXML
    private JFXComboBox<String> grsangField;

    @FXML
    private DatePicker dateField;

    @FXML
    private JFXTextField hospitalfield;

    @FXML
    private JFXButton ajoutButton;

    @FXML
    private JFXButton annulerButton;

    @FXML
    private JFXTextField namefield;

    @FXML
    private Label warninglabele;

    @FXML
    private JFXComboBox<String> Typedons;

    @FXML
    private JFXTextField nbrsac;

    @FXML
    private JFXTextField rechDonField;

    @FXML
    private Label warninglabele1;

    @FXML
    private JFXButton donRechButt;

    @FXML
    private TableView<dons> donTable;

    @FXML
    private TableColumn<dons, Integer> id_Don;

    @FXML
    private TableColumn<dons, String> nom_don;

    @FXML
    private TableColumn<dons, String> nom_doc;

    @FXML
    private TableColumn<dons, String> nom_hop;

    @FXML
    private TableColumn<dons, String> sang_don;

    @FXML
    private TableColumn<dons, String> type_don;

    @FXML
    private TableColumn<dons, LocalDate> date_don;

    @FXML
    private TableColumn<dons, Integer> nbr_sac_don;

    @FXML
    private JFXButton homeButton;
    @FXML
    private  JFXButton closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grsangField.setItems(FXCollections.observableArrayList(
                "A+","A-","B+","B-","AB+","AB-","O+","O-"
        ));

        Typedons.setItems(FXCollections.observableArrayList(
                "sang total","globules rouges","plasma","plaquettes"
        ));
        affichertable();
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
    public void createDonAction(javafx.event.ActionEvent actionEvent){
        warninglabele.setText("");
        if (idfield.getText().isBlank()== false && docnamefield.getText().isBlank()== false && hospitalfield.getText().isBlank()== false && namefield.getText().isBlank()== false && nbrsac.getText().isBlank()== false  && grsangField.getValue()!=null && Typedons.getValue()!=null )
        {
            warninglabele.setText("");
            validateDon();
            affichertable();

        }else {
            warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText("please fill all the fields ");}

    }
    void validateDon() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String Type = (String) Typedons.getValue();
        String sang = (String) grsangField.getValue();

        String querry =" INSERT INTO DON VALUES ("+idfield.getText()+",'"+namefield.getText()+"','"+hospitalfield.getText()+"','"+sang+"','"+dateField.getValue().toString()+"','"+Type+"','"+docnamefield.getText()+"',"+nbrsac.getText()+");";
        try { if (checkid(idfield.getText()) == true)
            {   warninglabele.setTextFill(Color.web("#ff0000"));
                warninglabele.setText(" id existe deja "); return;}
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" IT IS INSERTED ;) !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void AnnullerAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText("");
        dateField.setValue(null);
        idfield.setText("");
        namefield.setText("");
        hospitalfield.setText("");
        docnamefield.setText("");
        Typedons.setValue("");
        grsangField.setValue("");
        nbrsac.setText("");

    }

    @FXML
    void rechDon(ActionEvent event) {
        if (rechDonField.getText().isBlank()==false){
            warninglabele1.setText("");
        remplireTable();}
        else warninglabele1.setText("please choose a Name to search for");
    }

    private void remplireTable() {
        ObservableList<dons> dons = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String nomDonnateur = rechDonField.getText();
            ResultSet rs = query.select("select * from don where nom_donneur='"+nomDonnateur+"'");

            while (rs.next()){

                dons.add(new dons(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getString("nom_Medecin_infermiere"),
                        rs.getString("Hopital"),rs.getString("groupe_sanguin"),rs.getString("typeDon"),
                        rs.getDate("date_collecte").toLocalDate(),rs.getInt("nbr_sac")));
            }
            id_Don.setCellValueFactory(new PropertyValueFactory<>("idDon"));
            nom_don.setCellValueFactory(new PropertyValueFactory<>("nomDon"));
            nom_doc.setCellValueFactory(new PropertyValueFactory<>("nomDoc"));
            nom_hop.setCellValueFactory(new PropertyValueFactory<>("nomHop"));
            sang_don.setCellValueFactory(new PropertyValueFactory<>("groupS"));
            type_don.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
            date_don.setCellValueFactory(new PropertyValueFactory<>("dateDon"));
            nbr_sac_don.setCellValueFactory(new PropertyValueFactory<>("nbr_sacDon"));


            donTable.setItems(dons);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}

    private void affichertable(){
        ObservableList<dons> dons = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from don");

            while (rs.next()){

                dons.add(new dons(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getString("nom_Medecin_infermiere"),
                        rs.getString("Hopital"),rs.getString("groupe_sanguin"),rs.getString("typeDon"),
                        rs.getDate("date_collecte").toLocalDate(),rs.getInt("nbr_sac")));
            }
            id_Don.setCellValueFactory(new PropertyValueFactory<>("idDon"));
            nom_don.setCellValueFactory(new PropertyValueFactory<>("nomDon"));
            nom_doc.setCellValueFactory(new PropertyValueFactory<>("nomDoc"));
            nom_hop.setCellValueFactory(new PropertyValueFactory<>("nomHop"));
            sang_don.setCellValueFactory(new PropertyValueFactory<>("groupS"));
            type_don.setCellValueFactory(new PropertyValueFactory<>("typeDon"));
            date_don.setCellValueFactory(new PropertyValueFactory<>("dateDon"));
            nbr_sac_don.setCellValueFactory(new PropertyValueFactory<>("nbr_sacDon"));


            donTable.setItems(dons);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    private ArrayList<String> fill() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Query q = new Query();
        ResultSet result = q.select("SELECT id_Donnneur FROM don ");

        while(result.next()){
            list.add(result.getString("id_Donnneur"));
        }
        return list;
    }

    private boolean checkid(String RVid) throws SQLException {
        ArrayList<String> arr = fill();
        for(String s: arr)
            if (s.equals(RVid)) return true;
        return false;
    }
}
