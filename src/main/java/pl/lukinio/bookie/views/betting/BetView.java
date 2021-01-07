package pl.lukinio.bookie.views.betting;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.components.OddField;
import pl.lukinio.bookie.data.entity.events.Games;
import pl.lukinio.bookie.data.entity.users.UserBase;
import pl.lukinio.bookie.data.service.GameService;
import pl.lukinio.bookie.views.main.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;


@Route(value = "bet", layout = MainView.class)
@PageTitle("Betting")
public class BetView extends Div {
    private static final Logger LOGGER = Logger.getLogger(BetView.class.getName());
    @Autowired
    private final transient GameService gameService;
    private final NumberField stake;
    private final List<OddField> gamesOdd = new ArrayList<>();

    public BetView(GameService gameService){
        this.gameService = gameService;
        stake = new NumberField();
        add(createBettingSection());
    }

    private void makeBet(ClickEvent<Button> event){
        UserBase user = VaadinSession.getCurrent().getAttribute(UserBase.class);
        if(user.isNull()){
            Notification.show("Log in to play", 2500, TOP_CENTER);
            return;
        }
        OddField[] res = gamesOdd.stream()
                .filter(o -> o.getValue() != null)
                .toArray(OddField[]::new);
        if(res.length == 0){
            Notification.show("Choose odds to play", 2500, TOP_CENTER);
            return;
        } else if(stake.isEmpty() || stake.getValue() < 1){
            Notification.show("stake have to be > 1", 2500, TOP_CENTER);
            return;
        }
        for(OddField o : res) {
            LOGGER.log(Level.INFO,() -> o.getValue().toString());
        }
        Notification.show("Success, you've made a bet!", 2500, TOP_CENTER);
    }

    private VerticalLayout createBettingSection(){
        List<Games> games = gameService.findAll();
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        Label describe = new Label("Betting offers");
        layout.add(describe);
        for(Games game : games){
            OddField odds = new OddField(game.getHome() +" vs " + game.getAway(),
                    game.getHomeOdd(), game.getDrawOdd(), game.getAwayOdd());
            gamesOdd.add(odds);
            layout.add(odds);
        }
        HorizontalLayout hl = new HorizontalLayout();
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        stake.setPlaceholder("Stake");
        stake.setMin(1);
        stake.setHasControls(true);
        hl.add(stake, new Button("Make a bet!", this::makeBet));
        layout.add(hl);
        return layout;
    }
}
