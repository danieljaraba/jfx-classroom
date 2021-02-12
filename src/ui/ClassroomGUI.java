package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Classroom;

import java.io.IOException;

public class ClassroomGUI {

    private FXMLLoader loader;
    private Classroom classroom;
    private Alert alert;

    public ClassroomGUI(){
        classroom = new Classroom();
    }

    @FXML
    private Pane mainPane;

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
    private ChoiceBox<String> chbSignup;

    @FXML
    void actLogin(ActionEvent event) {

    }

    @FXML
    void actStart(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Pane pnLogin = loader.load();
        mainPane.getChildren().setAll(pnLogin);
    }

    @FXML
    void actSignup(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("register.fxml"));
        loader.setController(this);
        Pane pnSignup = loader.load();
        mainPane.getChildren().setAll(pnSignup);

        chbSignup.getItems().add("Opera");
        chbSignup.getItems().add("Brave");
        chbSignup.getItems().add("Chrome");
        chbSignup.getItems().add("Mozilla");
    }

    @FXML
    void actBackSignin(ActionEvent event) {

    }

    @FXML
    void actBrowsePhoto(ActionEvent event) {

    }

    @FXML
    void actCreateAccount(ActionEvent event) throws Exception{
        try{
            String user = tfUserSignup.getText();
            String password = pfSignup.getText();
            String photoBrwsr = tfProfilePhoto.getText();
            RadioButton rbSelected = (RadioButton)gender.getSelectedToggle();
            String txtGender = rbSelected.getText();
            String career = "";
            if(cbSoftware.isSelected()){
                career += "Software Engineering ";
            } if(cbTelematic.isSelected()){
                career += "Telematic Engineering ";
            } if(cbIndustrial.isSelected()){
                career += "Industrial Engineering ";
            }
            String birthday = dpSignup.getValue().toString();
            String favoriteBrwsr = chbSignup.getValue();

            classroom.addUser(user, password, photoBrwsr, txtGender, career, birthday, favoriteBrwsr);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Account successfully created.");
            alert.showAndWait();
        } catch(Exception e){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect information.");
            alert.showAndWait();
        }
    }
}
