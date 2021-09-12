module br.com.igorfernandes.A99 {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires java.desktop;
    requires SystemTray;
    requires com.formdev.flatlaf;
    requires MaterialFX;
    requires org.apache.logging.log4j;

    opens br.com.igorfernandes.A99 to javafx.fxml;
    exports br.com.igorfernandes.A99;
}