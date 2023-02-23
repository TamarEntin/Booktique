package enums;

public enum ResponseStatus {
    Error(1),
    OK(2);

    int errorCode;
    ResponseStatus(int statusValue){ this.errorCode = statusValue;}

    public int errorCode(){ return this.errorCode;}
}
