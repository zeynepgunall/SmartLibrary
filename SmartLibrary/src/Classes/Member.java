package Classes;

public class Member {

    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return memberId + " - " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member other = (Member) o;
        return memberId.equals(other.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}
