package com.university.service.applicants;

import java.util.List;
import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;

public interface ApplicantService {
    public boolean insertApplicant(Applicant applicant);
    public boolean deleteApplicant(int id);
    public Applicant findApplicant(int id);
    public Applicant findApplicant(String name);
    public List<Applicant> getAllApplicant();
    public Map<Applicant, Faculty> getAllApplicantWithFaculty();
    public Map<Applicant, Faculty> getRegisteredApplicantWithFaculty();
    public Map<Applicant, Faculty> getUnRegisteredApplicantWithFaculty();
}
