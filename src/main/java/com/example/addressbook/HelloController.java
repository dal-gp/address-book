package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    public TextArea termsAndCondtionsTextArea;
    @FXML
    public CheckBox agreeCheckBox;
    @FXML
    public Button nextButton;
    @FXML
    public Button cancelButton;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        termsAndCondtionsTextArea.setText("""
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Eget dolor morbi non arcu risus. Quis lectus nulla at volutpat diam
                ut venenatis tellus in. Feugiat in fermentum posuere urna nec tincidunt
                praesent semper. Turpis tincidunt id aliquet risus feugiat in.
                Libero volutpat sed cras ornare. Facilisi morbi tempus iaculis urna.
                Bibendum est ultricies integer quis auctor. Eu augue ut lectus arcu.
                Tincidunt tortor aliquam nulla facilisi cras fermentum odio eu.
                Gravida neque convallis a cras. Elit ut aliquam purus sit.
                Suspendisse ultrices gravida dictum fusce ut placerat.
                Integer feugiat scelerisque varius morbi enim nunc.
                Amet justo donec enim diam vulputate ut pharetra.
                Sapien pellentesque habitant morbi tristique.
                Lorem sed risus ultricies tristique nulla aliquet.
                Elementum nibh tellus molestie nunc non blandit massa.
                """);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the Address Book Application!");
    }

    @FXML
    public void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        nextButton.setDisable(!accepted);
    }

    @FXML
    public void onNextButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}