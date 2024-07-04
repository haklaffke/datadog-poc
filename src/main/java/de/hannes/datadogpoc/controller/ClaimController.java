package de.hannes.datadogpoc.controller;

import de.hannes.datadogpoc.entities.Claim;
import de.hannes.datadogpoc.exceptions.ClaimNotFoundException;
import de.hannes.datadogpoc.repos.ClaimRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/claim")

public class ClaimController {
    private ClaimRepository claimRepository;

    @GetMapping("/all")
    public CollectionModel<EntityModel<Claim>> all() {
        List<EntityModel<Claim>> claims = new ArrayList<>();
        claimRepository.findAll().forEach(claim -> claims.add(EntityModel.of(claim)));
        return CollectionModel.of(claims);
    }

    @GetMapping("/{id}")
    public EntityModel<Claim> one(@PathVariable Long id) {
        Long claimID = getClaimID();

        Claim claim = claimRepository.findById(id).orElseThrow(() -> new ClaimNotFoundException(id));
        return EntityModel.of(claim);
    }

    @PostMapping("/add")
    ResponseEntity<EntityModel<Claim>> newClaim(@RequestBody Claim newClaim) {
        if(!validateNewClaim(newClaim.getClaimID())) {
            return ResponseEntity.badRequest().body(null);
        }

        Claim claim = claimRepository.save(newClaim);


    }

    boolean validateNewClaim(Long newClaimID) {
        AtomicBoolean claimIsValid = new AtomicBoolean(true);
        claimRepository.findAll().forEach(claim -> {
            if(claim.getClaimID().equals(newClaimID))    {
                claimIsValid.set(false);
            }
        });
        return claimIsValid.get();
    }


    private Long getClaimID() {
        Long newClaimID = getClaimID();
        AtomicReference<Long> claimID = new AtomicReference<>(0L);
        claimRepository.findAll().forEach(claim -> {
            if(claim.getClaimID().equals(newClaimID)) {
                claimID.set(claim.getClaimID());
            }
        });
        return claimID.get();
    }



}
