package org.wcci.campuslibraries;

import org.springframework.data.repository.CrudRepository;

public interface CampusRepository extends CrudRepository<Campus, Long> {
    Campus findByName(String campusName);
}
