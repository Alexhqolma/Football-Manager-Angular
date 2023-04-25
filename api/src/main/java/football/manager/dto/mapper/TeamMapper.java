package football.manager.dto.mapper;

import football.manager.dto.request.TeamRequestDto;
import football.manager.dto.response.TeamResponseDto;
import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.service.PlayerService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    private final PlayerService playerService;

    public TeamMapper(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Team toModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        if (dto.getPlayersId() != null) {
            team.setPlayers(dto.getPlayersId()
                    .stream()
                    .map(playerService::findById)
                    .collect(Collectors.toList()));
        }
        team.setFunds(dto.getFunds());
        team.setCity(dto.getCity());
        team.setCountry(dto.getCountry());
        return team;
    }

    public TeamResponseDto toDto(Team team) {
        TeamResponseDto dto = new TeamResponseDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        if (team.getPlayers() != null) {
            dto.setPlayersId(team.getPlayers()
                    .stream()
                    .map(Player::getId)
                    .collect(Collectors.toList()));
        }
        dto.setFunds(team.getFunds());
        dto.setCity(team.getCity());
        dto.setCountry(team.getCountry());
        return dto;
    }
}
