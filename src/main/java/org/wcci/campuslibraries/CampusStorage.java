package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

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
}
