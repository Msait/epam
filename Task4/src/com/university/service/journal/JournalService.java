package com.university.service.journal;

import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;

public interface JournalService {
    public  Applicant findStudentInJournal (int id);
    public boolean insertToJournal (Applicant applicant);
    public boolean insertToJournal (int id);
    public Map<Applicant, Faculty>  getAllStudents();
}
