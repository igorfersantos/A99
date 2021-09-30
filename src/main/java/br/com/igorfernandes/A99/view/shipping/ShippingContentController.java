package br.com.igorfernandes.A99.view.shipping;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.function.Function;

public class ShippingContentController {
    private static final Logger logger = LogManager.getLogger(ShippingContentController.class);

    static int stepsSize = 0;

    static Function<Integer, StepType> stepTypeFromInt = i -> {
        StepType stepType = null;
        if(i == 0){
            stepType = StepType.FIRST;
        }
        else if (i > 0 && i < stepsSize){
            stepType = StepType.MIDDLE;
        }
        else if (i > 0){
            stepType = StepType.LAST;
        }
        return stepType;
    };

    static Function<StepType, Pos> aligmentFromStepType = stepType -> switch (stepType){
        case FIRST -> Pos.TOP_CENTER;
        case MIDDLE -> Pos.CENTER;
        case LAST -> Pos.BOTTOM_RIGHT;
    };

    /**
     * Create Shipping Content Pane using the Shipping information already obtained from Correios API.
     * @param shippingInfo
     *  The shipping containing information about the object to be tracked.
     * @return
     *  StackPane with shipping information.
     */
    public static StackPane createShippingContentPane(IShipping shippingInfo) {
        StackPane shippingContent = new StackPane();
        shippingContent.setStyle("-fx-background-color: FFFFFF");
        shippingContent.setPrefSize(350, 300);
        shippingContent.setMaxHeight(Integer.MAX_VALUE);

        VBox shippingSteps = new VBox();

        ArrayList<ShippingStep> steps = shippingInfo.getSteps();

        stepsSize = steps.size();

        for (int i = 0; i < stepsSize; i++) {
            StepType stepType = stepTypeFromInt.apply(i);

            shippingSteps.getChildren().add(createShippingStep(steps.get(i), stepType, stepsSize, i));
        }

        shippingContent.getChildren().add(shippingSteps);

        return shippingContent;
    }

    private static HBox createShippingStep(ShippingStep step, StepType stepType, int stepsSize, int currentStep) {
        HBox shippingStep = new HBox();
        shippingStep.setPadding(new Insets(0, 0, 0, 17));
        shippingStep.setAlignment(Pos.TOP_LEFT);

        StackPane circleIconPane = createCircleIconPane(step.getIcon_url(), stepType);
        VBox shippingInformation = createShippingInformation(step.getTitle(), step.getDescription(),
                step.getEventDateTime());
        shippingInformation.setPrefHeight(100);

        shippingStep.getChildren().addAll(circleIconPane, shippingInformation);

        return shippingStep;
    }

    private static StackPane createCircleIconPane(String icon_url, StepType stepType) {
        StackPane circleImagePane = new StackPane();
        circleImagePane.setAlignment(Pos.CENTER_LEFT);
        circleImagePane.setMinWidth(Region.USE_PREF_SIZE);
        circleImagePane.setMinHeight(Region.USE_PREF_SIZE);
        circleImagePane.setPrefWidth(40);
        circleImagePane.maxWidth(Region.USE_PREF_SIZE);

        Line line = new Line();
        line.setStroke(Color.web("#71d0ff"));
        line.setStrokeWidth(2);

        if (stepType == StepType.FIRST || stepType == StepType.LAST) {
            line.setEndY(circleImagePane.getPrefHeight() / 2);
        } else {
            line.setEndY(circleImagePane.getPrefHeight());
        }

        Circle circle = new Circle(20, Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        logger.info("Icon URL: ".concat(icon_url));

        ImageView imageView = new ImageView(new Image(icon_url, true));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);

        StackPane.setAlignment(line, aligmentFromStepType.apply(stepType));
        StackPane.setAlignment(imageView, Pos.CENTER);
        circleImagePane.getChildren().addAll(line, circle, imageView);

        return circleImagePane;
    }

    private static VBox createShippingInformation(String title, String description, String eventDateTime) {
        VBox shippingInformation = new VBox();
        shippingInformation.setAlignment(Pos.CENTER_LEFT);
        shippingInformation.setPrefHeight(100);
        shippingInformation.setMaxHeight(shippingInformation.getPrefHeight());

        Font titleFont = Font.font("Verdana", FontWeight.BOLD, 12);
        Font normalFont = Font.font("Verdana", FontWeight.NORMAL, 12);

        Label labelTitle = new Label(title);
        labelTitle.setPadding(new Insets(0, 0, 0, 10));
        labelTitle.setFont(titleFont);

        Label labelDescription = new Label(description);
        labelDescription.setPadding(new Insets(0, 0, 0, 10));
        labelDescription.setFont(normalFont);

        Label labelEventDateTime = new Label(eventDateTime);
        labelEventDateTime.setPadding(new Insets(0, 0, 0, 10));
        labelEventDateTime.setFont(normalFont);

        shippingInformation.getChildren().addAll(labelTitle, labelDescription, labelEventDateTime);

        return shippingInformation;
    }
}
