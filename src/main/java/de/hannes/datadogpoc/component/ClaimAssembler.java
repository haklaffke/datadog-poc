package de.hannes.datadogpoc.component;

import de.hannes.datadogpoc.controller.ClaimController;
import de.hannes.datadogpoc.entities.Claim;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ClaimAssembler implements RepresentationModelAssembler<Claim, EntityModel<Claim>> {

    @Override
    public EntityModel<Claim> toModel(Claim claim) {
        return EntityModel.of(claim,
                linkTo(methodOn(ClaimController.class).one(claim.getClaimID())).withSelfRel(),
                linkTo(methodOn(ClaimController.class).all()).withRel("claims"));
    }
}

