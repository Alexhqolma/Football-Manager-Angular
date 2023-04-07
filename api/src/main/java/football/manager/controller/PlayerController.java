package football.manager.controller;

import football.manager.dto.mapper.PlayerMapper;
import football.manager.dto.request.PlayerRequestDto;
import football.manager.dto.request.TeamRequestDto;
import football.manager.dto.response.PlayerResponseDto;
import football.manager.dto.response.TeamResponseDto;
import football.manager.model.Player;
import football.manager.model.Team;
import football.manager.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService,
                            PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @PostMapping
    public PlayerResponseDto create(@RequestBody PlayerRequestDto playerRequestDto) {
        return playerMapper.toDto(playerService.save(playerMapper.toModel(playerRequestDto)));
    }

    @GetMapping("/{id}")
    public PlayerResponseDto findById(@PathVariable Long id) {
        return playerMapper.toDto(playerService.findById(id));
    }

    @GetMapping
    public List<PlayerResponseDto> findAllPlayers() {
        return playerService.findAll()
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerMapper.toModel(playerRequestDto);
        player.setId(id);
        return playerMapper.toDto(playerService.save(player));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }

    @PutMapping("/transfer")
    public PlayerResponseDto transferPlayer(@RequestParam("playerId") Long playerId,
                                            @RequestParam("teamId") Long teamId) {
        Player player = playerService.findById(playerId);
        playerService.transferPlayer(teamId, player);
        return playerMapper.toDto(player);
    }
}
