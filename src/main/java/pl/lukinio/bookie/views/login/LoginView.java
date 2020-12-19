package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lukinio.bookie.data.service.AuthService;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "login", layout = MainView.class)
@PageTitle("Login")
@CssImport("./styles/views/login/login-view.css")
public class LoginView extends Div {
    @Autowired
    private final transient AuthService authService;

    public LoginView(AuthService authService) {
        this.authService = authService;
        addClassName("login-view");
        add(loginBox());
    }

    private VerticalLayout loginBox(){
        TextField username = new TextField();
        username.setPlaceholder("Username");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");

        Button button = new Button("Login",
                event -> {
                    try {
                        authService.authenticate(username.getValue(), password.getValue());
                        UI.getCurrent().navigate("about");
                    } catch (AuthService.AuthException e) {
                        Notification.show("Wrong credentials.");
                    }
                });

        RouterLink register = new RouterLink("register", RegisterView.class);

        username.setWidthFull();
        password.setWidthFull();
        button.setWidthFull();
        username.isRequired();
        password.isRequired();

        VerticalLayout box = new VerticalLayout(
                username, password, button, register
        );
        box.addClassName("login-box");
        return box;
    }
}
