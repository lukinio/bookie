package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "login", layout = MainView.class)
@PageTitle("Login")
public class LoginView extends Div {

    public LoginView() {
        addClassName("login-view");
        add(loginBox());
    }

    private VerticalLayout loginBox(){
        TextField username = new TextField();
        username.setPlaceholder("Username");
        PasswordField password = new PasswordField();
        password.setPlaceholder("Password");

        Button button = new Button("Login");

        RouterLink register = new RouterLink("register", RegisterView.class);

        username.setWidthFull();
        password.setWidthFull();
        button.setWidthFull();
        username.isRequired();
        password.isRequired();

        VerticalLayout box = new VerticalLayout(
                username, password, button, register
        );
        box.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        box.setMaxWidth("25%");
        box.setMargin(true);
        box.addClassName("login-box");
        return box;
    }
}
