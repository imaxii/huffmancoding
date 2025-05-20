package com.app.huffmancoding;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacterCode {
    private final SimpleObjectProperty<Character> character;
    private final SimpleStringProperty code;

    public CharacterCode(Character character, String code) {
        this.character = new SimpleObjectProperty<>(character);
        this.code = new SimpleStringProperty(code);
    }

    public Character getCharacter() {
        return character.get();
    }

    public void setCharacter(Character character) {
        this.character.set(character);
    }

    public SimpleObjectProperty<Character> characterProperty() {
        return character;
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }
}