module project.databasegui
{
    requires java.sql;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires mysql.connector.j;
    requires annotations;

    opens project.databasegui to javafx.fxml;
    opens project.databasegui.tableitems to javafx.fxml;
    exports project.databasegui;
    exports project.databasegui.tableitems;
}