package ru.stqf.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String mobilePhone;
    private final String email;
    private final String address;

    public ContactData(String name, String lastName, String mobilePhone, String email, String address) {
        this.name = name;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
