package org.wcci.campuslibraries.storage;

import org.springframework.stereotype.Service;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.storage.repositories.CampusRepository;

@Service
public class CampusStorage {
    private CampusRepository campusRepo;

    public CampusStorage(CampusRepository campusRepo) {
        this.campusRepo = campusRepo;
    }

    public Campus findCampusByName(String campusName) {
        return campusRepo.findByName(campusName);
    }

    public Iterable<Campus> findAllCampuses() {
        return campusRepo.findAll();
    }

    public void addCampus(Campus campus) {
        campusRepo.save(campus);
    }
}
