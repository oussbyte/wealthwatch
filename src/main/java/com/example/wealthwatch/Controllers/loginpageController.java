package com.example.wealthwatch;

import com.example.wealthwatch.Models.ConnectionToData;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class loginpageController {
    public TextField username_fld;
    public TextField password_fld;
    public Button login_btn;
    public Button register_btn;
    public Label error_log;

    @FXML
    protected void loginClick_btn() throws SQLException, IOException {
        ConnectionToData.connect();

        if (ConnectionToData.verifyCredentials(username_fld.getText(), password_fld.getText())) {
            System.out.println("Connecté");

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dashboardapp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

            Stage st = (Stage) login_btn.getScene().getWindow();
            st.close();


        } else {
            System.out.println("Non connecté");

            error_log.setVisible(true);
        }


    }

    @FXML
    protected void noAccountonClick() throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("signuppage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage st = (Stage) login_btn.getScene().getWindow();
        st.close();
    }

}