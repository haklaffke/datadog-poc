package de.hannes.datadogpoc.controller;

import de.hannes.datadogpoc.component.ClaimAssembler;
import de.hannes.datadogpoc.entities.Claim;
import de.hannes.datadogpoc.entities.Damage;
import de.hannes.datadogpoc.exceptions.ClaimNotFoundException;
import de.hannes.datadogpoc.repos.ClaimRepository;
import de.hannes.datadogpoc.repos.DamageRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.lang.System.currentTimeMillis;

@RestController
@RequestMapping("/claim")

public class ClaimController {
    private ClaimAssembler claimAssembler;
    private ClaimRepository claimRepository;
    private DamageRepository damageRepository;

    public ClaimController(ClaimAssembler claimAssembler, DamageRepository damageRepository, ClaimRepository claimRepository) {
        this.claimAssembler = claimAssembler;
        this.claimRepository = claimRepository;
        this.damageRepository = damageRepository;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Claim>> all() {
        List<EntityModel<Claim>> claims = new ArrayList<>();
        claimRepository.findAll().forEach(claim -> claims.add(EntityModel.of(claim)));
        return CollectionModel.of(claims);
    }

    @GetMapping("/{id}")
    public EntityModel<Claim> one(@PathVariable Long id) {
        Claim claimFoundByID = claimRepository.findById(id).orElseThrow(() -> new ClaimNotFoundException(id));
        return EntityModel.of(claimFoundByID);
    }

    @PostMapping("/add")
    public ResponseEntity<EntityModel<Claim>> newClaim(@RequestBody Map<String, List<Long>> payload) {
        Claim newClaim = new Claim();
        newClaim.setTimestamp(currentTimeMillis());
        newClaim.setStatus(Claim.status.CREATED);
        List<Long> damagesByID = payload.get("damagesByID");
        for(Long damageID : damagesByID) {
            Damage damage = damageRepository.findById(damageID).orElseThrow(() -> new ClaimNotFoundException(damageID));
            newClaim.getDamages().add(damage);
        }
        EntityModel<Claim> entityModel = claimAssembler.toModel(claimRepository.save(newClaim));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityModel<Claim>> updateClaimStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Claim claim = claimRepository.findById(id).orElseThrow(() -> new ClaimNotFoundException(id));
        String statusStr = payload.get("status");
        Claim.status newStatus = Claim.status.valueOf(statusStr.toUpperCase());
        claim.setStatus(newStatus);
        Claim updatedClaim = claimRepository.save(claim);
        EntityModel<Claim> entityModel = claimAssembler.toModel(updatedClaim);
        return ResponseEntity.ok(entityModel);
    }
}
