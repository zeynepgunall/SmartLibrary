package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Classes.BorrowTransaction;
import Classes.Member;
import Interfaces.LibraryItem;

public class LibraryManager {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<BorrowTransaction> transactions = new ArrayList<>();

    private final Set<String> itemIds = new HashSet<>();
    private final Set<String> memberIds = new HashSet<>();

    public boolean addItem(LibraryItem item) {
        if (item == null || item.getId() == null) return false;

        String id = item.getId().trim();
        if (id.isEmpty()) return false;
        if (itemIds.contains(id)) return false;

        items.add(item);
        itemIds.add(id);
        return true;
    }

    public boolean addMember(Member member) {
        if (member == null || member.getMemberId() == null) return false;

        String id = member.getMemberId().trim();
        if (id.isEmpty()) return false;
        if (memberIds.contains(id)) return false;

        members.add(member);
        memberIds.add(id);
        return true;
    }

    public String displayItems() {
        if (items.isEmpty()) return "No items found.";
        StringBuilder sb = new StringBuilder();
        for (LibraryItem item : items) {
            sb.append(item.getDetails()).append("\n");
        }
        return sb.toString();
    }

    public String displayMembers() {
        if (members.isEmpty()) return "No members found.";
        StringBuilder sb = new StringBuilder();
        for (Member m : members) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    public String displayTransactions() {
        if (transactions.isEmpty()) return "No transactions found.";
        StringBuilder sb = new StringBuilder();
        for (BorrowTransaction tx : transactions) {
            sb.append(tx.toString()).append("\n");
        }
        return sb.toString();
    }

    public LibraryItem findItemById(String id) {
        if (id == null) return null;
        for (LibraryItem item : items) {
            if (item.getId().equals(id.trim())) {
                return item;
            }
        }
        return null;
    }

    public Member findMemberById(String id) {
        if (id == null) return null;
        for (Member m : members) {
            if (m.getMemberId().equals(id.trim())) {
                return m;
            }
        }
        return null;
    }

    public boolean deleteItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item == null) return false;

        if (!item.isAvailable()) return false;

        items.remove(item);
        itemIds.remove(item.getId());
        return true;
    }

    public boolean deleteMember(String memberId) {
        Member member = findMemberById(memberId);
        if (member == null) return false;

        for (BorrowTransaction tx : transactions) {
            if (!tx.isReturned() &&
                tx.getMember().getMemberId().equals(member.getMemberId())) {
                return false;
            }
        }

        members.remove(member);
        memberIds.remove(member.getMemberId());
        return true;
    }

    public boolean borrowItem(String memberId, String itemId) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);

        if (member == null || item == null) return false;
        if (!item.isAvailable()) return false;

        item.borrowItem();
        BorrowTransaction tx = new BorrowTransaction(member, item);
        transactions.add(tx);
        return true;
    }

    public boolean returnItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item == null || item.isAvailable()) return false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            BorrowTransaction tx = transactions.get(i);
            if (!tx.isReturned() &&
                tx.getItem().getId().equals(item.getId())) {

                tx.markReturned(LocalDate.now());
                item.returnItem();
                return true;
            }
        }
        return false;
    }

    public int getAvailableItemCount() {
        int count = 0;
        for (LibraryItem item : items) {
            if (item.isAvailable()) count++;
        }
        return count;
    }

    public int getBorrowedItemCount() {
        return items.size() - getAvailableItemCount();
    }

    public List<LibraryItem> getAllItems() {
        return items;
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public List<BorrowTransaction> getAllTransactions() {
        return transactions;
    }
}
