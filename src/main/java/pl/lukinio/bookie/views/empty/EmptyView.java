package pl.lukinio.bookie.views.empty;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.lukinio.bookie.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "empty", layout = MainView.class)
@PageTitle("Empty")
@RouteAlias(value = "", layout = MainView.class)
public class EmptyView extends Div {

    public EmptyView() {
        setId("empty-view");
        add(new Text("Content placeholder\n"));
        add(new Text("Test 2"));
    }

}
