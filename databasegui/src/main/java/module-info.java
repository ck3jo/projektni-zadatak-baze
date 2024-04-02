module com.ckejo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ckejo to javafx.fxml;
    exports com.ckejo;
}
