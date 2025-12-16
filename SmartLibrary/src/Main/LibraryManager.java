package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Classes.BorrowTransaction;
import Classes.Member;
import Interfaces.LibraryItem;

public final class LibraryManager {

    // --- System Class: all members static ---
    private static final List<LibraryItem> items = new ArrayList<>();
    private static final List<Member> members = new ArrayList<>();
    private static final List<BorrowTransaction> transactions = new ArrayList<>();

    private static final Set<String> itemIds = new HashSet<>();
    private static final Set<String> memberIds = new HashSet<>();

    // Prevent instantiation
    private LibraryManager() {}

    // --- Helpers ---
    private static String norm(String s) {
        return (s == null) ? null : s.trim();
    }

    // --- Add ---
    public static boolean addItem(LibraryItem item) {
        if (item == null) return false;

        String id = norm(item.getId());
        if (id == null || id.isEmpty()) return false;
        if (itemIds.contains(id)) return false;

        items.add(item);
        itemIds.add(id);
        return true;
    }

    public static boolean addMember(Member member) {
        if (member == null) return false;

        String id = norm(member.getMemberId());
        if (id == null || id.isEmpty()) return false;
        if (memberIds.contains(id)) return false;

        members.add(member);
        memberIds.add(id);
        return true;
    }

    // --- Display ---
    public static String displayItems() {
        if (items.isEmpty()) return "No items found.";
        StringBuilder sb = new StringBuilder();
        for (LibraryItem item : items) {
            sb.append(item.getDetails()).append("\n");
        }
        return sb.toString();
    }

    public static String displayMembers() {
        if (members.isEmpty()) return "No members found.";
        StringBuilder sb = new StringBuilder();
        for (Member m : members) {
            sb.append(m).append("\n");
        }
        return sb.toString();
    }

    public static String displayTransactions() {
        if (transactions.isEmpty()) return "No transactions found.";
        StringBuilder sb = new StringBuilder();
        for (BorrowTransaction tx : transactions) {
            sb.append(tx).append("\n");
        }
        return sb.toString();
    }

    // --- Find ---
    public static LibraryItem findItemById(String id) {
        String key = norm(id);
        if (key == null || key.isEmpty()) return null;

        for (LibraryItem item : items) {
            String itemId = norm(item.getId());
            if (key.equals(itemId)) return item;
        }
        return null;
    }

    public static Member findMemberById(String id) {
        String key = norm(id);
        if (key == null || key.isEmpty()) return null;

        for (Member m : members) {
            String memberId = norm(m.getMemberId());
            if (key.equals(memberId)) return m;
        }
        return null;
    }

    // --- Delete ---
    public static boolean deleteItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item == null) return false;

        // borrowed item cannot be deleted
        if (!item.isAvailable()) return false;

        items.remove(item);
        itemIds.remove(norm(item.getId()));
        return true;
    }

    public static boolean deleteMember(String memberId) {
        Member member = findMemberById(memberId);
        if (member == null) return false;

        // cannot delete member with an active borrow
        String mid = norm(member.getMemberId());
        for (BorrowTransaction tx : transactions) {
            if (!tx.isReturned() && norm(tx.getMember().getMemberId()).equals(mid)) {
                return false;
            }
        }

        members.remove(member);
        memberIds.remove(mid);
        return true;
    }

    // --- Borrow / Return ---
    public static boolean borrowItem(String memberId, String itemId) {
        Member member = findMemberById(memberId);
        LibraryItem item = findItemById(itemId);

        if (member == null || item == null) return false;
        if (!item.isAvailable()) return false;

        item.borrowItem();
        transactions.add(new BorrowTransaction(member, item));
        return true;
    }

    public static boolean returnItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item == null) return false;

        // if it's already available, there's nothing to return
        if (item.isAvailable()) return false;

        String iid = norm(item.getId());
        for (int i = transactions.size() - 1; i >= 0; i--) {
            BorrowTransaction tx = transactions.get(i);
            if (!tx.isReturned() && norm(tx.getItem().getId()).equals(iid)) {
                tx.markReturned(LocalDate.now());
                item.returnItem();
                return true;
            }
        }
        return false;
    }

    // --- Counts ---
    public static int getAvailableItemCount() {
        int count = 0;
        for (LibraryItem item : items) {
            if (item.isAvailable()) count++;
        }
        return count;
    }

    public static int getBorrowedItemCount() {
        return items.size() - getAvailableItemCount();
    }

    // --- Safe getters (do not expose internal lists directly) ---
    public static List<LibraryItem> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    public static List<Member> getAllMembers() {
        return Collections.unmodifiableList(members);
    }

    public static List<BorrowTransaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
