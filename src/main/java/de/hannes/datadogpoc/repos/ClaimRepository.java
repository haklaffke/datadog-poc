package de.hannes.datadogpoc.repos;

import de.hannes.datadogpoc.entities.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepository extends CrudRepository<Claim, Long> {
}
