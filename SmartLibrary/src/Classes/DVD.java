package Classes;

import Interfaces.LibraryItem;

public class DVD extends LibraryItem {

    private int duration; 

    public DVD(String id, String title, int duration) {
        super(id, title);
        this.duration = duration;
    }

    public int getDuration() { 
        return duration; 
    }

    @Override
    public String getDetails() {
        return "DVD - ID: " + getId()
             + ", Title: " + getTitle()
             + ", Duration: " + duration + " min"
             + ", Available: " + isAvailable();
    }
}
