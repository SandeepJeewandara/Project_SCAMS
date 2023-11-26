package com.example.scams_ood;

import javafx.scene.control.TextField;

public class Validations {
    public static void addTextLimiter(final TextField textField, final int maxLength) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField.getText().length() > maxLength) {
                textField.setText(textField.getText(0, maxLength));
            }
        });
    }

    public static void validInput(TextField text, String validation) {
        String input = text.getText();
        if (!input.matches(validation)) {
            text.clear();
        }
    }
}
