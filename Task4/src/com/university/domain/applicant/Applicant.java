package com.university.domain.applicant;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private Integer applicantId;
    private String applicantName;
    private String applicantLastName;
    private Integer applicantFacultyId;
    private Integer applicantCertificate;
    private List<Integer> exams;
    
    public Integer getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }
    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    public String getApplicantLastName() {
        return applicantLastName;
    }
    public void setApplicantLastName(String applicantLastName) {
        this.applicantLastName = applicantLastName;
    }
    public Integer getApplicantFacultyId() {
        return applicantFacultyId;
    }
    public void setApplicantFacultyId(Integer applicantFacultyId) {
        this.applicantFacultyId = applicantFacultyId;
    }
    public Integer getApplicantCertificate() {
        return applicantCertificate;
    }
    public void setApplicantCertificate(Integer applicantCertificate) {
        this.applicantCertificate = applicantCertificate;
    }
    public List<Integer> getExams() {
        return exams;
    }
    public void setExams(List<Integer> exams) {
        this.exams = exams;
    }
    public void setExam(Integer exam) {
	if (exams == null) 
	    exams = new ArrayList<Integer>();
	this.exams.add(exam);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        builder.append(applicantId).append(", ");
        builder.append(applicantName).append(", ");
        builder.append(applicantLastName).append(", ");
        builder.append(applicantFacultyId).append(", ");
        builder.append(applicantCertificate).append(", (");
        
        for (Integer exam : exams) {
	    builder.append( exam ).append(", ");
	}
        
        return builder.append(")]").toString();
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 13*hash + (applicantId == null ? 0 : applicantId.hashCode()); 
	hash = 13*hash + (applicantName == null ? 0 : applicantName.hashCode()); 
	hash = 13*hash + (applicantLastName == null ? 0 : applicantLastName.hashCode()); 
	hash = 13*hash + (applicantFacultyId == null ? 0 : applicantFacultyId.hashCode()); 
	hash = 13*hash + (applicantCertificate == null ? 0 : applicantCertificate.hashCode()); 
	hash = 13*hash + (exams == null ? 0 : exams.hashCode()); 
	return hash;
    }
    
    @Override
    public boolean equals(Object object) {
	if (object == null)	return false;
	if (getClass() != object.getClass())	return false;
	final Applicant applicant = (Applicant) object;
	
	if ( (applicantId == null) ? (applicant.applicantId != null) : (!applicantId.equals( applicant.applicantId )) )
	    return false;
	if ( (applicantName == null) ? (applicant.applicantName != null) : (!applicantName.equals( applicant.applicantName )) )
	    return false;
	if ( (applicantLastName == null) ? (applicant.applicantLastName != null) : (!applicantLastName.equals( applicant.applicantLastName )) )
	    return false;
	if ( (applicantFacultyId == null) ? (applicant.applicantFacultyId != null) : (!applicantFacultyId.equals( applicant.applicantFacultyId )) )
	    return false;
	if ( (applicantCertificate == null) ? (applicant.applicantCertificate != null) : (!applicantCertificate.equals( applicant.applicantCertificate )) )
	    return false;
	if ( (exams == null) ? (applicant.exams != null) : (!exams.equals( applicant.exams )) )
	    return false;
	
	return true;
    }
    
}
