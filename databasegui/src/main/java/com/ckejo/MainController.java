package com.ckejo;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController 
{
    @FXML
    private void loadAddData() throws IOException
    {
        App.setRoot("add");
    }

    @FXML
    private void loadDeleteData() throws IOException
    {
        App.setRoot("delete");
    }

    @FXML
    private void loadEditData() throws IOException
    {
        App.setRoot("edit");
    }
}
