package com.university.repository.journal;

import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.domain.journal.Journal;

public interface JournalRepository {
    
    public boolean insertStudentToJournal (int id);
    public Journal getJournal ();
    public boolean deleteStudentFromJournal (int id);
    public Applicant findStudentInJournal(int id);
    public Map<Applicant, Faculty> getAllStudents();
}
