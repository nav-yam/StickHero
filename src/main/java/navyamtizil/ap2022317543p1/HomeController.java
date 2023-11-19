package navyamtizil.ap2022317543p1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    public Label VolumeLabel;
    @FXML
    public Label BuyCheriesLabel;
    public Label resultLabel;

    @FXML
    private Label popUpMenuLabel;

    public void resetAllLabels() {
        VolumeLabel.setText("");
        BuyCheriesLabel.setText("");
        popUpMenuLabel.setText("");
        // Add more labels if needed
    }

    @FXML
    private void Stick(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("Files.Stick selected");
    }

    @FXML
    private void Slash(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("Slash selected");
    }

    @FXML
    private void HowToPlay(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("HowToPlay selected");
    }

    @FXML
    private void Volume(ActionEvent event) {
        resetAllLabels();
        VolumeLabel.setText("Volume selected");
    }

    @FXML
    private void NoADS(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("NoADS selected");
    }

    @FXML
    private void Skin(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("Skin selected");
    }
    @FXML
    private void BuyCheries(ActionEvent event) {
        resetAllLabels();
        BuyCheriesLabel.setText("BuyCheries selected");
    }
    @FXML
    private void Play(ActionEvent event) {
        resetAllLabels();
        popUpMenuLabel.setText("Play selected");
    }

    public void ExitGame(ActionEvent actionEvent) {
        System.exit(69);
    }
}