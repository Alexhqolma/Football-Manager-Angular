package football.manager.service;

import football.manager.model.Team;
import java.util.List;

public interface TeamService {
    Team save(Team team);

    Team findById(Long id);

    List<Team> findAll();

    Team update(Team team);

    void delete(Long id);
}
