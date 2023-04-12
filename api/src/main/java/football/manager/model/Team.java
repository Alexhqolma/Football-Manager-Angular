package football.manager.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(generator = "teams_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "teams_id_seq",
            sequenceName = "teams_id_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany
    private List<Player> players;
    private BigDecimal funds;
    private String city;
    private String country;
}
