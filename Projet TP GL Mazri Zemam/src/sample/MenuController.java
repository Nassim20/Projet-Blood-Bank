package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable {

    @FXML
    private  JFXButton eventButton;
    @FXML
    private  JFXButton RVbutton;
    @FXML
    private  JFXButton historiqueButton;
    @FXML
    private  JFXButton donneurButton;
    @FXML
    private  JFXButton DonsButton;
    @FXML
    private  JFXButton demandeButton;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    @FXML
    private  JFXButton stockButton;
    @FXML
    private  JFXButton closeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    public void closeAction (ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void loadEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/event.fxml"));
        Scene scene = eventButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void loadRV(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/Rendezvous.fxml"));
        Scene scene = RVbutton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    @FXML
    private void loadDons(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/Dons.fxml"));
        Scene scene = DonsButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    @FXML
    private void loadhistorique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/Historique.fxml"));
        Scene scene = historiqueButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    @FXML
    private void loadDonneur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/Donneurs.fxml"));
        Scene scene = donneurButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    @FXML
    private void loadStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/gestion_de_stock.fxml"));
        Scene scene = stockButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void loadDemmande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/demandes.fxml"));
        Scene scene = demandeButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

}
