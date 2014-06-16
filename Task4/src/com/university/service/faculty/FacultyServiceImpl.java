package com.university.service.faculty;

import java.util.List;

import com.university.domain.faculty.Faculty;
import com.university.repository.faculty.FacultyRepository;

public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository repository;

    public FacultyServiceImpl(FacultyRepository repository) {
	this.repository = repository;
    }

    @Override
    public boolean insertFaculty(Faculty faculty) {
	return repository.insertFaculty(faculty);
    }

    @Override
    public boolean deleteFaculty(int id) {
	return repository.deleteFaculty(id);
    }

    @Override
    public List<Faculty> findAllFaculties() {
	return repository.findAllFaculties();
    }

    @Override
    public Faculty findFaculty(int id) {
	return repository.findFaculty(id);
    }

    @Override
    public Faculty findFaculty(String name) {
	return repository.findFaculty(name);
    }

}
