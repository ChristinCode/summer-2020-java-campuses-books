package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CampusStorage {
    private Map<String, Campus> campuses = new HashMap<>();

    public CampusStorage(){
        campuses.put("Columbus",new Campus("Columbus","In Columbus", null));
    }

    public Campus findCampusByName(String campusName) {
        return campuses.get(campusName);
    }
}
