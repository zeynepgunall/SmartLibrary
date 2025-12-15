package Classes;

import java.time.LocalDate;

import Interfaces.LibraryItem;

public class BorrowTransaction {

    private Member member;
    private LibraryItem item;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    public BorrowTransaction(Member member, LibraryItem item) {
        this.member = member;
        this.item = item;
        this.borrowDate = LocalDate.now();
        this.returned = false;
        this.returnDate = null;
    }

    public Member getMember() {
        return member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned(LocalDate date) {
        this.returned = true;
        this.returnDate = date;
    }

    @Override
    public String toString() {
        String status = returned ? ("Returned on " + returnDate) : "NOT RETURNED";
        return "TX | member=" + member.getMemberId()
                + " | item=" + item.getId()
                + " | borrowed=" + borrowDate
                + " | " + status;
    }
}
