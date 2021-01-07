package pl.lukinio.bookie.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukinio.bookie.data.entity.events.Team;
import pl.lukinio.bookie.data.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team findByName(String name){
        return teamRepository.getByName(name);
    }
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

}
