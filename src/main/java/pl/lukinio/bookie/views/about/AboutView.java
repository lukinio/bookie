package pl.lukinio.bookie.views.about;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import pl.lukinio.bookie.data.entity.users.UserBase;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "about", layout = MainView.class, registerAtStartup = false)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        add(create());
    }
    private HorizontalLayout createRow(String name, String value){
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(new Label(name), new Label(value));
        return layout;
    }
    private VerticalLayout create(){
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        UserBase user = VaadinSession.getCurrent().getAttribute(UserBase.class);
        layout.add(
                new Label("Personal Information"),
                createRow("Username: ", user.getUsername()),
                createRow("Email: ", user.getEmail()),
                createRow("Role: ", user.getRole())
        );
        return layout;
    }
}
