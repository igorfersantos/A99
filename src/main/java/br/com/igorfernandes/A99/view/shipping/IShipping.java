package br.com.igorfernandes.A99.view.shipping;

import java.util.ArrayList;

public interface IShipping {

    ArrayList<ShippingStep> steps = new ArrayList<>();

    default ArrayList<ShippingStep> getSteps() {
        return steps;
    }
}
