package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ClassroomGUI {

    @FXML
    private Pane mainPane;

    @FXML
    void actStart(ActionEvent event) {

    }

    @FXML
    private TextField tfUserLogin;

    @FXML
    private PasswordField pfLogin;

    @FXML
    private PasswordField pfSignup;

    @FXML
    private TextField tfUserSignup;

    @FXML
    private TextField tfProfilePhoto;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private CheckBox cbSoftware;

    @FXML
    private CheckBox cbTelematic;

    @FXML
    private CheckBox cbIndustrial;

    @FXML
    private DatePicker dpSignup;

    @FXML
    private ChoiceBox<?> chbSignup;

    @FXML
    void actLogin(ActionEvent event) {

    }

    @FXML
    void actSignup(ActionEvent event) {

    }

    @FXML
    void actBackSignin(ActionEvent event) {

    }

    @FXML
    void actBrowsePhoto(ActionEvent event) {

    }

    @FXML
    void actCreateAccount(ActionEvent event) {

    }
}
