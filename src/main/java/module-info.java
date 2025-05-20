module com.app.huffmancoding {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.huffmancoding to javafx.fxml;
    exports com.app.huffmancoding;
}