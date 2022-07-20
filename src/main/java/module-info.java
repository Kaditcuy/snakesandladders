module com.example.snakesandladders {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakesandladders to javafx.fxml;
    exports com.example.snakesandladders;
}