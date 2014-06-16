package com.university.domain.applicantlist;

public class ApplicantList {
    private Integer applicantId;
    private Integer facultyId;
    
    public Integer getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }
    public Integer getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 13*hash + (applicantId == null ? 0 : applicantId.hashCode());
	hash = 13*hash + (facultyId == null ? 0 : facultyId.hashCode());
	return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null)	return false;
	if (getClass() != obj.getClass())	return false;
	ApplicantList list = (ApplicantList) obj;
	
	if ( (applicantId == null) ? list.applicantId != null : !applicantId.equals(list.applicantId) ) 
	    return false;
	if ( (facultyId == null) ? list.facultyId != null : !facultyId.equals(list.facultyId) ) 
	    return false;
	
	return true;
    }
    
}
