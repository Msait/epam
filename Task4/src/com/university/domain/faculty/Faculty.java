package com.university.domain.faculty;


public class Faculty {
    private Integer facultyId;
    private String facultyName;
    private Integer facultyLimit;
    private Integer idExams;
    
    public Integer getFacultyId() {
        return facultyId;
    }
    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }
    public String getFacultyName() {
        return facultyName;
    }
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
    public Integer getFacultyLimit() {
        return facultyLimit;
    }
    public void setFacultyLimit(Integer facultyLimit) {
        this.facultyLimit = facultyLimit;
    }
    public Integer getIdExams() {
        return idExams;
    }
    public void setIdExams(Integer idExams) {
        this.idExams = idExams;
    }
    @Override
    public String toString() {
	
        return String.format("[%d, %s, %d, %d]",  facultyId, facultyName, facultyLimit, idExams);
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 13*hash + (facultyId == null ? 0 : facultyId.hashCode());
	hash = 13*hash + (facultyName == null ? 0 : facultyName.hashCode());
	hash = 13*hash + (facultyLimit == null ? 0 : facultyLimit.hashCode());
	hash = 13*hash + (idExams == null ? 0 : idExams.hashCode());
	return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null)	return false;
	if (getClass() != obj.getClass())	return false;
	Faculty faculty = (Faculty) obj;
	
	if ( (facultyId == null) ? faculty.facultyId != null : !facultyId.equals(faculty.facultyId) ) 
	    return false;
	if ( (facultyName == null) ? faculty.facultyName != null : !facultyName.equals(faculty.facultyName) ) 
	    return false;
	if ( (facultyLimit == null) ? faculty.facultyLimit != null : !facultyLimit.equals(faculty.facultyLimit) ) 
	    return false;
	if ( (idExams == null) ? faculty.idExams != null : !idExams.equals(faculty.idExams) ) 
	    return false;
	
	return true;
    }
    
}
