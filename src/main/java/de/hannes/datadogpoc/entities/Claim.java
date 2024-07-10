package de.hannes.datadogpoc.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "claims")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long claimID;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "claim_damage",
            joinColumns = @JoinColumn(name = "claim_id"),
            inverseJoinColumns = @JoinColumn(name = "damage_id")
    )
    @JsonManagedReference
    private Set<Damage> damages = new HashSet<>();
    private long timestamp;
    public enum status{CREATED, IN_PROGRESS, DONE, CANCELLED};
    private status status;
}
