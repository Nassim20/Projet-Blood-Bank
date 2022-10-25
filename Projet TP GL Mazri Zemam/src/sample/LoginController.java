package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signup;

    @FXML
    private Label warninglable;
    @FXML
    private  JFXButton closeButton;


    @FXML
    void gotosignUp(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/signIn.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene=new Scene(root, 1029, 572);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginButtonAction (ActionEvent event)
    {

        if (user.getText().isBlank()== false  && password.getText().isBlank() ==false ) {
            ValidateLogIn();
        } else { warninglable.setText(" please fill the fields ;) !!!");

        }
    }
   void ValidateLogIn() {
       DataBaseConnection connectNow = new DataBaseConnection();
       Connection connectDB = connectNow.getConnection();
       String querry = " SELECT count(1) FROM USERS WHERE USERNAME = '" +user.getText()+ "' AND PASSWORD = '"+password.getText()+"'; ";
       try {
           Statement statement = connectDB.createStatement();
           ResultSet queryResult =  statement.executeQuery(querry);
           while (queryResult.next()){
               if (queryResult.getInt(1)==1){
                   warninglable.setText(" "); gotoMenu();
               }else{ warninglable.setText(" Sorry but you don't have an account  :( !!!");

               }

           }

       }catch (Exception e) {
           e.printStackTrace();
           e.getCause();
       }

   }
    void gotoMenu(){
        try { //please change this so that the log in tero7 and the menu comes in it place ma 3ereftch how
            Parent root = FXMLLoader.load(getClass().getResource("../ressources/Menu.fxml"));
            Stage MenuStage = new Stage();
            MenuStage.initStyle(StageStyle.UNDECORATED);
            MenuStage.setScene(new Scene(root, 1029, 572));
            MenuStage.show();
            MenuStage.setResizable(false);
            Stage stage = (Stage) signIn.getScene().getWindow();
            stage.close();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}