package com.university.service.applicants;

import java.util.List;
import java.util.Map;

import com.university.domain.applicant.Applicant;
import com.university.domain.faculty.Faculty;
import com.university.repository.applicant.ApplicantRepository;

public class ApplicantServiceImpl implements ApplicantService {

    ApplicantRepository repository;

    public ApplicantServiceImpl(ApplicantRepository repository) {
	this.repository = repository;
    }
    
    @Override
    public boolean insertApplicant(Applicant applicant) {
	return repository.insertApplicant(applicant);
    }

    @Override
    public boolean deleteApplicant(int id) {
	return repository.deleteApplicant(id);
    }

    @Override
    public Applicant findApplicant(int id) {
	return repository.findApplicant(id);
    }

    @Override
    public Applicant findApplicant(String name) {
	return repository.findApplicant(name);
    }

    @Override
    public List<Applicant> getAllApplicant() {
	return repository.getAllApplicant();
    }

    @Override
    public Map<Applicant, Faculty> getAllApplicantWithFaculty() {
	return repository.getAllApplicantWithFaculty();
    }

    @Override
    public Map<Applicant, Faculty> getRegisteredApplicantWithFaculty() {
	return repository.getRegisteredApplicantWithFaculty();
    }

    @Override
    public Map<Applicant, Faculty> getUnRegisteredApplicantWithFaculty() {
	return repository.getUnRegisteredApplicantWithFaculty();
    }

}
