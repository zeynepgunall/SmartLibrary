package model;

public class DVD extends LibraryItem implements Borrowable {
    private int duration; 

    public DVD(String id, String title, int duration) {
        super(id, title);
        this.duration = duration;
    }

    public int getDuration() { 
        return duration; 
    }

    @Override
    public void borrowItem() {
        if (isAvailable()) {
            setAvailable(false);
        }
    }

    @Override
    public void returnItem() {
        setAvailable(true);
    }

    @Override
    public String getDetails() {
        return "DVD - ID: " + getId() + ", Title: " + getTitle() +
               ", Duration: " + duration + " min, Available: " + isAvailable();
    }
}

