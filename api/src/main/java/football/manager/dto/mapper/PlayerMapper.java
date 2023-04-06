package football.manager.dto.mapper;

import football.manager.dto.request.PlayerRequestDto;
import football.manager.dto.response.PlayerResponseDto;
import football.manager.model.Player;
import football.manager.service.TeamService;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    private final TeamService teamService;

    public PlayerMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    public Player toModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setSurname(dto.getSurname());
        player.setAge(dto.getAge());
        player.setCareerStart(dto.getCareerStart());
        player.setTeam(teamService.findById(dto.getTeamId()));
        player.setPrice(dto.getPrice());
        return player;
    }

    public PlayerResponseDto toDto(Player player) {
        PlayerResponseDto dto = new PlayerResponseDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setSurname(player.getSurname());
        dto.setAge(player.getAge());
        dto.setCareerStart(player.getCareerStart());
        dto.setTeamId(player.getTeam().getId());
        dto.setPrice(player.getPrice());
        return dto;
    }
}
