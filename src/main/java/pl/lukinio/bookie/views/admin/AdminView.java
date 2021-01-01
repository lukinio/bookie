package pl.lukinio.bookie.views.admin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.lukinio.bookie.views.main.MainView;

@Route(value = "admin", layout = MainView.class, registerAtStartup = false)
@PageTitle("Admin")
public class AdminView extends Div{
    public AdminView(){
        setId("admin-view");
        add(new Text("Admin View"));
    }
}
