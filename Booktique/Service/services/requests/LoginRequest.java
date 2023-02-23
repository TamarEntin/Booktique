package services.requests;

public class LoginRequest extends RequestBase {

    private java.lang.String username;
    private java.lang.String password;

    public LoginRequest(java.lang.String username, java.lang.String password)
    {
        this.username = username;
        this.password = password;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
}
