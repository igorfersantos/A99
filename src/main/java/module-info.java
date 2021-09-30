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
    requires java.validation;
    requires jfx.asynctask;

    opens br.com.igorfernandes.A99 to javafx.fxml;
    exports br.com.igorfernandes.A99;
    exports br.com.igorfernandes.A99.util;
    opens br.com.igorfernandes.A99.util to javafx.fxml;
    exports br.com.igorfernandes.A99.view;
    opens br.com.igorfernandes.A99.view to javafx.fxml;
    exports br.com.igorfernandes.A99.view.shipping;
    opens br.com.igorfernandes.A99.view.shipping to javafx.fxml;
    exports br.com.igorfernandes.A99.provider.correios.dto;
    opens br.com.igorfernandes.A99.provider.correios.dto to com.google.gson;
}