package br.com.igorfernandes.A99.view.shipping;

public class ShippingValidator {

    public static boolean validate(ShippingType shippingType, String shippingCode) {
        return shippingType != null && switch (shippingType) {
            case CORREIOS -> shippingCode.matches("^[A-Z]{2}[0-9]{9}[A-Z]{2}$");
            default -> false;
        };
    }

    public static int getTextLimitByShippingType(ShippingType shippingType) {
        return shippingType != null
                ?
                switch (shippingType) {
                    case CORREIOS -> 13;
                    default -> 13;
                }
                : 13;
    }
}
