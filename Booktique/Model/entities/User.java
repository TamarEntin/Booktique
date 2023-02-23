package entities;

import java.util.Date;

public class User extends Entity {
	private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private int userStatus;
    private Date created;
    private int gender;
    private String address;
    private String email;
    private String phone;
    private boolean isActive;
    public User()
    {

    }

    public User(String id, String userName, String firstName, String lastName, String password, int userStatus, Date created, int gender, String address, String email, String phone, boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userStatus = userStatus;
        this.created = created;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }
    
    @Override
	public String toString()
	{
		return "User( Id:" + id + " | Username:" + userName + " | First Name:" + firstName +
				" | Last Name:" + lastName + " | Password:" + password + " | Address:" + address + 
				" | Email:" + email + " | Phone:" + phone + " | Gender:" + gender + " | Status:" + userStatus + 
				" | Operative:" + isActive + " | Account Created:" + created + ")";			
	}

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
