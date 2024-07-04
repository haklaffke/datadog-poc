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
    private ArrayList<Damage> damages = new ArrayList<Damage>();

    private Long timestamp;




}
