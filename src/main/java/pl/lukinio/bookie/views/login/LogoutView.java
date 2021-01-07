package pl.lukinio.bookie.views.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import pl.lukinio.bookie.data.entity.users.Anonymous;
import pl.lukinio.bookie.data.entity.users.UserBase;

@Route(value = "logout", registerAtStartup = false)
@PageTitle("Logout")
public class LogoutView extends Div {
    public LogoutView() {
        UI.getCurrent().getPage().setLocation("home");
        UserBase anon = new Anonymous();
        anon.setAvailableRoutes();
        VaadinSession.getCurrent().setAttribute(UserBase.class, anon);
    }
}
