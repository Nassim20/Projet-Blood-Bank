package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.events;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class EventController implements Initializable {
    @FXML
    private JFXButton homeButton;
    @FXML
    private AnchorPane anchorRoot2;
    @FXML
    private StackPane parentContainer2;
    @FXML
    private JFXButton ajouterButton;
    @FXML
    private JFXButton annullerButton;
    @FXML
    private Label warninglabele;
    @FXML
    private Label warninglabele1;
    @FXML
    private JFXTextField codeEvent;
    @FXML
    private JFXTextField nomRes;
    @FXML
    private JFXTextField nomEvent;
    @FXML
    private JFXTextField location;
    @FXML
    private JFXComboBox<String> TypeField;
    @FXML
    private JFXTextField timeField;
    @FXML
    private DatePicker dateField;
    @FXML
    private JFXTextField coderech;
    @FXML
    private JFXButton rechbutt;
    @FXML
    private TableView<events> tableEvent;
    @FXML
    private TableColumn<events, String> codeclm;

    @FXML
    private TableColumn<events, String> nomclm;

    @FXML
    private JFXButton supEventButt;

    @FXML
    private TableColumn<events, String> rspclmn;

    @FXML
    private TableColumn<events, String> locationclm;

    @FXML
    private TableColumn<events, String> typeclm;

    @FXML
    private TableColumn<events, LocalDate> dateclm;

    @FXML
    private TableColumn<events, String> heureclm;

    @FXML
    private JFXButton modifEventButt;
    @FXML
    private  JFXButton closeButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeField.setItems(FXCollections.observableArrayList(
                "Sensibilisation", "Organisation dons"
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

    public void createEventAction(javafx.event.ActionEvent actionEvent){
        warninglabele.setText("");
        if (codeEvent.getText().isBlank()== false && nomRes.getText().isBlank()== false && nomEvent.getText().isBlank()== false && location.getText().isBlank()== false && timeField.getText().isBlank()== false && TypeField.getValue()!=null && dateField.getValue()!=null)
        {
            if (LocalDate.now().isBefore(dateField.getValue())){
            warninglabele.setText("");
            validateEvent();
            afficherTable();
            }
            else {warninglabele.setTextFill(Color.web("#ff0000"));
                warninglabele.setText("an Event can't be in the Past ");}

        }else { warninglabele.setTextFill(Color.web("#ff0000"));
            warninglabele.setText("please fill all the fields ");}
    }

    void validateEvent() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String Type = TypeField.getSelectionModel().getSelectedItem();
        String querry ="INSERT INTO Event VALUES ('"+codeEvent.getText()+"','"+nomEvent.getText()+"','"+nomRes.getText()+"','"+location.getText()+"','"+Type+"','"+dateField.getValue().toString()+"','"+timeField.getText()+"');";
        try { if (checkid(codeEvent.getText()) == true)
           {warninglabele.setTextFill(Color.web("#ff0000"));
           warninglabele.setText(" id existe deja "); return;}
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" IT IS INSERTED   😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void AnnullerAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText("");
        dateField.setValue(null);
        codeEvent.setText("");
        nomEvent.setText("");
        nomRes.setText("");
        location.setText("");
        TypeField.setValue("");
        timeField.setText("");

    }

    @FXML
    void rechbuttonAction (ActionEvent event) {

        if (coderech.getText().isBlank()== false ) {
            remplireTable();
        } else { warninglabele1.setText(" please fill the fields ;) !!!");

        }
    }


    int index = -1;
    private String rowToDelete;
    private String rowtoModify;

    @FXML
    void getSelected(MouseEvent event) {
        index = tableEvent.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        rowToDelete= codeclm.getCellData(index).toString();
        rowtoModify= codeclm.getCellData(index);

        codeEvent.setText(codeclm.getCellData(index).toString());
        nomEvent.setText(nomclm.getCellData(index).toString());
        nomRes.setText(rspclmn.getCellData(index).toString());
        dateField.setValue(dateclm.getCellData(index));
        TypeField.setValue(typeclm.getCellData(index));
        location.setText(locationclm.getCellData(index).toString());
        timeField.setText(heureclm.getCellData(index).toString());
    }

    private void afficherTable(){
        ObservableList<events> events = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            ResultSet rs = query.select("select * from event");

            while (rs.next()) {

                events.add(new events(rs.getString("id_Event"), rs.getString("nom_Event"), rs.getString("nom_Res"),
                        rs.getString("location"), rs.getString("type_Event"),
                        rs.getDate("date_Event").toLocalDate(), rs.getString("heur_Event")));
            }
            codeclm.setCellValueFactory(new PropertyValueFactory<>("id_Event"));
            nomclm.setCellValueFactory(new PropertyValueFactory<>("nom_Event"));
            rspclmn.setCellValueFactory(new PropertyValueFactory<>("nom_Res"));
            locationclm.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeclm.setCellValueFactory(new PropertyValueFactory<>("type_event"));
            dateclm.setCellValueFactory(new PropertyValueFactory<>("date_event"));
            heureclm.setCellValueFactory(new PropertyValueFactory<>("heur_event"));


            tableEvent.setItems(events);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void remplireTable() {
        ObservableList<events> events = FXCollections.observableArrayList();
        try {
            Query query = new Query();
            String codeEventRech = coderech.getText();
            ResultSet rs = query.select("select * from event  where id_Event='"+codeEventRech+ "'");

            while (rs.next()) {

                events.add(new events(rs.getString("id_Event"), rs.getString("nom_Event"), rs.getString("nom_Res"),
                        rs.getString("location"), rs.getString("type_Event"),
                        rs.getDate("date_Event").toLocalDate(), rs.getString("heur_Event")));
            }
            codeclm.setCellValueFactory(new PropertyValueFactory<>("id_Event"));
            nomclm.setCellValueFactory(new PropertyValueFactory<>("nom_Event"));
            rspclmn.setCellValueFactory(new PropertyValueFactory<>("nom_Res"));
            locationclm.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeclm.setCellValueFactory(new PropertyValueFactory<>("type_event"));
            dateclm.setCellValueFactory(new PropertyValueFactory<>("date_event"));
            heureclm.setCellValueFactory(new PropertyValueFactory<>("heur_event"));


            tableEvent.setItems(events);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void modifEvent(ActionEvent event) {
        if (codeEvent.getText().equals(rowtoModify)){
            if (LocalDate.now().isBefore(dateField.getValue())){
                warninglabele.setText("");
                modifApply();
                afficherTable();}
            else {warninglabele.setTextFill(Color.web("#ff0000"));
                warninglabele.setText("an Event can't be in the Past ");}}
        else{warninglabele.setTextFill(Color.web("#ff0000"));
           warninglabele.setText("You are not Allowed to Modify the code of the event");}
    }

    private void modifApply(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String Type = TypeField.getSelectionModel().getSelectedItem();
        String querry ="UPDATE Event SET nom_Event='"+nomEvent.getText()+"' , nom_Res='"+nomRes.getText()+"' , location='"+location.getText()+"' , type_event='"+Type+"', date_event='"+dateField.getValue().toString()+"', heur_event='"+timeField.getText()+"' WHERE id_Event='"+codeEvent.getText()+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
            warninglabele.setText(" MODIFIED   😉 !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    @FXML
    void supprimerrEvent(ActionEvent event) {
        supprimApplied();
        afficherTable();
    }

    private void supprimApplied(){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry ="DELETE FROM Event WHERE id_Event='"+rowToDelete+"'";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setTextFill(Color.web("#AFC3FE"));
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

    private ArrayList<String> fill() throws SQLException {
        ArrayList<String> list = new ArrayList<String>();
        Query q = new Query();
        ResultSet result = q.select("SELECT id_Event FROM event ");

        while(result.next()){
            list.add(result.getString("id_Event"));
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
