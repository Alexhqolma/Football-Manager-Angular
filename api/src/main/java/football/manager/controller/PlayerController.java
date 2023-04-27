package football.manager.controller;

import football.manager.dto.mapper.PlayerMapper;
import football.manager.dto.request.PlayerRequestDto;
import football.manager.dto.response.PlayerResponseDto;
import football.manager.model.Player;
import football.manager.service.PlayerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
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
