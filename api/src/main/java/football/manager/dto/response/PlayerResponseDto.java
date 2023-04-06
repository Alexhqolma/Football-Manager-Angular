package football.manager.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PlayerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private LocalDate careerStart;
    private Long teamId;
    private BigDecimal price;
}
