module br.com.igorfernandes.A99 {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires java.desktop;
    requires SystemTray;
    requires com.formdev.flatlaf;
    requires MaterialFX;
    requires org.apache.logging.log4j;
    requires com.google.gson;
    requires okhttp3;

    opens br.com.igorfernandes.A99 to javafx.fxml;
    exports br.com.igorfernandes.A99;
    exports br.com.igorfernandes.A99.util;
    opens br.com.igorfernandes.A99.util to javafx.fxml;
    exports br.com.igorfernandes.A99.viewController;
    opens br.com.igorfernandes.A99.viewController to javafx.fxml;
}