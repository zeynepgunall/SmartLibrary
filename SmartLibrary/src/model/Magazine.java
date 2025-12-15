package model;

public class Magazine extends LibraryItem implements Borrowable {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { 
        return issueNumber; 
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
        return "Magazine - ID: " + getId() + ", Title: " + getTitle() +
               ", Issue: " + issueNumber + ", Available: " + isAvailable();
    }
}

