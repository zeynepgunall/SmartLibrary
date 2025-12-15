package Interfaces;

public abstract class LibraryItem implements Borrowable {

    protected String id;
    protected String title;
    protected boolean available;

    private static int itemCount = 0;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
        itemCount++; 
    }

    public static int getItemCount() {
        return itemCount;
    }

    @Override
    public void borrowItem() {
        if (available) {
            available = false;
        }
    }

    @Override
    public void returnItem() {
        if (!available) {
            available = true;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getDetails();
}
