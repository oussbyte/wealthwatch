package com.example.wealthwatch;

import com.example.wealthwatch.Models.ConnectionToData;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class signupController implements Initializable {
    @FXML
    private Button login_btn;
    @FXML
    private TextField username_fld;

    @FXML
    private TextField fName_fld;

    @FXML
    private TextField lName_fld;

    @FXML
    private TextField passwd_fld;

    @FXML
    private TextField confirm_passwd_fld;

    @FXML
    private TextField email_fld;

    @FXML
    private Label error_fld;

    private final String regexMailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username_fld.setOnKeyTyped(event -> {
            try {
                if (username_fld.getText().matches("")) {
                    //pass
                    //si le champ est vide on ne fait rien
                } else if (isUsernameValid()) {
                    username_fld.setStyle("-fx-border-color: #0ac40d; -fx-focus-color: #0ac40d;");
                    error_fld.setVisible(false);
                } else {
                    username_fld.setStyle("-fx-border-color: #cf2317; -fx-focus-color: #cf2317;");
                    error_fld.setVisible(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        fName_fld.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                try {
                    if (fName_fld.getText().matches("")){
                        //pass
                        //si le champ est vide on ne fait rien
                    } else if (isNameFieldValid()) {
                        fName_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                        error_fld.setVisible(false);
                    }else{
                        fName_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #cf2317;");
                        error_fld.setVisible(true);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        lName_fld.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                try {
                    if (lName_fld.getText().matches("")){
                        //pass
                        //si le champ est vide on ne fait rien
                    } else if (isNameFieldValid()) {
                        lName_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                        error_fld.setVisible(false);
                    }else{
                        lName_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #cf2317;");
                        error_fld.setVisible(true);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });




        confirm_passwd_fld.setOnKeyTyped(event -> {
            try {
                if (isPasswdFieldValid()) {
                    passwd_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                    confirm_passwd_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                    error_fld.setVisible(false);
                } else {
                    passwd_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                    confirm_passwd_fld.setStyle("-fx-text-box-border: #cf2317; -fx-focus-color: #cf2317;");
                    error_fld.setVisible(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        email_fld.setOnKeyTyped(event -> {
            try {
                if (email_fld.getText().matches("")) {
                    //pass
                    //si le champ est vide on ne fait rien
                } else if (iseMailFieldValid()) {


                    email_fld.setStyle("-fx-text-box-border: #0ac40d; -fx-focus-color: #0ac40d;");
                    error_fld.setVisible(false);
                } else {
                    email_fld.setStyle("-fx-text-box-border: #cf2317; -fx-focus-color: #cf2317;");
                    error_fld.setVisible(true);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    //functions
    private boolean isUsernameValid() throws IOException, SQLException {
        String input = username_fld.getText();

        if (input==null || input.matches("") || ConnectionToData.exists(input)){
            return false;
        }
        return true;
    }

    private boolean isNameFieldValid() throws IOException {
        String input = fName_fld.getText();

        if (input==null || input.matches("")){
            return false;
        }
        return true;
    }

    private boolean isPasswdFieldValid() throws IOException {
        String input = passwd_fld.getText();
        String input1 = confirm_passwd_fld.getText();

        if (input==null || input.matches("") || !input.matches(input1)){
            return false;
        }
        return true;
    }


    public boolean iseMailFieldValid() throws IOException{
        return Pattern.compile(regexMailPattern)
                .matcher(email_fld.getText())
                .matches();
    }

    public void registrationclicked_btn(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {

        if (isUsernameValid() && isNameFieldValid() && isPasswdFieldValid() && iseMailFieldValid()
                && ConnectionToData.signup(username_fld.getText(),  fName_fld.getText(), lName_fld.getText(), passwd_fld.getText(), email_fld.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous avez été inscrit avec succès", ButtonType.OK);
            alert.showAndWait();
            Stage stage = new Stage() ;
            FXMLLoader fxmlLoader = new FXMLLoader(loginpageController.class.getResource("dashboardapp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

            Stage st = (Stage) username_fld.getScene().getWindow();
            st.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite\nVérifiez que vous avez bien remplis tout les champs\nVérifiez également de respecter les messages d'erreurs", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    protected void alreadyhaveAccount() throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage st = (Stage) login_btn.getScene().getWindow();
        st.close();
    }


}