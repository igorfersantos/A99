package br.com.igorfernandes.A99.view.shipping;

import javax.validation.constraints.NotEmpty;

public class ShippingStep {
    private String icon_url;

    @NotEmpty(message = "Title cannot be empty.")
    private String title;

    @NotEmpty(message = "Description cannot be empty.")
    private String description;

    @NotEmpty(message = "EventDateTime cannot be empty.")
    private String eventDateTime;

    public ShippingStep() {
    }

    public ShippingStep(String icon_url, String title, String description, String eventDateTime) {
        this.icon_url = icon_url;
        this.title = title;
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
}
