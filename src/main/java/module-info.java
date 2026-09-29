module lab03.lab5cards {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab03.lab5cards to javafx.fxml;
    exports lab03.lab5cards;
}