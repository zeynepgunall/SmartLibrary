package service;

import java.util.ArrayList;
import java.util.List;

import model.LibraryItem;
import model.Member;
import model.Borrowable;

public class LibraryManager {

    private List<LibraryItem> items = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> getAllItems() {
        return items;
    }

    public LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

   
    public void addMember(Member member) {
        members.add(member);
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public Member findMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    
    public boolean borrowItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item != null && item instanceof Borrowable && item.isAvailable()) {
            ((Borrowable) item).borrowItem();
            return true;
        }
        return false;
    }

    public boolean returnItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item != null && item instanceof Borrowable && !item.isAvailable()) {
            ((Borrowable) item).returnItem();
            return true;
        }
        return false;
    }
}

