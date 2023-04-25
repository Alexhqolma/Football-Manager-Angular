package football.manager.service.impl;

import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.repository.PlayerRepository;
import football.manager.repository.TeamRepository;
import football.manager.service.TeamService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    private static final Long FREE_PLAYER = 1L;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository,
                           PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
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
        moveAllPlayers(id);
        teamRepository.deleteById(id);
    }

    private void moveAllPlayers(Long id) {
        Team teamFrom = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find team by id " + id));
        Team teamTo = teamRepository.findById(FREE_PLAYER)
                .orElseThrow(() -> new RuntimeException("Can't find team by id " + FREE_PLAYER));
        if (teamFrom.getPlayers() != null) {
            List<Player> playersTo = teamTo.getPlayers();
            playersTo.addAll(teamFrom.getPlayers());
            teamTo.setPlayers(playersTo);
            teamRepository.save(teamTo);
            for (Player player : playersTo) {
                player.setTeam(teamTo);
            }
            List<Player> newTeamPlayers = new ArrayList<>();
            for (Player player : playersTo) {
                player.setTeam(teamTo);
                newTeamPlayers.add(player);
            }
            playerRepository.saveAll(newTeamPlayers);
        }
    }
}
