package com.university.domain.subject;


public class Subject {
    private Integer subjectId;
    private String subjectName;
    
    public Integer getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 13*hash + (subjectId == null ? 0 : subjectId.hashCode());
	hash = 13*hash + (subjectName == null ? 0 : subjectName.hashCode());
	return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null)	return false;
	if (getClass() != obj.getClass())	return false;
	Subject subject = (Subject) obj;
	
	if ( (subjectId == null) ? subject.subjectId != null : !subjectId.equals(subject.subjectId) ) 
	    return false;
	if ( (subjectName == null) ? subject.subjectName != null : !subjectName.equals(subject.subjectName) ) 
	    return false;
	
	return true;
    }
}
