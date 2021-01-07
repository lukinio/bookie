package pl.lukinio.bookie.data.entity.users;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import org.javatuples.Pair;
import pl.lukinio.bookie.views.about.AboutView;
import pl.lukinio.bookie.views.admin.AdminView;
import pl.lukinio.bookie.views.betting.BetView;
import pl.lukinio.bookie.views.home.HomeView;
import pl.lukinio.bookie.views.login.LoginView;
import pl.lukinio.bookie.views.login.LogoutView;
import pl.lukinio.bookie.views.login.RegisterView;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@DiscriminatorValue("ADMIN")
public class Admin extends UserBase{
    public Admin(String username, String password, String email){
        super(username, password, email, "ADMIN");
    }
    public Admin(){
        /* default constructor for JPA */
        super();
    }

    @Override
    public void setAvailableRoutes() {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();
        configuration.setAnnotatedRoute(AboutView.class);
        configuration.setAnnotatedRoute(LogoutView.class);
        configuration.setAnnotatedRoute(AdminView.class);
        configuration.removeRoute(LoginView.class);
        configuration.removeRoute(RegisterView.class);
    }

    @Override
    public List<Pair<String,  Class<? extends Component>>> getRoutes(){
        ArrayList<Pair<String,  Class<? extends Component>>> routes = new ArrayList<>();
        routes.add(new Pair<>("Home", HomeView.class));
        routes.add(new Pair<>("About", AboutView.class));
        routes.add(new Pair<>("Bet", BetView.class));
        routes.add(new Pair<>("Admin", AdminView.class));
        routes.add(new Pair<>("Logout", LogoutView.class));
        return routes;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
