package pl.lukinio.bookie.views.home;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.lukinio.bookie.views.main.MainView;


@Route(value = "home", layout = MainView.class)
@PageTitle("Home")
//@RouteAlias(value = "", layout = MainView.class)
public class HomeView extends Div {

    public HomeView() {
        setId("home-view");
        add(new Text("Home"));
    }
}
