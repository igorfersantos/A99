package br.com.igorfernandes.A99;

import com.formdev.flatlaf.FlatDarkLaf;
import dorkbox.systemTray.Menu;
import dorkbox.systemTray.MenuItem;
import dorkbox.systemTray.SystemTray;
import dorkbox.systemTray.util.WindowsSwingUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public
class Main {

    public static final URL APP_ICON = Main.class.getResource("image/icon.png");
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
    private static Main main;
    private SystemTray systemTray;
    private Parent root = null;

    public Main() {

    }

    public static void main(String[] args) {
        main = new Main();

        boolean isWindows = System.getProperty("os.name").toLowerCase().indexOf("win") > 0;
        //SystemTray.FORCE_TRAY_TYPE = SystemTray.TrayType.Swing;
        if (!FlatDarkLaf.setup()) {
            logger.debug("Could not apply FlatDarkLaf theme!");
        }
        logger.debug("teste");

        SystemTray.SWING_UI = new WindowsSwingUI();

        // NOTE: make sure JNA jar is on the classpath!
        Application.launch(MyApplication.class);
    }

    public void start(final Stage primaryStage) {
        Platform.setImplicitExit(false);

        SystemTray.DEBUG = true; // for test apps, we always want to run in debug mode

        this.systemTray = SystemTray.get("A99");

        if (systemTray == null) {
            throw new RuntimeException("Unable to load SystemTray!");
        }

        systemTray.setImage(APP_ICON);

        Menu mainMenu = systemTray.getMenu();

        mainMenu.add(new MenuItem("Shippings", e -> {
            Platform.runLater(() -> {
                try {
                    root = FXMLLoader.load(getClass().getResource("model/shippings-view.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("css/shippings.css").toExternalForm());
                primaryStage.setTitle("Shippings");
                primaryStage.getIcons().add(new Image(APP_ICON.toExternalForm()));
                primaryStage.setScene(scene);
                primaryStage.show();
            });
        }));

        if (SystemTray.DEBUG) {
            MenuItem entry = new MenuItem("Type: " + systemTray.getType().toString());
            entry.setEnabled(false);
            systemTray.getMenu().add(entry);
        }

        systemTray.getMenu().add(new MenuItem("Quit", e -> {
            systemTray.shutdown();
            System.exit(0);
        })).setShortcut('q'); // case does not matter
    }

    private void shutdown() {
        // SWT/JavaFX "shutdown hooks" have changed. Since it's no longer available with JPMS, it is no longer supported.
        // Developers must add the shutdown hooks themselves.
        systemTray.shutdown();
    }

    public static class MyApplication extends Application {
        public MyApplication() {
        }

        @Override
        public void start(final Stage stage) {
            if (main == null) {
                main = new Main();
            }

            main.start(stage);
        }

        @Override
        public void stop() throws Exception {
            // SWT/JavaFX "shutdown hooks" have changed. Since it's no longer available with JPMS, it is no longer supported.
            // Developers must add the shutdown hooks themselves.
            main.shutdown();
        }
    }
}