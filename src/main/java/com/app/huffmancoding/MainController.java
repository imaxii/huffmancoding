package com.app.huffmancoding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane borderPane;


    @FXML
    public void initialize() {
        loadPage("encode");
    }
    @FXML
    private void encode(){
        loadPage("encode");
    }

    @FXML
    private void decode(){
        loadPage("decode");
    }

    private void loadPage(String page){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}