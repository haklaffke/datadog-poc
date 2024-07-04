package de.hannes.datadogpoc.component;

import de.hannes.datadogpoc.controller.ClaimController;
import de.hannes.datadogpoc.controller.DamageController;
import de.hannes.datadogpoc.entities.Claim;
import de.hannes.datadogpoc.entities.Damage;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class DamageAssembler implements RepresentationModelAssembler<Damage, EntityModel<Damage>> {

    @Override
    public EntityModel<Damage> toModel(Damage damage) {
        return EntityModel.of(damage,
                linkTo(methodOn(DamageController.class).one(damage.getDamageID())).withSelfRel(),
                linkTo(methodOn(DamageController.class).all()).withRel("damages"));
    }
}

