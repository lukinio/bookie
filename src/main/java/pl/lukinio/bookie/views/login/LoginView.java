package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.data.service.UserService;
import pl.lukinio.bookie.views.main.MainView;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

@Route(value = "login", layout = MainView.class)
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends Div {
    @Autowired
    private final transient UserService userService;
    private final TextField username;
    private final PasswordField password;
    private final Button button;

    public LoginView(UserService userService) {
        this.userService = userService;
        addClassName("login-view");

        username = new TextField();
        password = new PasswordField();
        button = new Button("Login", this::handleLogin);

        initialize();

        VerticalLayout box = new VerticalLayout(username, password, button);
        box.addClassName("login-box");
        add(box);
    }

    private void handleLogin(ClickEvent<Button> buttonClickEvent) {
        if (username.getValue().trim().isEmpty()) {
            Notification.show("Enter a username", 2500, TOP_CENTER);
        } else if (password.getValue().isEmpty()) {
            Notification.show("Enter a password", 2500, TOP_CENTER);
        } else {
            userService.authenticate(username.getValue(), password.getValue());
        }
    }

    private void initialize(){
        username.setPlaceholder("Username");
        password.setPlaceholder("Password");

        username.setWidthFull();
        password.setWidthFull();
        button.setWidthFull();
        username.isRequired();
        password.isRequired();
    }
}
