package ru.stqf.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String name;
    private final String lastName;
    private final String mobilePhone;
    private final String email;
    private final String address;
    private String group;


    public void setId(int id) {
        this.id = id;
    }

    public ContactData(int id, String name, String lastName, String mobilePhone, String email, String address, String group) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public ContactData(String name, String lastName, String mobilePhone, String email, String address, String group) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.address = address;
        this.group = group;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
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
