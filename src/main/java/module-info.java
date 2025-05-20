module com.app.huffmancoding {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.app.huffmancoding to javafx.fxml;
    exports com.app.huffmancoding;
}