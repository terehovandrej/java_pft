package ru.stqf.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String mobilePhone;
    private final String email;
    private final String address;
    private String group;

    public ContactData(String name, String lastName, String mobilePhone, String email, String address, String group) {
        this.name = name;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}
