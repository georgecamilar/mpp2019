package springclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import springclient.RequestBot;

import java.io.IOException;


public class LoginFXController {


    private RequestBot requester;
    private Stage currentStage;
    @FXML
    Label label;
    @FXML
    TextField usernameTextField;
    @FXML
    Button signupButton;
    @FXML
    PasswordField passwordTextField;
    @FXML
    Button loginButton;

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public LoginFXController() {
        requester = new RequestBot();
    }

    @FXML
    public void initialize() {

    }

    public void handleSignUp(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        try {
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            requester.saveUser(u);
        } catch (Exception e) {
            System.out.println(" ");
        }
    }


    public void handleLoginRequest(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();


        try {

            User u = requester.getByUsername(username);

            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            if (u != null && u.getPassword().equals(password)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmls/MainWindow.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Main window");
                    stage.setScene(scene);
                    stage.show();
                    currentStage.close();
                } catch (IOException e) {
                    System.out.println("FXML error" + e.getMessage());
                }
            }
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
