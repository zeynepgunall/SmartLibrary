package model;

public class Book extends LibraryItem implements Borrowable {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() { 
        return author; 
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
        return "Book - ID: " + getId() + ", Title: " + getTitle() +
               ", Author: " + author + ", Available: " + isAvailable();
    }
}

