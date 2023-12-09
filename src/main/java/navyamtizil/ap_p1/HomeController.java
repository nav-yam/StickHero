package navyamtizil.ap_p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public Button noPremium;

    public AnchorPane BuyCherriesPane;
    public Text numCherryLabel;
    public AnchorPane HTPpane;
    public Button closeHTPpane;
    public AnchorPane volumePANE;
    public Slider volumeSlider;
    public AnchorPane skinShop;
    boolean premiumUser = false;
    int NUM_CHERRIES = 0;
    Image player;

    @FXML
    public Label VolumeLabel;
    public Label resultLabel;
    public Group character;
    public AnchorPane premiumWindow;
    public Text premiumLabel;

    @FXML
    private Label popUpMenuLabel;


    public void resetAllLabels() {
        VolumeLabel.setText("");
        popUpMenuLabel.setText("");
    }

    @FXML
    private void HowToPlay(ActionEvent event) {
        resetAllLabels();
        HTPpane.setVisible(true);
    }

    @FXML
    private void Volume(ScrollEvent event) {
        resetAllLabels();

        volumePANE.setVisible(true);
        volumeSlider.setMax(100);
        volumeSlider.setMin(0);
        double volume = volumeSlider.getValue();
        VolumeLabel.setText("Volume selected" + volume);
    }

    @FXML
    private void NoADS(ActionEvent event) {
        resetAllLabels();
        if (premiumUser) return;
        premiumWindow.setVisible(true);
        premiumWindow.setDisable(false);
        premiumWindow.setOpacity(1);

    }

    @FXML
    private void Skin(ActionEvent event) {
        resetAllLabels();
        skinShop.setVisible(!skinShop.isVisible());
    }
    @FXML
    private void BuyCheries(ActionEvent event) {
        resetAllLabels();
        BuyCherriesPane.setVisible(true);
    }
    @FXML
    private void Play(ActionEvent event) throws IOException {
        resetAllLabels();
        popUpMenuLabel.setText("Play selected");

//
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("Gameplay.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 676, 676);
        ((Stage) resultLabel.getScene().getWindow()).setScene(scene);

    }
    public void ExitGame(ActionEvent actionEvent) {
        System.exit(69);
    }

    public void closePremiumWindow(ActionEvent actionEvent) {
        premiumWindow.setDisable(true);
        premiumWindow.setOpacity(0);
        premiumWindow.setVisible(false);
    }

    public void buyPremium(ActionEvent actionEvent){
        premiumLabel.setText("Rich man !");
        premiumUser = true;
        closePremiumWindow(actionEvent);
    }

    public void Buy1(ActionEvent actionEvent) {
        NUM_CHERRIES += 1;
        BuyCherriesPane.setVisible(false);
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);
    }
    public void Buy2(ActionEvent actionEvent) {
        NUM_CHERRIES += 2;
        BuyCherriesPane.setVisible(false);
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);

    }
    public void Buy5(ActionEvent actionEvent) {
        NUM_CHERRIES += 5;
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);
        BuyCherriesPane.setVisible(false);
    }
    public void Buy10(ActionEvent actionEvent) {
        NUM_CHERRIES += 10;
        BuyCherriesPane.setVisible(false);
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);

    }
    public void Buy25(ActionEvent actionEvent) {
        NUM_CHERRIES += 25;
        BuyCherriesPane.setVisible(false);
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);

    }
    public void Buy50(ActionEvent actionEvent) {
        NUM_CHERRIES += 50;
        BuyCherriesPane.setVisible(false);
        numCherryLabel.setText("Cherries : " + NUM_CHERRIES);

    }

    public void closeHTPpane(ActionEvent actionEvent) {
        HTPpane.setVisible(false);
    }

    public void Skin1(MouseEvent mouseEvent) {
//        player = new Image(String.valueOf((getClass().getResource("/IMAGES/hero.png"))));
//        ImageView iv = new ImageView(player);
//        skinShop.getChildren().add(iv);
    }
}