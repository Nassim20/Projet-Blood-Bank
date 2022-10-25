package sample;

import Classes.demandes;
import Classes.stockedBlood;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class demandesController implements Initializable {
    @FXML
    private JFXButton closeButton;
    @FXML
    private StackPane parentContainer2;

    @FXML
    private AnchorPane anchorRoot2;
    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton ajoutbutton;

    @FXML
    private JFXButton annulerbutton;

    @FXML
    private Label warninglabele;
    @FXML
    private JFXTextField idfield;

    @FXML
    private JFXTextField namefield;

    @FXML
    private JFXTextField phonefield;

    @FXML
    private JFXTextField hospitalfield;

    @FXML
    private DatePicker dateField;

    @FXML
    private JFXComboBox<String> statefield;

    @FXML
    private JFXComboBox<String> typefield;

    @FXML
    private JFXTextField nbsacField;
    @FXML
    private JFXButton filterbutt;

    @FXML
    private JFXButton approveButt;

    @FXML
    private JFXButton supButt;

    @FXML
    private JFXButton verifierStockbutt;

    @FXML
    private JFXComboBox<String> samgfield;

    @FXML
    private JFXComboBox<String> state1field;

    @FXML
    private JFXTextField idrech;

    @FXML
    private TableView<demandes> demandeTable;

    @FXML
    private TableColumn<demandes, String> idDemande;

    @FXML
    private TableColumn<demandes, String> nomPrenomDemande;

    @FXML
    private TableColumn<demandes, String> hopitalDemande;

    @FXML
    private TableColumn<demandes, String> typeDemande;

    @FXML
    private TableColumn<demandes, String> groupeDemande;

    @FXML
    private TableColumn<demandes, String> etatsDemande;

    @FXML
    private TableColumn<demandes, String> urgentDemande;

    @FXML
    private TableColumn<demandes, Integer> nombreSack;

    @FXML
    private TableColumn<demandes, Integer> contactDemande;

    @FXML
    private TableColumn<demandes, LocalDate> dateDemande;

    @FXML
    private Label warninglabele1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statefield.setItems(FXCollections.observableArrayList(
                "urgent", "normal"
        ));
        typefield.setItems(FXCollections.observableArrayList(
                "sang total","globules rouges","plasma","plaquettes"
        ));
        samgfield.setItems(FXCollections.observableArrayList(
                "A+","A-","B+","B-","AB+","AB-","O+","O-"
        ));
        state1field.setItems(FXCollections.observableArrayList(
                "urgent", "normal","tout les etats"
        ));

        afficherTable();

    }

    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
    public void  AddDemandeAction(javafx.event.ActionEvent actionEvent){
        warninglabele.setText("");
        if (idfield.getText().isBlank()== false && namefield.getText().isBlank()== false && phonefield.getText().isBlank()== false   && samgfield.getValue()!=null && typefield.getValue()!=null && statefield.getValue()!=null )
        {
            warninglabele.setText("");
            validateADD();
            afficherTable();

        }else { warninglabele.setTextFill(Color.web("#ff0000"));
               warninglabele.setText("SVP remplissez les champs ");}

    }
    void validateADD() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String type = (String) typefield.getValue();
        String isUrgent  = (String) statefield.getValue();
        String sang = (String) samgfield.getValue();

        String querry =" INSERT INTO demande  VALUES ('"+idfield.getText()+"','"+namefield.getText()+"','"+hospitalfield.getText()+"','"+type+"','"+sang+"','En attente','"+isUrgent+"','"+nbsacField.getText()+"','"+phonefield.getText()+"','"+dateField.getValue().toString()+"');";
        try { if (checkid(idfield.getText()) == true)
             { warninglabele.setTextFill(Color.web("#ff0000"));
               warninglabele.setText(" id existe deja "); return;}
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" BIEN INSERER  ;) !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    @FXML
    public void AnnullerAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText("");
        idfield.setText("");
        nbsacField.setText("");
        namefield.setText("");
        phonefield.setText("");
        hospitalfield.setText("");
        samgfield.setValue("");
        statefield.setValue("");
        typefield.setValue("");
        dateField.setValue(null);
    }

    int index=-1;
    String idDemSauv ;

    @FXML
    void getSelected(MouseEvent event) {
        index = demandeTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idDemSauv= idDemande.getCellData(index);
    }

    @FXML
    void rechDemande(ActionEvent event) {
        if (idrech.getText().isBlank()==false){
            warninglabele1.setText(" ");
        ObservableList<demandes> demandesObliste = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from demande where id_Demmande='"+idrech.getText()+"'");

            while (rs.next()){

                demandesObliste.add(new demandes(rs.getString("id_Demmande"),rs.getString("nom_demandeur"),rs.getString("Hopital"),
                        rs.getString("typeDon"),rs.getString("groupe_sanguin"),rs.getString("state"),rs.getString("isUrgent"),
                        rs.getInt("nbr_sac"),rs.getInt("Contact"),rs.getDate("dateDem").toLocalDate()));
            }

            idDemande.setCellValueFactory(new PropertyValueFactory<>("id_Demande"));
            nomPrenomDemande.setCellValueFactory(new PropertyValueFactory<>("nom_Prenom_Demande"));
            hopitalDemande.setCellValueFactory(new PropertyValueFactory<>("hopital_demande"));
            typeDemande.setCellValueFactory(new PropertyValueFactory<>("type_demande"));
            groupeDemande.setCellValueFactory(new PropertyValueFactory<>("groupeS_demande"));
            etatsDemande.setCellValueFactory(new PropertyValueFactory<>("state_demande"));
            urgentDemande.setCellValueFactory(new PropertyValueFactory<>("urgentetat_demande"));
            nombreSack.setCellValueFactory(new PropertyValueFactory<>("nbrSac_dem"));
            contactDemande.setCellValueFactory(new PropertyValueFactory<>("contact_dem"));
            dateDemande.setCellValueFactory(new PropertyValueFactory<>("date_dem"));


            demandeTable.setItems(demandesObliste);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}
        else warninglabele1.setText("SVP remplissez les champs ");
    }

    @FXML
    void gotoVerifStock(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ressources/verifierStock.fxml"));
            Stage Stage = new Stage();
            Stage.initStyle(StageStyle.UNDECORATED);
            Stage.setScene(new Scene(root, 536, 328));
            Stage.show();
            Stage.setResizable(false);

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    void livSuppDemande(ActionEvent event) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="DELETE FROM demande WHERE id_Demmande='"+idDemSauv+"';";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" SUPREMER  😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        afficherTable();
    }

    @FXML
    void filtreDemande(ActionEvent event) {
        String state = state1field.getSelectionModel().getSelectedItem();
        if (state!= null){
            warninglabele1.setText(" ");
        if (state== "tout les etats"){afficherTable();}
        else{
        ObservableList<demandes> demandesObliste = FXCollections.observableArrayList();
        try {
            Query query = new Query();

            ResultSet rs = query.select("select * from demande where isUrgent='"+state+"'");

            while (rs.next()){

                demandesObliste.add(new demandes(rs.getString("id_Demmande"),rs.getString("nom_demandeur"),rs.getString("Hopital"),
                        rs.getString("typeDon"),rs.getString("groupe_sanguin"),rs.getString("state"),rs.getString("isUrgent"),
                        rs.getInt("nbr_sac"),rs.getInt("Contact"),rs.getDate("dateDem").toLocalDate()));
            }

            idDemande.setCellValueFactory(new PropertyValueFactory<>("id_Demande"));
            nomPrenomDemande.setCellValueFactory(new PropertyValueFactory<>("nom_Prenom_Demande"));
            hopitalDemande.setCellValueFactory(new PropertyValueFactory<>("hopital_demande"));
            typeDemande.setCellValueFactory(new PropertyValueFactory<>("type_demande"));
            groupeDemande.setCellValueFactory(new PropertyValueFactory<>("groupeS_demande"));
            etatsDemande.setCellValueFactory(new PropertyValueFactory<>("state_demande"));
            urgentDemande.setCellValueFactory(new PropertyValueFactory<>("urgentetat_demande"));
            nombreSack.setCellValueFactory(new PropertyValueFactory<>("nbrSac_dem"));
            contactDemande.setCellValueFactory(new PropertyValueFactory<>("contact_dem"));
            dateDemande.setCellValueFactory(new PropertyValueFactory<>("date_dem"));

            demandeTable.setItems(demandesObliste);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}}
        else warninglabele1.setText("SVP  choisissez une Option");
    }

    @FXML
    void approuveDemande(ActionEvent event) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="UPDATE demande SET state='Approuvé' WHERE id_Demmande='"+idDemSauv+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" MODIFIER  😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        afficherTable();
    }

    private void afficherTable(){
        ObservableList<demandes> demandesObliste = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from demande");

            while (rs.next()){

                demandesObliste.add(new demandes(rs.getString("id_Demmande"),rs.getString("nom_demandeur"),rs.getString("Hopital"),
                        rs.getString("typeDon"),rs.getString("groupe_sanguin"),rs.getString("state"),rs.getString("isUrgent"),
                        rs.getInt("nbr_sac"),rs.getInt("Contact"),rs.getDate("dateDem").toLocalDate()));
            }

            idDemande.setCellValueFactory(new PropertyValueFactory<>("id_Demande"));
            nomPrenomDemande.setCellValueFactory(new PropertyValueFactory<>("nom_Prenom_Demande"));
            hopitalDemande.setCellValueFactory(new PropertyValueFactory<>("hopital_demande"));
            typeDemande.setCellValueFactory(new PropertyValueFactory<>("type_demande"));
            groupeDemande.setCellValueFactory(new PropertyValueFactory<>("groupeS_demande"));
            etatsDemande.setCellValueFactory(new PropertyValueFactory<>("state_demande"));
            urgentDemande.setCellValueFactory(new PropertyValueFactory<>("urgentetat_demande"));
            nombreSack.setCellValueFactory(new PropertyValueFactory<>("nbrSac_dem"));
            contactDemande.setCellValueFactory(new PropertyValueFactory<>("contact_dem"));
            dateDemande.setCellValueFactory(new PropertyValueFactory<>("date_dem"));


            demandeTable.setItems(demandesObliste);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private ArrayList<String> fill() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Query q = new Query();
        ResultSet result = q.select("SELECT id_Demmande FROM demande ");

        while(result.next()){
            list.add(result.getString("id_Demmande"));
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
