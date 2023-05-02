package football.manager.service.impl;

import football.manager.model.Team;
import football.manager.repository.PlayerRepository;
import football.manager.repository.TeamRepository;
import football.manager.service.TeamService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find team by id " + id));
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
