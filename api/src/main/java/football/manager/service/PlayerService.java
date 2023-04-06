package football.manager.service;

import football.manager.model.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);

    Player findById(Long id);

    List<Player> findAll();

    List<Player> findAllPlayersByTeamId(Long id);

    Player update(Player player);

    void delete(Long id);

    Player transferPlayer(Long teamToId, Player player);
}
