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

import Classes.rdvs;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
public class RendezVousController implements Initializable {
    @FXML
    private StackPane parentContainer2;

    @FXML
    private AnchorPane anchorRoot2;

    @FXML
    private JFXTextField namefield;

    @FXML
    private JFXTextField idRV;

    @FXML
    private JFXButton ajouterbutton;

    @FXML
    private JFXButton annulerbutton;

    @FXML
    private JFXTextField lastNamefield;

    @FXML
    private DatePicker datefield;

    @FXML
    private JFXTextField timefield;

    @FXML
    private Label warninglabele;

    @FXML
    private JFXTextField rdvRechField;

    @FXML
    private JFXButton rdvRechButt;

    @FXML
    private TableView<rdvs> rdvTable;

    @FXML
    private TableColumn<rdvs, String> idRdv;

    @FXML
    private TableColumn<rdvs, String> nomRdv;

    @FXML
    private TableColumn<rdvs, String> PrenomRdv;

    @FXML
    private TableColumn<rdvs, LocalDate> dateRdv;

    @FXML
    private TableColumn<rdvs, String> heurRdv;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton modifRdvButt;

    @FXML
    private Label warninglabele1;

    @FXML
    private JFXButton supRdvButt;
    @FXML
    private  JFXButton closeButton;
    @FXML
    private DatePicker datefield2;



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
    public void  AddRVAction(ActionEvent actionEvent){
        warninglabele.setText("");
        if ( namefield.getText().isBlank()== false && idRV.getText().isBlank()== false && lastNamefield.getText().isBlank()== false && timefield.getText().isBlank()== false && datefield.getValue()!=null )
        {
            if (LocalDate.now().isBefore(datefield.getValue())){
            warninglabele.setText("");
            validateRDV();
            afficherTable();
            }
            else{ warninglabele.setTextFill(Color.web("#ff0000"));
                warninglabele.setText("un rendez vous ne peut pas etre dans le passe ");}

        }else { warninglabele.setTextFill(Color.web("#ff0000"));
             warninglabele.setText("remplisser tout les chanps svp");}

    }
    void validateRDV() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry =" INSERT INTO rendezvous VALUES ('"+idRV.getText()+"','"+namefield.getText()+"','"+lastNamefield.getText()+"','"+datefield.getValue().toString()+"','"+timefield.getText()+"');";
        try {
            if (checkRV(idRV.getText()) == true)
            {warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText(" id existe deja "); return;}
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" BIEN INSERER;) !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void AnnullerAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText("");
        datefield.setValue(null);
        idRV.setText("");
        namefield.setText("");
        lastNamefield.setText("");
        timefield.setText("");
    }

    @FXML
    void rechercheRdv(ActionEvent event) {
        if (rdvRechField.getText().isBlank()==false){
            warninglabele1.setText("");
            remplireTable();}
        else warninglabele1.setText(" svp remplissez tout les champs svp ");
    }

        private void remplireTable() {
        ObservableList<rdvs> rdvs = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String codeRdv = rdvRechField.getText();
            ResultSet rs= query.select("select * from rendezvous where id_RV='"+codeRdv+"'");


            while (rs.next()){

                rdvs.add(new rdvs(rs.getString("id_RV"),rs.getString("nom"),rs.getString("prenom"),
                        rs.getDate("date").toLocalDate(),rs.getString("heure")));
            }
            idRdv.setCellValueFactory(new PropertyValueFactory<>("idRdv"));
            nomRdv.setCellValueFactory(new PropertyValueFactory<>("nomRdv"));
            PrenomRdv.setCellValueFactory(new PropertyValueFactory<>("prenomRdv"));
            dateRdv.setCellValueFactory(new PropertyValueFactory<>("dateRdv"));
            heurRdv.setCellValueFactory(new PropertyValueFactory<>("heurRdv"));


            rdvTable.setItems(rdvs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void afficherTable(){
        ObservableList<rdvs> rdvs = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs= query.select("select * from rendezvous");

            while (rs.next()){

                rdvs.add(new rdvs(rs.getString("id_RV"),rs.getString("nom"),rs.getString("prenom"),
                        rs.getDate("date").toLocalDate(),rs.getString("heure")));
            }
            idRdv.setCellValueFactory(new PropertyValueFactory<>("idRdv"));
            nomRdv.setCellValueFactory(new PropertyValueFactory<>("nomRdv"));
            PrenomRdv.setCellValueFactory(new PropertyValueFactory<>("prenomRdv"));
            dateRdv.setCellValueFactory(new PropertyValueFactory<>("dateRdv"));
            heurRdv.setCellValueFactory(new PropertyValueFactory<>("heurRdv"));


            rdvTable.setItems(rdvs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    int index = -1;
    String rowToDelete;

    @FXML
    void getSelected(MouseEvent event) {
        index = rdvTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        rowToDelete= idRdv.getCellData(index).toString();

        idRV.setText(idRdv.getCellData(index).toString());
        namefield.setText(nomRdv.getCellData(index).toString());
        lastNamefield.setText(PrenomRdv.getCellData(index).toString());
        datefield.setValue(dateRdv.getCellData(index));
        timefield.setText(heurRdv.getCellData(index).toString());
    }

    @FXML
    void modifRdvApplied(ActionEvent event) {
        if (idRV.getText().equals(rowToDelete)){
            if (LocalDate.now().isBefore(datefield.getValue())){
                warninglabele.setText("");
                modifApply();
                afficherTable();
            }
            else{warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText("Un rendez vous ne peut pas etre dans le passe  ");}
        }
        else {warninglabele.setTextFill(Color.web("#ff0000"));
           warninglabele.setText("vous nw pouvez pas modifier le id d'un rendez vous ");}
    }
    @FXML
    private void modifApply(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="UPDATE rendezvous SET nom='"+namefield.getText()+"' , prenom='"+lastNamefield.getText()+"' , date='"+datefield.getValue().toString()+"', heure='"+timefield.getText()+"' WHERE id_RV='"+idRV.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" MODIFIER   😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }

    }

    @FXML
    void supRdvApplied(ActionEvent event) {
        supprimApplied();
        afficherTable();
    }

    private void supprimApplied(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="DELETE FROM rendezvous WHERE id_RV='"+rowToDelete+"'";
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
    private ArrayList<String> fill() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Query q = new Query();
        ResultSet result = q.select("SELECT id_RV FROM rendezvous ");

        while(result.next()){
            list.add(result.getString("id_RV"));
        }
        return list;
    }

    private boolean checkRV(String RVid) throws SQLException {
        ArrayList<String> arr = fill();
        for(String s: arr)
            if (s.equals(RVid)) return true;
        return false;
    }

    @FXML
    void rechercheRdvDate(ActionEvent event) {
        if (datefield2.getValue()!=null){
            warninglabele1.setText(" ");
        remplireTable1();}
        else warninglabele1.setText("SVP ajouter une date pour la recherech ");
    }

    private void remplireTable1() {
        ObservableList<rdvs> rdvs = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            LocalDate dateToSearch = datefield2.getValue();
            ResultSet rs= query.select("select * from rendezvous where date='"+dateToSearch+"'");


            while (rs.next()){

                rdvs.add(new rdvs(rs.getString("id_RV"),rs.getString("nom"),rs.getString("prenom"),
                        rs.getDate("date").toLocalDate(),rs.getString("heure")));
            }
            idRdv.setCellValueFactory(new PropertyValueFactory<>("idRdv"));
            nomRdv.setCellValueFactory(new PropertyValueFactory<>("nomRdv"));
            PrenomRdv.setCellValueFactory(new PropertyValueFactory<>("prenomRdv"));
            dateRdv.setCellValueFactory(new PropertyValueFactory<>("dateRdv"));
            heurRdv.setCellValueFactory(new PropertyValueFactory<>("heurRdv"));


            rdvTable.setItems(rdvs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
