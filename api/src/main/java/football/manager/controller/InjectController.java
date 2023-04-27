package football.manager.controller;

import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Team teamFreePlayers = new Team();
        teamFreePlayers.setName("Free players");
        teamFreePlayers.setFunds(BigDecimal.valueOf(1000000));
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
        teamService.save(teamFreePlayers);
        teamService.save(teamManchester);
        teamService.save(teamChelsea);

        /** Add players */
        Player cristianoRonaldo = new Player();
        cristianoRonaldo.setName("Cristiano");
        cristianoRonaldo.setSurname("Ronaldo");
        cristianoRonaldo.setCareerStart(LocalDate.of(2000,1,21));
        cristianoRonaldo.setAge(38);
        cristianoRonaldo.setPrice(BigDecimal.ZERO);
        cristianoRonaldo.setTeam(teamManchester);
        playerService.save(cristianoRonaldo);
        Player robertoCarlos = new Player();
        robertoCarlos.setName("Roberto");
        robertoCarlos.setSurname("Carlos");
        robertoCarlos.setCareerStart(LocalDate.of(1995,1,21));
        robertoCarlos.setAge(43);
        robertoCarlos.setPrice(BigDecimal.ZERO);
        robertoCarlos.setTeam(teamManchester);
        playerService.save(robertoCarlos);
        Player gabrielBatistuta = new Player();
        gabrielBatistuta.setName("Gabriel");
        gabrielBatistuta.setSurname("Batistuta");
        gabrielBatistuta.setCareerStart(LocalDate.of(1990,1,21));
        gabrielBatistuta.setAge(45);
        gabrielBatistuta.setPrice(BigDecimal.ZERO);
        gabrielBatistuta.setTeam(teamChelsea);
        playerService.save(gabrielBatistuta);
        Player lionelMessi = new Player();
        lionelMessi.setName("Lionel");
        lionelMessi.setSurname("Messi");
        lionelMessi.setCareerStart(LocalDate.of(2005,1,21));
        lionelMessi.setAge(28);
        lionelMessi.setPrice(BigDecimal.ZERO);
        lionelMessi.setTeam(teamChelsea);
        playerService.save(lionelMessi);

        /** Team set players */
        List<Player> firstTeamplayers = new ArrayList<>();
        firstTeamplayers.add(cristianoRonaldo);
        firstTeamplayers.add(robertoCarlos);
        List<Player> secondTeamplayers = new ArrayList<>();
        secondTeamplayers.add(gabrielBatistuta);
        secondTeamplayers.add(lionelMessi);
        teamManchester.setPlayers(firstTeamplayers);
        teamChelsea.setPlayers(secondTeamplayers);
        teamService.update(teamManchester);
        teamService.update(teamChelsea);
        /** Transfer */
        playerService.transferPlayer(teamChelsea.getId(), cristianoRonaldo);
        playerService.transferPlayer(teamManchester.getId(), cristianoRonaldo);

        return "Done!";
    }
}
