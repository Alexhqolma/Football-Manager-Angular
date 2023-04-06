package football.manager.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class TeamRequestDto {
    private String name;
    private List<Long> playersId;
    private BigDecimal funds;
    private String city;
    private String country;

    public String getName() {
        return name;
    }

    public List<Long> getPlayersId() {
        return playersId;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
