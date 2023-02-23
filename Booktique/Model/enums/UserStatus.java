package enums;

public enum UserStatus {
    Reader (1),
    Librarian(2),
    Manager(3);

    int _statusValue;
    UserStatus(int statusValue){
        _statusValue = statusValue;
    }

    public int StatusValue(){
        return _statusValue;
    }
}


