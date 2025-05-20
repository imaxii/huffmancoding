package com.app.huffmancoding;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HuffmanApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)// Optional if you don't need JavaFX's default theme, still recommended though
                .themes(MaterialFXStylesheets.forAssemble(true)) // Adds the MaterialFX's default theme. The boolean argument is to include legacy controls
                .setDeploy(true) // Whether to deploy each theme's assets on a temporary dir on the disk
                .setResolveAssets(true) // Whether to try resolving @import statements and resources urls
                .build() // Assembles all the added themes into a single CSSFragment (very powerful class check its documentation)
                .setGlobal(); // Finally, sets the produced stylesheet as the global User-Agent stylesheet


        FXMLLoader fxmlLoader = new FXMLLoader(HuffmanApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("codificación Huffman");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}