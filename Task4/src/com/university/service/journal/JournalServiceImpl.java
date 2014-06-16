package com.university.service.journal;

import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.journal.JournalRepository;

public class JournalServiceImpl implements JournalService {

    private JournalRepository repository;
    
    public JournalServiceImpl(JournalRepository repository) {
	this.repository = repository;
    }

    @Override
    public Applicant findStudentInJournal(int id) {
	return repository.findStudentInJournal(id);
    }

    @Override
    public boolean insertToJournal(Applicant applicant) {
	return repository.insertStudentToJournal(applicant.getApplicantId());
    }

    @Override
    public boolean insertToJournal(int id) {
	return repository.insertStudentToJournal(id);
    }

    @Override
    public Map<Applicant, Faculty> getAllStudents() {
	return repository.getAllStudents();
    }

}
