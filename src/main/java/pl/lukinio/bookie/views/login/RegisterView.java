package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.data.entity.Role;
import pl.lukinio.bookie.data.entity.User;
import pl.lukinio.bookie.data.service.UserService;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "register", layout = MainView.class)
@PageTitle("Registration")
@CssImport("./styles/views/login/register-view.css")
public class RegisterView extends Div {
    @Autowired
    private final transient UserService userService;
    public RegisterView(UserService userService){
        addClassName("register-view");
        this.userService = userService;
        add(registerBox());
    }

    private void register(String username, String password1, String password2, String email) {
        if (username.trim().isEmpty()) {
            Notification.show("Enter a username");
        } else if (password1.isEmpty()) {
            Notification.show("Enter a password");
        } else if (!password1.equals(password2)) {
            Notification.show("Passwords don't match");
        } else if (email.trim().isEmpty()) {
            Notification.show("Enter an email");
        } else {
            User newUser = new User.Builder()
                    .username(username)
                    .password(password1)
                    .role(Role.USER)
                    .build();

            if(userService.save(newUser)){
                Notification.show("Registration succeeded.");
                UI.getCurrent().navigate("home");
            }else{
                Notification.show("Registration fail. User exists");
            }
        }
    }

    private VerticalLayout registerBox(){
        TextField username = new TextField();
        username.setPlaceholder("Username");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPlaceholder("Confirm Password");
        EmailField emailField = new EmailField();
        emailField.setPlaceholder("example@example.pl");

        Button button = new Button("Register",
                e -> register(
                        username.getValue(),
                        password.getValue(),
                        confirmPassword.getValue(),
                        emailField.getValue()
                ));

        RouterLink login = new RouterLink("login", LoginView.class);

        username.setWidthFull();
        password.setWidthFull();
        confirmPassword.setWidthFull();
        emailField.setWidthFull();
        button.setWidthFull();
        username.isRequired();
        password.isRequired();
        confirmPassword.isRequired();

        VerticalLayout box = new VerticalLayout(
                username, password, confirmPassword, emailField, button, login
        );
        box.addClassName("register-box");
        return box;
    }
}
