package br.com.igorfernandes.A99;

public enum ShippingType {
    CORREIOS("Correios");

    private String description;

    ShippingType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
