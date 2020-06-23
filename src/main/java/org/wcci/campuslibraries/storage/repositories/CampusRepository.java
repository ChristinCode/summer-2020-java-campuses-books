package org.wcci.campuslibraries.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.campuslibraries.entities.Campus;

import java.util.Optional;

public interface CampusRepository extends CrudRepository<Campus, Long> {
    Optional<Campus> findByName(String campusName);
}
