module com.htlshkoder.demo222 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.htlshkoder.demo222 to javafx.fxml;
    exports com.htlshkoder.demo222;
}