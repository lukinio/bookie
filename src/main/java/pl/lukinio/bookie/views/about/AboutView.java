package pl.lukinio.bookie.views.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "about", layout = MainView.class, registerAtStartup = false)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        add(new Text("Personal Information"));
    }
}
