package ru.stqf.pft.addressbook;

public class ContactData {
    private final String name;
    private final String middleName;
    private final String mobilePhone;
    private final String email;
    private final String address;

    public ContactData(String name, String middleName, String mobilePhone, String email, String address) {
        this.name = name;
        this.middleName = middleName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
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
