package org.wcci.campuslibraries;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CampusStorage {
    private Map<String, Campus> campuses = new HashMap<>();

    public CampusStorage(){
        Book book1 =new Book("Head First Java", "Kathy Sierra", "4440333044-2","Good book to learn Java.");
        Book book2 =new Book("Test Driven Development by Example", "Kent Beck", "44443333044-2","Good book to learn TDD.");
        campuses.put("Columbus",new Campus("Columbus","In Columbus", new ArrayList<>(List.of(book1, book2))));
        Book book3 =new Book("Head First C Sharp", "Bert Bates", "422-333044-2","Good book to learn C#.");
        Book book4 =new Book("Agile Development Principles, Patterns, and Practices for C Sharp", "Micah Martin", "4543-54-2","SOLID principles and more for C#.");
        campuses.put("Cleveland", new Campus("Cleveland", "Above a casino in downtown Cleveland", new ArrayList<>(List.of(book3,book4))));


    }

    public Campus findCampusByName(String campusName) {
        return campuses.get(campusName);
    }
}
