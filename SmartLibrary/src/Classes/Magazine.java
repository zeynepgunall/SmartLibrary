package Classes;

import Interfaces.LibraryItem;

public class Magazine extends LibraryItem {

    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { 
        return issueNumber; 
    }

    @Override
    public String getDetails() {
        return "Magazine - ID: " + getId()
             + ", Title: " + getTitle()
             + ", Issue: " + issueNumber
             + ", Available: " + isAvailable();
    }
}
