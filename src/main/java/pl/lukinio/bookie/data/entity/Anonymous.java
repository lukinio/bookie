package pl.lukinio.bookie.data.entity;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import org.javatuples.Pair;
import pl.lukinio.bookie.views.about.AboutView;
import pl.lukinio.bookie.views.admin.AdminView;
import pl.lukinio.bookie.views.home.HomeView;
import pl.lukinio.bookie.views.login.LoginView;
import pl.lukinio.bookie.views.login.LogoutView;
import pl.lukinio.bookie.views.login.RegisterView;

import java.util.ArrayList;
import java.util.List;

public class Anonymous extends UserBase {
    public Anonymous(){
        super("", "", "", "ANONYMOUS");
    }

    @Override
    public void setAvailableRoutes() {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();
        configuration.setAnnotatedRoute(LoginView.class);
        configuration.setAnnotatedRoute(RegisterView.class);
        configuration.removeRoute(AboutView.class);
        configuration.removeRoute(LogoutView.class);
        configuration.removeRoute(AdminView.class);
    }

    @Override
    public List<Pair<String,  Class<? extends Component>>> getRoutes(){
        ArrayList<Pair<String,  Class<? extends Component>>> routes = new ArrayList<>();
        routes.add(new Pair<>("Home", HomeView.class));
        routes.add(new Pair<>("Login", LoginView.class));
        routes.add(new Pair<>("Register", RegisterView.class));
        return routes;
    }

    @Override
    public boolean isNull(){
        return true;
    }
}
