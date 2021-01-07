package pl.lukinio.bookie.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


public class OddField extends CustomField<Double> {

    private static final ButtonVariant bv = ButtonVariant.LUMO_PRIMARY;
    private final Label game;
    private final Button homeOdd;
    private final Button drawOdd;
    private final Button awayOdd;
    private Double value;

    public OddField(String label, Double home, Double draw, Double away) {
        this.game = new Label(label);
        this.homeOdd = new Button(String.valueOf(home), this::handleBet);
        this.drawOdd = new Button(String.valueOf(draw), this::handleBet);
        this.awayOdd = new Button(String.valueOf(away), this::handleBet);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setVerticalComponentAlignment(FlexComponent.Alignment.AUTO);
        layout.add(game, homeOdd, drawOdd, awayOdd);
        add(layout);
    }

    private void handleBet(ClickEvent<Button> event) {
        if(event.getSource().hasThemeName(bv.getVariantName())){
            value = null;
            event.getSource().removeThemeVariants(bv);
            return;
        }
        value = Double.valueOf(event.getSource().getText());
        homeOdd.removeThemeVariants(bv);
        drawOdd.removeThemeVariants(bv);
        awayOdd.removeThemeVariants(bv);
        event.getSource().addThemeVariants(bv);
        getElement().appendChild();
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    public Label getGame() {
        return game;
    }

    @Override
    protected Double generateModelValue() {
        return value;
    }

    @Override
    protected void setPresentationValue(Double value) {
        this.value = value;
    }
}