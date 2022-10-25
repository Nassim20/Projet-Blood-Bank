package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class SingInController {

    @FXML
    private JFXTextField newUser;
    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private  JFXButton createAccount;
    @FXML
    private Label warninglabele;
    @FXML
    private Label passwordmatch;
    @FXML
    private  JFXButton closeButton;

    public void createAccountAction(javafx.event.ActionEvent actionEvent) {
        warninglabele.setText(" ");
        if (newUser.getText().isBlank()== false  && password1.getText().isBlank() ==false && password2.getText().isBlank() ==false ) {
            warninglabele.setText("");
            if(password1.getText().equals(password2.getText())){
                passwordmatch.setText("");
                validateRegister();




            }else { passwordmatch.setText(" please  put the same password  !!!");

            }
        } else { warninglabele.setText(" please fill the fields ;) !!!");

        }

    }
    void validateRegister() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();
        String querry =" INSERT INTO USERS VALUES ('"+newUser.getText()+"','"+password2.getText()+"');";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(querry);
            warninglabele.setText(" inscription terminée !!!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    void gotologin(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene=new Scene(root, 1029, 572);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}