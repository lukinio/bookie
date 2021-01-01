package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.data.service.UserService;
import pl.lukinio.bookie.views.main.MainView;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

@Route(value = "register", layout = MainView.class)
@PageTitle("Registration")
@CssImport("./styles/views/login/register-view.css")
public class RegisterView extends Div {
    @Autowired
    private final transient UserService userService;
    private final TextField username;
    private final PasswordField password;
    private final PasswordField confirmPassword;
    private final EmailField email;
    private final Button button;

    public RegisterView(UserService userService){
        addClassName("register-view");
        this.userService = userService;
        username = new TextField();
        password = new PasswordField();
        confirmPassword = new PasswordField();
        email = new EmailField();
        button = new Button("Register", this::handleRegister);

        initialize();

        VerticalLayout box = new VerticalLayout(username, password, confirmPassword, email, button);
        box.addClassName("register-box");
        add(box);
    }

    private void handleRegister(ClickEvent<Button> buttonClickEvent) {
        if (username.getValue().trim().isEmpty()) {
            Notification.show("Enter a username", 2500, TOP_CENTER);
        } else if (password.getValue().isEmpty()) {
            Notification.show("Enter a password", 2500, TOP_CENTER);
        } else if (!password.getValue().equals(confirmPassword.getValue())) {
            Notification.show("Passwords don't match", 2500, TOP_CENTER);
        } else if (email.getValue().trim().isEmpty()) {
            Notification.show("Enter an email", 2500, TOP_CENTER);
        } else {
            userService.register(username.getValue(), password.getValue(), email.getValue());
        }
    }

    private void initialize(){
        username.setPlaceholder("Username");
        password.setPlaceholder("Password");
        confirmPassword.setPlaceholder("Confirm Password");
        email.setPlaceholder("example@example.pl");

        username.setWidthFull();
        password.setWidthFull();
        confirmPassword.setWidthFull();
        email.setWidthFull();
        button.setWidthFull();
        username.isRequired();
        password.isRequired();
        confirmPassword.isRequired();
    }
}
