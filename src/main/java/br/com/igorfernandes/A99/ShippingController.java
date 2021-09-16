package br.com.igorfernandes.A99;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.enums.ButtonType;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.BindingUtils;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private FlowPane shippingPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vMenu;
    @FXML
    private MFXTextField titleTextField;

    @FXML
    private Button teste;

    private Color fadedColor = new Color(0, 0, 0, 1);
    private ColorAdjust colorAdjust = new ColorAdjust();

    private boolean isFadedIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            Paint unfocusedLineColor = titleTextField.getParent()
                    instanceof StackPane stackPane
                    ? stackPane.getBackground().getFills().get(0).getFill()
                    : Color.WHITE;

            titleTextField.setUnfocusedLineColor(unfocusedLineColor);
            //scrollPane.setStyle("-fx-text-fill: white");
        });

        shippingPane.prefWidthProperty().bind(Bindings.add(-5, scrollPane.widthProperty()));
        shippingPane.prefHeightProperty().bind(Bindings.add(-5, scrollPane.heightProperty()));
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

        root.setOnMouseClicked(e -> {
            showAddShippingDialog();
        });

        root.setOnMouseEntered(mouseHoverEvent);
        root.setOnMouseExited(mouseExitEvent);

        root.getChildren().addAll(background, imageView);

        vMenu.getChildren().add(root);
    }

    private void showAddShippingDialog() {
        MFXDialog shippingCodeDialog = MFXDialogFactory.buildGenericDialog("Add a shipping code to track", null);
        shippingCodeDialog.setAnimateIn(true);
        shippingCodeDialog.setAnimateOut(true);
        shippingCodeDialog.setVisible(false);
        shippingCodeDialog.setCenterBeforeShow(true);
        shippingCodeDialog.setPrefSize(300, 300);

        shippingCodeDialog.setOnClosed(event -> {
            System.out.println("CLOSED");
            // Clean the dialog from the rootPane, avoiding unnecessary memory consumption and GC work
            rootPane.getChildren().remove(shippingCodeDialog);
        });
        shippingCodeDialog.setCloseHandler(c -> {
            shippingCodeDialog.getChildren().clear();
            shippingCodeDialog.close();
        });

        // Center
        MFXTextField trackingTextField = new MFXTextField();
        trackingTextField.getValidator().add(
                BindingUtils.toProperty(trackingTextField.textProperty().isNotEmpty()),
                "Shipping code must be provided");
        trackingTextField.setIcon(new MFXFontIcon("mfx-variant7-mark", 16, Color.web("#8FF7A7")));
        trackingTextField.getIcon().visibleProperty().bind(trackingTextField.getValidator().validProperty());
        trackingTextField.setPromptText("Tracking code");
        trackingTextField.setAlignment(Pos.CENTER);
        trackingTextField.setPadding(new Insets(0, 0, 0, 20));
        trackingTextField.setLineColor(Color.web("#D6F9FF"));

        MFXComboBox<ShippingType> trackingTypeComboBox = new MFXComboBox<>();
        trackingTypeComboBox.setComboStyle(Styles.ComboBoxStyles.STYLE2);
        trackingTypeComboBox.getItems().setAll(ShippingType.values());
        trackingTypeComboBox.setMaxPopupHeight(ShippingType.values().length * 33);
        trackingTypeComboBox.getValidator().add(
                BindingUtils.toProperty(trackingTypeComboBox.getSelectionModel()
                        .selectedIndexProperty()
                        .isNotEqualTo(-1)), "A value must be selected");

        // Change the Text limit property on tracking type change
        trackingTypeComboBox.selectedValueProperty().addListener((observable, oldValue, newValue) -> {
            boolean validShippingCode = ShippingValidator.validate(newValue, trackingTextField.getText());

            if (!validShippingCode) {
                trackingTextField.clear();
            }

            trackingTextField.setTextLimit(ShippingValidator.getTextLimitByShippingType(newValue));
            trackingTypeComboBox.setSelectedValue(newValue);
        });

        Region spacer = new Region();
        spacer.setPrefHeight(10);
        Region spacer2 = new Region();
        spacer2.setPrefHeight(30);
        Region spacer3 = new Region();
        spacer3.setPrefHeight(10);

        Label shippingTypeLabel = new Label("Select a shipping type: ");
        shippingTypeLabel.setAlignment(Pos.CENTER_LEFT);
        shippingTypeLabel.setPrefWidth(255);

        Label trackingTextFieldLabel = new Label("Shipping code: ");
        trackingTextFieldLabel.setAlignment(Pos.CENTER_LEFT);
        trackingTextFieldLabel.setPrefWidth(255);

        VBox vBox = new VBox(
                shippingTypeLabel,
                spacer,
                trackingTypeComboBox,
                spacer2,
                trackingTextFieldLabel,
                spacer3,
                trackingTextField);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(300);


        shippingCodeDialog.setOnOpened(event -> {
            //arrumar essa parte do width

            System.out.println("teste");

            BooleanBinding booleanBinding = Bindings.createBooleanBinding(() -> ShippingValidator.validate(
                    trackingTypeComboBox.getSelectedValue(),
                    trackingTextField.textProperty().getValue()), trackingTextField.textProperty());
            trackingTextField.getValidator().add(BindingUtils.toProperty(booleanBinding),
                    "Invalid shipping code format");

            trackingTextField.setTextLimit(
                    ShippingValidator.getTextLimitByShippingType(trackingTypeComboBox.getSelectedValue()));

            trackingTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                // Triggered when focus is lost
                if (!newValue) {
                    ShippingType selectedShippingType = trackingTypeComboBox.getSelectedValue();

                    if (selectedShippingType != null) {
                        boolean validShippingCode
                                = ShippingValidator.validate(
                                selectedShippingType, trackingTextField.getText());

                        if (!validShippingCode) {
                            trackingTextField.getValidator().show();
                            trackingTextField.clear();
                        }
                    }
                }
            });

            trackingTextField.setValidated(true);
            trackingTypeComboBox.setValidated(true);
        });

        StackPane centerPanel = new StackPane(vBox);
        centerPanel.setPrefSize(200, 150);
        centerPanel.setAlignment(Pos.CENTER);
        shippingCodeDialog.setCenter(centerPanel);

        // Actions / Bottom
        shippingCodeDialog.setActions(createActionsBar(shippingCodeDialog, trackingTypeComboBox, trackingTextField));

        // Add to rootPane
        rootPane.getChildren().add(shippingCodeDialog);
        shippingCodeDialog.show();
    }

    private HBox createActionsBar(AbstractMFXDialog dialog, MFXComboBox<ShippingType> trackingTypeComboBox, MFXTextField trackingTextField) {
        MFXButton track = new MFXButton("Track");
        MFXButton close = new MFXButton("Cancel");

        track.setId("track-ripple");

        track.setButtonType(ButtonType.RAISED);
        close.setButtonType(ButtonType.RAISED);

        track.setDepthLevel(DepthLevel.LEVEL1);
        close.setDepthLevel(DepthLevel.LEVEL1);

        track.setOnAction(event -> {
            if (trackingTypeComboBox.getValidator().isValid() && trackingTextField.getValidator().isValid()) {
                boolean isValidShippingCode = ShippingValidator.validate(
                        trackingTypeComboBox.getSelectedValue(),
                        trackingTextField.textProperty().getValue());

                if (isValidShippingCode) {
                    createShippingTrackingPanel(trackingTextField.textProperty().getValue());
                } else {
                    System.out.println("Invalido!");
                }
            }
        });
        dialog.addCloseButton(close);

        HBox box = new HBox(20, track, close);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 5, 20, 5));
        return box;
    }

    private void createShippingTrackingPanel(String value) {
        System.out.println("Created!");
    }

    private void createExampleShipping() {
        MFXDialog dialog = MFXDialogFactory.buildGenericDialog(null, null);
        MFXButton close = new MFXButton("Cancel");

        dialog.setVisible(false);
        dialog.setAnimationMillis(500);
        dialog.setAnimateIn(true);
        dialog.setAnimateOut(true);
        dialog.setScrimBackground(false);
        dialog.setPrefSize(300, 300);

//        StackPane topStackPane = new StackPane();
//        topStackPane
//        dialog.setTop();

        dialog.addCloseButton(close);
        dialog.setCloseHandler(e -> {
            dialog.close();
        });
        dialog.setOnClosed(event -> {
            event.consume();
            shippingPane.getChildren().remove(dialog);
        });
        dialog.setActions(createShippingActions(close));

        shippingPane.getChildren().add(dialog);
        dialog.show();
    }

    private HBox createShippingActions(MFXButton close) {
        return new HBox(20, close);
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
