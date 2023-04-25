package football.manager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

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
