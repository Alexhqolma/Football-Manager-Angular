package football.manager.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(generator = "players_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "players_id_seq",
    sequenceName = "players_id_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private int age;
    @Column(name = "careerstart")
    private LocalDate careerStart;
    @ManyToOne
    private Team team;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }
}
