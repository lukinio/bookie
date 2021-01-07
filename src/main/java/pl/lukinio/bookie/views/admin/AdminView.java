package pl.lukinio.bookie.views.admin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.data.entity.events.Team;
import pl.lukinio.bookie.data.service.GameService;
import pl.lukinio.bookie.data.service.TeamService;
import pl.lukinio.bookie.views.main.MainView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

@Route(value = "admin", layout = MainView.class, registerAtStartup = false)
@PageTitle("Admin")
public class AdminView extends Div{
    @Autowired
    private final transient TeamService teamService;
    @Autowired
    private final transient GameService gameService;
    private final DateTimePicker date = new DateTimePicker(dateTimePickerLocalDateTimeComponentValueChangeEvent -> {} );
    private final Select<Team> home  = new Select<>();
    private final Select<Team> away = new Select<>();
    private final NumberField homeOdd = new NumberField();
    private final NumberField drawOdd = new NumberField();
    private final NumberField awayOdd = new NumberField();


    public AdminView(TeamService teamService, GameService gameService){
        this.teamService = teamService;
        this.gameService = gameService;
        setId("admin-view");
        Button button = new Button("Create", this::handleCreate);

        updateFields();

        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(createTeamSection(), createTimeSection(), createOddsSection(), button);
        add(layout);
    }

    private void handleCreate(ClickEvent<Button> buttonClickEvent) {
        if (home.isEmpty() || away.isEmpty()){
            Notification.show("Enter home and away team", 2500, TOP_CENTER);
        } else if (home.getValue().equals(away.getValue())){
            Notification.show("Incorrect, teams must be different", 2500, TOP_CENTER);
        } else if (date.isEmpty()){
            Notification.show("Enter date and time of match", 2500, TOP_CENTER);
        } else if (homeOdd.isEmpty() || drawOdd.isEmpty() || awayOdd.isEmpty()){
            Notification.show("Incorrect, odds are empty", 2500, TOP_CENTER);
        } else if (homeOdd.getValue() <= 1 || drawOdd.getValue() <= 1 || awayOdd.getValue() <= 1){
            Notification.show("Incorrect, odds have to be > 1", 2500, TOP_CENTER);
        } else {
            gameService.create(date.getValue(), home.getValue().getName(), away.getValue().getName(),
                    homeOdd.getValue(), drawOdd.getValue(), awayOdd.getValue());
        }
    }

    private void updateFields(){
        List<Team> teams = teamService.findAll();
        home.setItems(teams);
        away.setItems(teams);
    }

    private VerticalLayout createTeamSection(){
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout homeTeam = new VerticalLayout();
        VerticalLayout awayTeam = new VerticalLayout();
        homeTeam.setAlignItems(FlexComponent.Alignment.CENTER);
        awayTeam.setAlignItems(FlexComponent.Alignment.CENTER);
        homeTeam.add(new Label("Home Team"), home);
        awayTeam.add(new Label("Away Team"), away);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setAlignItems(FlexComponent.Alignment.CENTER);

        hl.add(homeTeam, awayTeam);

        layout.add(new Label("Choose teams"), hl);
        return layout;
    }
    private VerticalLayout createTimeSection(){
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        date.setStep(Duration.ofMinutes(15));
        date.setLocale(new Locale("pl"));
        date.setValue(LocalDateTime.now());
        layout.add(new Label("Time and date"), date);
        return layout;
    }
    private VerticalLayout createOddsSection(){
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout homeLayout = new VerticalLayout();
        VerticalLayout drawLayout = new VerticalLayout();
        VerticalLayout awayLayout = new VerticalLayout();
        homeLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        drawLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        awayLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        homeLayout.add(new Label("Home Odd"), homeOdd);
        drawLayout.add(new Label("Draw Odd"), drawOdd);
        awayLayout.add(new Label("Away Odd"), awayOdd);

        homeOdd.setHasControls(true);
        drawOdd.setHasControls(true);
        awayOdd.setHasControls(true);
        homeOdd.setMin(1.01);
        drawOdd.setMin(1.01);
        awayOdd.setMin(1.01);
        homeOdd.setStep(0.01);
        drawOdd.setStep(0.01);
        awayOdd.setStep(0.01);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        hl.add(homeLayout, drawLayout, awayLayout);

        layout.add(new Label("Choose Odds"), hl);
        return layout;
    }
}
