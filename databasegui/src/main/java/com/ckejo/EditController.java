package com.ckejo;

import java.io.IOException;
import javafx.fxml.FXML;

public class EditController 
{
    @FXML
    private void switchToPrimary() throws IOException
    {
        App.setRoot("edit");
    }
}