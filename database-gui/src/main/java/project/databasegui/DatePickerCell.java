package project.databasegui;

import javafx.scene.control.*;

import java.time.LocalDate;

public class DatePickerCell<T> extends TableCell<T, LocalDate>
{
    private final DatePicker datePicker;

    public DatePickerCell(TableColumn<T, LocalDate> column)
    {
        this.datePicker = new DatePicker();
        this.datePicker.editableProperty().bind(column.editableProperty());
        this.datePicker.disableProperty().bind(column.editableProperty().not());
        this.datePicker.setOnShowing(event -> {
            final TableView<T> tableView = getTableView();
            tableView.getSelectionModel().select(getTableRow().getIndex());
            tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);
        });
        this.datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (isEditing())
            {
                commitEdit(newValue);
            }
        });
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(LocalDate item, boolean empty)
    {
        super.updateItem(item, empty);

        datePicker.setValue(item);
        if (empty)
        {
            setGraphic(null);
        }
        else
        {
            this.datePicker.setValue(item);
            setGraphic(datePicker);
        }
    }
}
