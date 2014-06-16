package com.university.domain.journal;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<Integer> journalId = new ArrayList<Integer>();
    private List<Integer> applicantId = new ArrayList<Integer>();
    
    public List<Integer> getJournalId() {
        return journalId;
    }
    public void setJournalId(List<Integer> journalId) {
        this.journalId = journalId;
    }
    public void setJournalId(Integer journalId) {
	if (this.journalId == null)
	    this.journalId = new ArrayList<Integer>();
	this.journalId.add(journalId);
    }
    public List<Integer> getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(Integer applicantId) {
	if (this.applicantId == null)
	    
	    this.applicantId = new ArrayList<Integer>();
	
        this.applicantId.add( applicantId );
    }
    public void setApplicantId(List<Integer> applicantId) {
	this.applicantId = applicantId;
    }
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 13*hash + (journalId == null ? 0 : journalId.hashCode());
	hash = 13*hash + (applicantId == null ? 0 : applicantId.hashCode());
	return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (obj == null)	return false;
	if (getClass() != obj.getClass())	return false;
	Journal journal = (Journal) obj;
	
	if ( (journalId == null) ? journal.journalId != null : !journalId.equals(journal.journalId) ) 
	    return false;
	if ( (applicantId == null) ? journal.applicantId != null : !applicantId.equals(journal.applicantId) ) 
	    return false;
	
	return true;
    }
    
    
}
