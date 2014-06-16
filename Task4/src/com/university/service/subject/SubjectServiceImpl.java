package com.university.service.subject;

import java.util.List;

import com.university.domain.subject.Subject;
import com.university.repository.subject.SubjectRepository;

public class SubjectServiceImpl implements SubjectService {
    
    private SubjectRepository repository;
    
    public SubjectServiceImpl(SubjectRepository repository) {
	super();
	this.repository = repository;
    }

    @Override
    public Subject getSubject(int id) {
	return repository.getSubject(id);
    }

    @Override
    public boolean insertSubject(Subject subject) {
	return repository.insertSubject(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
	return repository.getAllSubjects();
    }

    @Override
    public List<Subject> getAllSubjectsForFaculty(int id) {
	return repository.getAllSubjectsForFaculty(id);
    }

}
