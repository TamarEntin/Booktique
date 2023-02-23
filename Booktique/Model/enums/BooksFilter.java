package enums;


public enum BooksFilter {
    AvailableOnly(2),
    All(1);

    int statusValue;
    BooksFilter(int statusValue){ this.statusValue = statusValue;}

    public int StatusValue(){ return this.statusValue;}
}
