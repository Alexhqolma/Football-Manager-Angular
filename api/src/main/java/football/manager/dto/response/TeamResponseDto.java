package football.manager.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TeamResponseDto {
    private Long id;
    private String name;
    private List<Long> playersId;
    private BigDecimal funds;
    private String city;
    private String country;
}
