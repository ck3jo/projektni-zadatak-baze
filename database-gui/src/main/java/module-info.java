module project.databasegui {
    requires java.sql;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens project.databasegui to javafx.fxml;
    exports project.databasegui;
}