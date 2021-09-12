package br.com.igorfernandes.A99;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDialog;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.enums.ButtonType;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import io.github.palexdev.materialfx.effects.DepthLevel;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShippingController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private StackPane menuPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vMenu;

    private Color fadedColor = new Color(0, 0, 0, 1);
    private ColorAdjust colorAdjust = new ColorAdjust();

    private boolean isFadedIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StackPane stackPane = new StackPane();
        Insets insets = new Insets(15);
        stackPane.setOpaqueInsets(insets);
        stackPane.setBorder(
                new Border(
                        new BorderStroke(
                                Color.TRANSPARENT,
                                BorderStrokeStyle.NONE,
                                new CornerRadii(360), BorderStroke.THIN)));

        // TODO: Talvez mudar aqui o componente pra ser redondo apenas no top left e top right
        StackPane root = new StackPane();
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> root.requestFocus());
        Circle background = new Circle();
        background.setRadius(15);
        background.setFill(Color.TRANSPARENT);
        background.setStroke(null);

        Image image = new Image(getClass().getResourceAsStream("image/add.png"));
        Image image2 = new Image(getClass().getResourceAsStream("image/add-hover.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setEffect(colorAdjust);
        // Prevents hover effect getting weird
        imageView.setMouseTransparent(true);

        EventHandler<MouseEvent> mouseHoverEvent = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO: Deixar o ColorAdjust com FadeIn/Out
                colorAdjust.setBrightness(1);
                fadeInOutFillProperty(background);
                imageView.setImage(image2);
            }
        };

        EventHandler<MouseEvent> mouseExitEvent = new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                colorAdjust.setBrightness(0);
                fadeInOutFillProperty(background);
                imageView.setImage(image);
            }
        };

        background.setOnMouseClicked(e -> {

            MFXDialog genericDialog = MFXDialogFactory.buildGenericDialog("teste", "teste");
            genericDialog.setAnimateIn(true);
            genericDialog.setAnimateOut(true);
            genericDialog.setVisible(false);
            genericDialog.setCenterBeforeShow(true);

            genericDialog.setOnBeforeOpen(event -> {
                System.out.println("BEFORE OPEN");
            });
            genericDialog.setOnOpened(event -> {
                System.out.println("OPENED");
            });
            genericDialog.setOnBeforeClose(event -> System.out.println("BEFORE CLOSING"));
            genericDialog.setOnClosed(event -> {
                System.out.println("CLOSED");
                // Clean the dialog from the rootPane, avoiding unnecessary memory consumption and GC work
                rootPane.getChildren().remove(genericDialog);
            });
            genericDialog.setCloseHandler(c -> {
                genericDialog.close();
            });
            genericDialog.setCenter(new Circle(5));
            genericDialog.setActions(createActionsBar(genericDialog));
            rootPane.getChildren().add(genericDialog);
            genericDialog.show();
        });


        root.setOnMouseEntered(mouseHoverEvent);
        root.setOnMouseExited(mouseExitEvent);

        root.getChildren().addAll(background, imageView);

        stackPane.getChildren().add(root);
        vMenu.getChildren().add(stackPane);
        StackPane.setAlignment(stackPane, Pos.CENTER_LEFT);
    }

    private HBox createActionsBar(AbstractMFXDialog dialog) {
        MFXButton action1 = new MFXButton("Perform Action 1");
        MFXButton close = new MFXButton("Cancel");

        action1.setButtonType(ButtonType.RAISED);
        close.setButtonType(ButtonType.RAISED);

        action1.setDepthLevel(DepthLevel.LEVEL1);
        close.setDepthLevel(DepthLevel.LEVEL1);

        action1.setOnAction(event -> System.out.println("1"));
        dialog.addCloseButton(close);

        HBox box = new HBox(20, action1, close);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 5, 20, 5));
        return box;
    }

    private void fadeInOutFillProperty(Shape shape) {
        final Animation animation = new Transition() {

            {
                setCycleDuration(Duration.millis(100));
                setInterpolator(Interpolator.EASE_OUT);
                setOnFinished(event -> isFadedIn = Objects.equals(shape.getFill(), fadedColor));
            }

            @Override
            protected void interpolate(double frac) {
                Color vColor;
                // TODO: Efeito de crescer ou spin o Circle aqui junto
                // https://github.com/Typhon0/AnimateFX
                if (isFadedIn) {
                    vColor = new Color(0, 0, 0, 1 - frac);
                } else {
                    vColor = new Color(0, 0, 0, 0 + frac);
                }
                shape.setFill(vColor);
            }
        };
        animation.play();
    }
}
