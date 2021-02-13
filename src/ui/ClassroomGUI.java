package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    private TableView<UserAccount> idxTable;

    @FXML
    private ImageView idxImage;

    @FXML
    private Label idxUsername;

    @FXML
    private TableColumn<UserAccount, String> tblColumnUsername;

    @FXML
    private TableColumn<UserAccount, String> tblColumnGender;

    @FXML
    private TableColumn<UserAccount, String> tblColumnCareer;

    @FXML
    private TableColumn<UserAccount, String> tblColumnBirthday;

    @FXML
    private TableColumn<UserAccount, String> tblColumnBrowser;

    @FXML
    void actLogin(ActionEvent event) throws IOException{
        String logUser = tfUserLogin.getText();
        String logPassword = pfLogin.getText();
        ArrayList<UserAccount> users = classroom.getAccounts();
        boolean found = false;
        int index = 0;
        for(int i = 0; i<users.size(); i++){
            if(users.get(i).getUser().equals(logUser) && users.get(i).getPassword().equals(logPassword)){
                found = true;
                index = i;
            }
        }
        if(found){
            loader = new FXMLLoader(getClass().getResource("account-list.fxml"));
            loader.setController(this);
            Pane pnAccountList = loader.load();
            mainPane.getChildren().setAll(pnAccountList);

            idxUsername.setText(logUser);

            System.out.println(users.get(index).getPhotoUrl());

            Image image = new Image(users.get(index).getPhotoUrl());

            idxImage.setImage(image);

            initializeTableView();
        }

    }

    public void initializeTableView(){
        ObservableList<UserAccount> observableList = FXCollections.observableList(classroom.getAccounts());

        idxTable.setItems(observableList);

        tblColumnUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("user"));
        tblColumnGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender"));
        tblColumnCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
        tblColumnBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
        tblColumnBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("favoriteBrwsr"));
    }

    @FXML
    public void actStart(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Pane pnLogin = loader.load();
        mainPane.getChildren().setAll(pnLogin);
    }

    @FXML
    public void actSignup(ActionEvent event) throws IOException{
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
    public void actBackSignin(ActionEvent event) throws IOException{
        loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setController(this);
        Pane pnLogin = loader.load();
        mainPane.getChildren().clear();
        mainPane.getChildren().setAll(pnLogin);
    }

    @FXML
    public void actBrowsePhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select the image");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", ".jpg", ".gif"));

        File file = chooser.showOpenDialog(null);
        if(file != null){
            tfProfilePhoto.setText(file.toURI().toString());
        }else{
            tfProfilePhoto.setText("Invalid file");
        }
    }

    @FXML
    public void actCreateAccount(ActionEvent event) throws Exception{
        try{
            String user = null;
            if(!tfUserSignup.getText().equals("")){
                user = tfUserSignup.getText();
            }
            String password = "";
            if(!pfSignup.getText().equals("")){
                password = pfSignup.getText();
            }
            String photoBrwsr = "";
            if(!(tfProfilePhoto.getText().equals("Invalid file") || tfProfilePhoto.getText().equals(""))){
                photoBrwsr = tfProfilePhoto.getText();
            }
            RadioButton rbSelected = (RadioButton)gender.getSelectedToggle();
            String txtGender = rbSelected.getText();
            String career = "";
            if(cbSoftware.isSelected()){
                career += "Software Engineering ";
            } if(cbTelematic.isSelected()){
                career += "Telematic Engineering ";
            } if(cbIndustrial.isSelected()){
                career += "Industrial Engineering ";
            } else{
                errorAlert();
            }
            String birthday = dpSignup.getValue().toString();
            String favoriteBrwsr = chbSignup.getValue();

            if(!(tfUserSignup.getText().equals("")) && !(pfSignup.getText().equals("")) && !(tfProfilePhoto.getText().equals("Invalid file") || tfProfilePhoto.getText().equals("")) && !(career.equals("")) && !(favoriteBrwsr.equals(""))){
                classroom.addUser(user, password, photoBrwsr, txtGender, career, birthday, favoriteBrwsr);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Account successfully created.");
                alert.showAndWait();

                actSignup(event);
            }

        } catch(Exception e){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect information.");
            alert.showAndWait();
        }
    }

    public void errorAlert(){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect information.");
        alert.showAndWait();
    }

    @FXML
    public void actLogout(ActionEvent event) throws IOException {
        actStart(event);
    }
}
