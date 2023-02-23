package enums;

public enum BorrowStatus {
    Borrowed(1),
    WaitingForReturnApproval(2),
    Approved(3);

    int statusValue;
    BorrowStatus(int statusValue){ this.statusValue = statusValue;}

    public int StatusValue(){ return this.statusValue;}
}
