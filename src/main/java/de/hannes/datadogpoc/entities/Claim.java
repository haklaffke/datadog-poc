package de.hannes.datadogpoc.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
}
