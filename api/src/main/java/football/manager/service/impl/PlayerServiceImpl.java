package football.manager.service.impl;

import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.repository.PlayerRepository;
import football.manager.repository.TeamRepository;
import football.manager.service.PlayerService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final int PLAYER_STATIC_PRICE = 100000;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find player by id " + id));
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            player.setPrice(getPlayerPrice(player));
        }
        return players;
    }

    @Override
    public List<Player> findAllPlayersByTeamId(Long id) {
        List<Player> players = playerRepository.findAllPlayersByTeamId(id);
        for (Player player : players) {
            player.setPrice(getPlayerPrice(player));
        }
        return players;
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        deletePlayerFromTeam(id);
        playerRepository.deleteById(id);
    }

    private BigDecimal getPlayerPrice(Player player) {
        int monthExp = (LocalDate.now().getMonthValue()
                - player.getCareerStart().getMonthValue()) * 100000;
        return BigDecimal.valueOf(monthExp)
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.CEILING);
    }

    private void deletePlayerFromTeam(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find player by id " + id));
        Team team = teamRepository.findById(player.getTeam().getId())
                .orElseThrow(() -> new RuntimeException("Can't find team by id "
                        + player.getTeam().getId()));
        List<Player> players = team.getPlayers();
        players.remove(player);
        teamRepository.save(team);
    }

    @Transactional
    @Override
    public Player transferPlayer(Long teamToId, Player player) {
        Team teamFrom = teamRepository.findById(player.getTeam().getId())
                .orElseThrow(() -> new RuntimeException("Can't find team by id "
                        + player.getTeam().getId()));
        Team teamTo = teamRepository.findById(teamToId)
                .orElseThrow(() -> new RuntimeException("Can't find team by id " + teamToId));
        movePlayer(teamTo, player);
        moveMoney(teamFrom, teamTo, player);
        System.out.println(teamFrom.getId());
        System.out.println(teamTo.getId());
        System.out.println(player.getId());
        teamRepository.save(teamFrom);
        teamRepository.save(teamTo);
        playerRepository.save(player);
        return player;
    }

    private void movePlayer(Team teamTo, Player player) {
        player.setTeam(teamTo);
    }

    private void moveMoney(Team teamFrom, Team teamTo, Player player) {
        int monthExp = (LocalDate.now().getMonthValue()
                - player.getCareerStart().getMonthValue()) * PLAYER_STATIC_PRICE;
        BigDecimal playerPrice = BigDecimal.valueOf(monthExp)
                .divide(BigDecimal.valueOf(player.getAge()), RoundingMode.CEILING);
        teamFrom.setFunds(teamFrom.getFunds().subtract(playerPrice));
        teamTo.setFunds(teamTo.getFunds().add(playerPrice));
    }
}
