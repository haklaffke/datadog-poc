package de.hannes.datadogpoc.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue
    private Long claimID;

    @OneToMany
    private Set<Damage> damages = new HashSet<>();
    private long timestamp;
    private int status;
}
