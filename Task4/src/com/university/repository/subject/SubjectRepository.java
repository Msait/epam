package com.university.repository.subject;

import java.util.List;

import com.university.domain.subject.Subject;

public interface SubjectRepository {
    public Subject getSubject(int id);
    public boolean insertSubject(Subject subject);
    public List<Subject> getAllSubjects();
    public List<Subject> getAllSubjectsForFaculty(int id);
}
