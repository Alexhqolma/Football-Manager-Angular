package football.manager.controller;

import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InjectController {
    private final TeamService teamService;
    private final PlayerService playerService;

    public InjectController(TeamService teamService,
                            PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/inject")
    public String inject() {
        /** Add teams*/
        Team team = new Team();
        team.setName("Free players");
        team.setFunds(BigDecimal.valueOf(1000000));
        Team teamManchester = new Team();
        teamManchester.setName("Manchester United");
        teamManchester.setFunds(BigDecimal.valueOf(10000000));
        teamManchester.setCountry("England");
        teamManchester.setCity("Manchester");
        Team teamChelsea = new Team();
        teamChelsea.setName("Chelsea");
        teamChelsea.setCity("London");
        teamChelsea.setCountry("England");
        teamChelsea.setFunds(BigDecimal.valueOf(20000000));
        teamService.save(team);
        teamService.save(teamManchester);
        teamService.save(teamChelsea);

        /** Add players */
        Player player1 = new Player();
        player1.setName("Cristiano");
        player1.setSurname("Ronaldo");
        player1.setCareerStart(LocalDate.of(2000,1,21));
        player1.setAge(38);
        player1.setTeam(teamManchester);
        playerService.save(player1);
        Player player2 = new Player();
        player2.setName("Roberto");
        player2.setSurname("Carlos");
        player2.setCareerStart(LocalDate.of(1995,1,21));
        player2.setAge(43);
        player2.setTeam(teamManchester);
        playerService.save(player2);
        Player player3 = new Player();
        player3.setName("Gabriel");
        player3.setSurname("Batistuta");
        player3.setCareerStart(LocalDate.of(1990,1,21));
        player3.setAge(45);
        player3.setTeam(teamChelsea);
        playerService.save(player3);
        Player player4 = new Player();
        player4.setName("Lionel");
        player4.setSurname("Messi");
        player4.setCareerStart(LocalDate.of(2005,1,21));
        player4.setAge(28);
        player4.setTeam(teamChelsea);
        playerService.save(player4);

        /** Team set players */
        List<Player> players1 = new ArrayList<>();
        players1.add(player1);
        players1.add(player2);
        List<Player> players2 = new ArrayList<>();
        players2.add(player3);
        players2.add(player4);
        teamManchester.setPlayers(players1);
        teamChelsea.setPlayers(players2);
        teamService.update(teamManchester);
        teamService.update(teamChelsea);
        /** Transfer */
        //playerService.transferPlayer(teamChelsea.getId(), player1);

        return "Done!";
    }
}
