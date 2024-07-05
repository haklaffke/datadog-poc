package de.hannes.datadogpoc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "damages")
@Getter
@Setter
public class Damage {

    @Id
    @GeneratedValue
    private Long damageID;

    private int price;


}
