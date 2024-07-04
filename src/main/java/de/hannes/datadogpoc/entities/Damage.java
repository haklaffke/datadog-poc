package de.hannes.datadogpoc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
