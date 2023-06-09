package football.manager.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PlayerRequestDto {
    private String name;
    private String surname;
    private int age;
    private LocalDate careerStart;
    private Long teamId;
    private BigDecimal price;
}
