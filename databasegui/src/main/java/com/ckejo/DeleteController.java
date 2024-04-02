package com.ckejo;

import java.io.IOException;
import javafx.fxml.FXML;

public class DeleteController 
{
    @FXML
    private void switchToPrimary() throws IOException
    {
        App.setRoot("delete");
    }
}