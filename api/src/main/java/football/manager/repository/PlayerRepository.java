package football.manager.repository;

import football.manager.model.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select p from Player p join fetch p.team t where p.team.id = :id")
    List<Player> findAllPlayersByTeamId(Long id);
}
