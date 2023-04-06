package football.manager.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerRequestDto {
    private String name;
    private String surname;
    private int age;
    private LocalDate careerStart;
    private Long teamId;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getCareerStart() {
        return careerStart;
    }

    public Long getTeamId() {
        return teamId;
    }
}
