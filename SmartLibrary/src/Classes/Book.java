package Classes;

import Interfaces.LibraryItem;

public class Book extends LibraryItem {

    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() { 
        return author; 
    }

    @Override
    public String getDetails() {
        return "Book - ID: " + getId()
             + ", Title: " + getTitle()
             + ", Author: " + author
             + ", Available: " + isAvailable();
    }
}
