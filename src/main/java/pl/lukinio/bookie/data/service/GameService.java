package pl.lukinio.bookie.data.service;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.events.Games;
import pl.lukinio.bookie.data.repository.GameRepository;

import java.time.LocalDateTime;
import java.util.List;

import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;

@Service
public class GameService {
    @Autowired
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Games> findAll() {
        return gameRepository.findAll();
    }

    public long count() {
        return gameRepository.count();
    }

    public void delete(Games game) {
        gameRepository.delete(game);
    }


    public void create(LocalDateTime date, String home, String away, Double homeOdd, Double drawOdd, Double awayOdd){
        Games game = new Games(date, home, away, homeOdd, drawOdd, awayOdd);
        if(gameRepository.exists(Example.of(game))){
            Notification.show("Fail, this game is already added", 2500, TOP_CENTER);
            return;
        }
        gameRepository.save(game);
        Notification.show("Success, you've made a new event", 2500, TOP_CENTER);
    }
}
