package org.wcci.campuslibraries.storage;

import org.springframework.stereotype.Service;
import org.wcci.campuslibraries.entities.Campus;
import org.wcci.campuslibraries.exceptions.ResourceNotFoundException;
import org.wcci.campuslibraries.storage.repositories.CampusRepository;

import java.util.Optional;

@Service
public class CampusStorage {
    private CampusRepository campusRepo;

    public CampusStorage(CampusRepository campusRepo) {
        this.campusRepo = campusRepo;
    }

    public Campus findCampusByName(String campusName) {
        Campus retrievedCampus;
        Optional<Campus> campusOptional = campusRepo.findByName(campusName);
        if(campusOptional.isEmpty()){
            throw new ResourceNotFoundException("Campus named "+ campusName+ " not found.");
        } else {
            retrievedCampus=campusOptional.get();
        }

        return retrievedCampus;
    }

    public Iterable<Campus> findAllCampuses() {
        return campusRepo.findAll();
    }

    public void addCampus(Campus campus) {
        campusRepo.save(campus);
    }
}
