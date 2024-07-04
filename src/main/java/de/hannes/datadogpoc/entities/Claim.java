package de.hannes.datadogpoc.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

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
    @JoinColumn(name = "claim_id") // Adjust based on your schema; this assumes a foreign key in Damage table

    private ArrayList<Damage> damages = new ArrayList<Damage>();

    private Long timestamp;




}
