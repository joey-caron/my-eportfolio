package com.SNHUInvestments.SNHU;

// TextAreaOutput class for SNHU Investments
//Created by Joseph Caron

import javax.swing.JTextArea;
import java.io.OutputStream;
import java.io.IOException;


// This allows output to be written to the output
// box in the UI to give the user feedback
public class TextAreaOutput extends OutputStream {
    private final JTextArea textArea;

    public TextAreaOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // Append each character to the JTextArea
        textArea.append(String.valueOf((char) b));
    }
}
