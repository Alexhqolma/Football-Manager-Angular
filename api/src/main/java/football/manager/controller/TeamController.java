package football.manager.controller;

import football.manager.dto.mapper.PlayerMapper;
import football.manager.dto.mapper.TeamMapper;
import football.manager.dto.request.TeamRequestDto;
import football.manager.dto.response.PlayerResponseDto;
import football.manager.dto.response.TeamResponseDto;
import football.manager.model.Team;
import football.manager.service.PlayerService;
import football.manager.service.TeamService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final PlayerService playerService;
    private final TeamMapper teamMapper;
    private final PlayerMapper playerMapper;

    public TeamController(TeamService teamService,
                          PlayerService playerService,
                          TeamMapper teamMapper,
                          PlayerMapper playerMapper) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.teamMapper = teamMapper;
        this.playerMapper = playerMapper;
    }

    @PostMapping
    public TeamResponseDto create(@RequestBody TeamRequestDto teamRequestDto) {
        return teamMapper.toDto(teamService.save(teamMapper.toModel(teamRequestDto)));
    }

    @GetMapping("/{id}")
    public TeamResponseDto findById(@PathVariable Long id) {
        return teamMapper.toDto(teamService.findById(id));
    }

    @GetMapping
    public List<TeamResponseDto> findAllTeams() {
        return teamService.findAll()
                .stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/players/{id}")
    public List<PlayerResponseDto> findAllPlayers(@PathVariable Long id) {
        return playerService.findAllPlayersByTeamId(id)
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TeamResponseDto update(@PathVariable Long id,
                                  @RequestBody TeamRequestDto teamRequestDto) {
        Team team = teamMapper.toModel(teamRequestDto);
        team.setId(id);
        return teamMapper.toDto(teamService.save(team));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
