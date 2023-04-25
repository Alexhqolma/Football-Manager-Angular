package football.manager.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class TeamRequestDto {
    private String name;
    private List<Long> playersId;
    private BigDecimal funds;
    private String city;
    private String country;
}
