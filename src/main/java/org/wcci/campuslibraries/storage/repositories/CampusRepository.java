package org.wcci.campuslibraries.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.campuslibraries.entities.Campus;

public interface CampusRepository extends CrudRepository<Campus, Long> {
    Campus findByName(String campusName);
}
