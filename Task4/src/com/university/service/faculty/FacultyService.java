package com.university.service.faculty;

import java.util.List;

import com.university.domain.faculty.Faculty;

public interface FacultyService {
    public boolean insertFaculty (Faculty faculty);
    public boolean deleteFaculty (int id);
    public List<Faculty> findAllFaculties ();
    public Faculty findFaculty (int id);
    public Faculty findFaculty (String name);
}
