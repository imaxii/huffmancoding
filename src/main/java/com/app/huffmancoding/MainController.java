package com.app.huffmancoding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane borderPane;
    private Parent encodeParent;
    private Parent decodeParent;

    @FXML
    public void initialize() {
        try {
            encodeParent = FXMLLoader.load(getClass().getResource("encode.fxml"));
            decodeParent = FXMLLoader.load(getClass().getResource("decode.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        encode();
    }

    @FXML
    private void encode() {
        if (encodeParent != null) {
            borderPane.setCenter(encodeParent);
        }
    }

    @FXML
    private void decode() {
        if (decodeParent != null) {
            borderPane.setCenter(decodeParent);
        }
    }
}
