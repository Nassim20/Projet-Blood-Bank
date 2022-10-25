package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.donneurs;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
public class DonneurController implements Initializable {
    @FXML
    private StackPane parentContainer2;

    @FXML
    private AnchorPane anchorRoot2;

    @FXML
    private JFXTextField idfield;

    @FXML
    private JFXTextField namefield;

    @FXML
    private JFXTextField phonefield;

    @FXML
    private JFXTextField agefield;

    @FXML
    private JFXComboBox<String> genderfielfd;

    @FXML
    private JFXTextField mailfield;

    @FXML
    private JFXComboBox<String> samgfield;

    @FXML
    private JFXButton ajoutbutton;

    @FXML
    private JFXButton annulerbutton;

    @FXML
    private Label warninglabele;

    @FXML
    private JFXButton rechDonneurButt;

    @FXML
    private JFXTextField rechNomDonneur;

    @FXML
    private TableView<donneurs> donneurTab;

    @FXML
    private TableColumn<donneurs, Integer> idDonneur;

    @FXML
    private TableColumn<donneurs, String> nomDonneur;

    @FXML
    private TableColumn<donneurs, Integer> contactDonneur;

    @FXML
    private TableColumn<donneurs, String> mailDonneur;

    @FXML
    private TableColumn<donneurs, Integer> ageDonneur;

    @FXML
    private TableColumn<donneurs, String> genderDonneur;

    @FXML
    private TableColumn<donneurs, String> sangDonneur;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton modifDonneurButt;

    @FXML
    private JFXButton supDonneurButt;
    @FXML
    private  JFXButton closeButton;

    @FXML
    private Label warninglabele1;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genderfielfd.setItems(FXCollections.observableArrayList(
                "Female","Male"
        ));


        samgfield.setItems(FXCollections.observableArrayList(
                "A+","A-","B+","B-","AB+","AB-","O+","O-"
        ));

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

    public void  AddDonnerAction(javafx.event.ActionEvent actionEvent){
        warninglabele.setText("");
        if (idfield.getText().isBlank()== false && namefield.getText().isBlank()== false && phonefield.getText().isBlank()== false && agefield.getText().isBlank()== false && mailfield.getText().isBlank()== false && samgfield.getValue()!=null && genderfielfd.getValue()!=null )
        {
            warninglabele.setText("");
            validateADD();
            afficherTable();
        }else { warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText("SVP remplissez tout les champs ");}

    }

    void validateADD() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String gender = (String) genderfielfd.getValue();
        String sang = (String) samgfield.getValue();
        String querry =" INSERT INTO donneur VALUES ("+idfield.getText()+",'"+namefield.getText()+"',"+phonefield.getText()+",'"+mailfield.getText()+"',"+agefield.getText()+",'"+gender+"','"+sang+"');";
        try { if (checkDNEUR(idfield.getText()) == true)
              { warninglabele.setTextFill(Color.web("#ff0000"));
              warninglabele.setText(" id existe deja "); return;}
              Statement statement = connectDB.createStatement();
              statement.executeUpdate(querry);
              warninglabele.setTextFill(Color.web("#AFC3FE"));
              warninglabele.setText(" BIEN INSERER   ;) !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void AnnullerAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText("");
        idfield.setText("");
        namefield.setText("");
        phonefield.setText("");
        mailfield.setText("");
        genderfielfd.setValue("");
        samgfield.setValue("");
        agefield.setText("");
    }

    @FXML
    void rechDonneur(ActionEvent event) {
        if (rechNomDonneur.getText().isBlank()==false){
            warninglabele1.setText(" ");
        remplireTable();}
        else warninglabele1.setText("SVP  remplissez le champs de recherch ");
    }

    private void remplireTable() {
        ObservableList<donneurs> donneurs = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String nomDonneeur = rechNomDonneur.getText();
            ResultSet rs = query.select("select * from donneur where nom_donneur='"+nomDonneeur+"'");

            while (rs.next()){

                donneurs.add(new donneurs(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getInt("Contact"),
                        rs.getString("Email"),rs.getInt("age_donneur"),
                        rs.getString("genre"),rs.getString("groupe_sanguin")));
            }
            idDonneur.setCellValueFactory(new PropertyValueFactory<>("id_donneur"));
            nomDonneur.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            contactDonneur.setCellValueFactory(new PropertyValueFactory<>("contact_donneur"));
            mailDonneur.setCellValueFactory(new PropertyValueFactory<>("mail_donneur"));
            ageDonneur.setCellValueFactory(new PropertyValueFactory<>("age_donneur"));
            genderDonneur.setCellValueFactory(new PropertyValueFactory<>("gender_donneur"));
            sangDonneur.setCellValueFactory(new PropertyValueFactory<>("sang_donneur"));


            donneurTab.setItems(donneurs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    int index = -1;
    String rowToDelete;
    String saveIdDONNEUR;

    @FXML
    void getSelected(MouseEvent event) {
        index = donneurTab.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        rowToDelete= idDonneur.getCellData(index).toString();
        saveIdDONNEUR = idDonneur.getCellData(index).toString();

        idfield.setText(idDonneur.getCellData(index).toString());
        namefield.setText(nomDonneur.getCellData(index).toString());
        phonefield.setText(contactDonneur.getCellData(index).toString());
        genderfielfd.setValue(genderDonneur.getCellData(index));
        samgfield.setValue(sangDonneur.getCellData(index));
        mailfield.setText(mailDonneur.getCellData(index).toString());
        agefield.setText(ageDonneur.getCellData(index).toString());
    }


    @FXML
    void modifDonneur(ActionEvent event) {
        warninglabele.setText("");
        if (idfield.getText()==saveIdDONNEUR){
        if (idfield.getText().isBlank()== false && namefield.getText().isBlank()== false && phonefield.getText().isBlank()== false && agefield.getText().isBlank()== false && mailfield.getText().isBlank()== false && samgfield.getValue()!=null && genderfielfd.getValue()!=null )
        {
            modifApply();
            afficherTable();

        }else { warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText(" svp remplisser les champs que vous voulez modifier ");}}
        else { warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText("vous ne pouvez pas modifier le Id");}

    }

    private void modifApply(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String gender = genderfielfd.getSelectionModel().getSelectedItem();
        String gs = samgfield.getSelectionModel().getSelectedItem();
        String querry ="UPDATE donneur SET nom_donneur='"+namefield.getText()+"' , Contact='"+phonefield.getText()+"' , Email='"+mailfield.getText()+"' , age_donneur='"+agefield.getText()+"',genre='"+gender+"',groupe_sanguin='"+gs+"' WHERE id_Donnneur='"+idfield.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" MODIFIER  😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    @FXML
    void supprimerDonneur(ActionEvent event) {
        supprimApplied();
        afficherTable();
    }

    private void supprimApplied(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="DELETE FROM donneur WHERE id_Donnneur="+rowToDelete+";";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" SUPRIMER  😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void afficherTable(){
        ObservableList<donneurs> donneurs = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from donneur");

            while (rs.next()){

                donneurs.add(new donneurs(rs.getInt("id_Donnneur"),rs.getString("nom_donneur"),rs.getInt("Contact"),
                        rs.getString("Email"),rs.getInt("age_donneur"),
                        rs.getString("genre"),rs.getString("groupe_sanguin")));
            }
            idDonneur.setCellValueFactory(new PropertyValueFactory<>("id_donneur"));
            nomDonneur.setCellValueFactory(new PropertyValueFactory<>("nom_donneur"));
            contactDonneur.setCellValueFactory(new PropertyValueFactory<>("contact_donneur"));
            mailDonneur.setCellValueFactory(new PropertyValueFactory<>("mail_donneur"));
            ageDonneur.setCellValueFactory(new PropertyValueFactory<>("age_donneur"));
            genderDonneur.setCellValueFactory(new PropertyValueFactory<>("gender_donneur"));
            sangDonneur.setCellValueFactory(new PropertyValueFactory<>("sang_donneur"));

            donneurTab.setItems(donneurs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private ArrayList<String> fill() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Query q = new Query();
        ResultSet result = q.select("SELECT id_Donnneur FROM donneur ");

        while(result.next()){
            list.add(result.getString("id_Donnneur"));
        }
        return list;
    }

    private boolean checkDNEUR(String RVid) throws SQLException {
        ArrayList<String> arr = fill();
        for(String s: arr)
            if (s.equals(RVid)) return true;
        return false;
    }
}
