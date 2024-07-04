package de.hannes.datadogpoc.controller;

import de.hannes.datadogpoc.entities.Damage;
import de.hannes.datadogpoc.exceptions.DamageNotFoundException;
import de.hannes.datadogpoc.repos.DamageRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/damage")

public class DamageController {
    private DamageRepository damageRepository;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Damage>> all() {
        List<EntityModel<Damage>> damages = new ArrayList<>();
        damageRepository.findAll().forEach(damage -> damages.add(EntityModel.of(damage)));
        return CollectionModel.of(damages);
    }

    @GetMapping("/{id}")
    public EntityModel<Damage> one(@PathVariable Long id) {
        Long damageID = getDamageID();

        Damage damage = damageRepository.findById(id).orElseThrow(() -> new DamageNotFoundException(id));
        return EntityModel.of(damage);
    }

    @PostMapping("/add")
    ResponseEntity<EntityModel<Damage>> newDamage(@RequestBody Damage newDamage) {
        if(!validateNewDamage(newDamage.getDamageID())) {
            return ResponseEntity.badRequest().body(null);
        }

        Damage damage = damageRepository.save(newDamage);


    }

    boolean validateNewDamage(Long newDamageID) {
        AtomicBoolean damageIsValid = new AtomicBoolean(true);
        damageRepository.findAll().forEach(damage -> {
            if(damage.getDamageID().equals(newDamageID))    {
                damageIsValid.set(false);
            }
        });
        return damageIsValid.get();
    }


    private Long getDamageID() {
        Long newDamageID = getDamageID();
        AtomicReference<Long> damageID = new AtomicReference<>(0L);
        damageRepository.findAll().forEach(damage -> {
            if(damage.getDamageID().equals(newDamageID)) {
                damageID.set(damage.getDamageID());
            }
        });
        return damageID.get();
        }



}
