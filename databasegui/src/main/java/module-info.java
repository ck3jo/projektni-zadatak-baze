module com.ckejo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ckejo to javafx.fxml;
    exports com.ckejo;
}
