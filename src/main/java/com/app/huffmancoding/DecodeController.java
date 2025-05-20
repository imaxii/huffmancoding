package com.app.huffmancoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class DecodeController {
    @FXML
    private TableView<CharacterCode> tableView;
    @FXML
    private TextField charaterField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField bitStringField;
    @FXML
    private TableColumn<CharacterCode, Character> characterColumn;
    @FXML
    private TableColumn<CharacterCode, String> codeColumn;

    private ObservableList<CharacterCode> data = FXCollections.observableArrayList();
    @FXML
    private TextArea decodedBitString;

    private HuffmanCoding huffmanCoding = new HuffmanCoding();

    @FXML
    public void initialize() {
        characterColumn.setCellValueFactory(new PropertyValueFactory<>("character"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        tableView.setItems(data);
    }

    @FXML
    private void addCharacterCode() {
        char character = charaterField.getText().charAt(0);
        String code = codeField.getText();
        data.add(new CharacterCode(character, code));
        charaterField.clear();
        codeField.clear();
        charaterField.requestFocus();
    }

    public void decode(MouseEvent mouseEvent) {
        Map<String,Character> table = new HashMap<String,Character>();
        for (CharacterCode item : tableView.getItems()) {
            table.put(item.getCode(),item.getCharacter());
        }

        decodedBitString.setText(huffmanCoding.decode(bitStringField.getText(),table));
    }
}