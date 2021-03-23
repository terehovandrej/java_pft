package ru.stqf.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import ru.stqf.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

//    public void fillContactForm(ContactData contactData, boolean creation) {
//        type(By.name("firstname"), contactData.getName());
//        type(By.name("lastname"), contactData.getLastName());
//        type(By.name("mobile"), contactData.getMobilePhone());
//        type(By.name("email"), contactData.getEmail());
//        type(By.name("address"), contactData.getAddress());
//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
//    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
    }


    public void editSelectedContacts(int i) {
        click(By.xpath("(//img[@alt='Edit'])[" + i + "]"));
    }

    public void initContactModificationById(int id) {
        click(By.xpath("//input[@id='" + id + "']/../..//img[@title='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact);
        submitContactCreation();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName));
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            String allEmails = element.findElement(By.xpath("./td[5]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));

        }
        return contacts;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
    }

    public void addToGroup(ContactData contact, GroupData group) {

        selectContactById(contact.getId());
        selectGroup(group);
        click(By.name("add"));
    }

    public void selectGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
    }

    public void filterGroup(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));
    }

    public void filterGroupByName(String name) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }



    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String firstEmail = wd.findElement(By.name("email")).getAttribute("value");
        String secondEmail = wd.findElement(By.name("email2")).getAttribute("value");
        String thirdEmail = wd.findElement(By.name("email3")).getAttribute("value");
        String address =  wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname).withHomePhone(home)
                .withWorkPhone(work).withMobilePhone(mobile).withFirstEmail(firstEmail)
                .withSecondEmail(secondEmail).withThirdEmail(thirdEmail).withAddress(address);
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        filterGroup(group);
        selectContactById(contact.getId());
        click(By.name("remove"));
    }

    public void deleteRelationIfExist(ContactData contact, GroupData group) {
        Set<GroupData> groups = contact.getGroups();
        for (GroupData gr : groups ){
            if (gr.getId() == (group.getId())){
                removeFromGroup(contact, group);
            }
        }
    }

    public boolean checkRelationIsExist(ContactData contact, GroupData group) {
        Set<GroupData> groups = contact.getGroups();
        for (GroupData gr : groups ){
            if (gr.getId() == (group.getId())){
                return true;
            }
        }
        return false;
    }


    public void addRelationIfNotExist(ContactData contact, GroupData group){
        Set<GroupData> groups = contact.getGroups();
        if (groups.size() == 0){
            addToGroup(contact, group);
        }
        else {
            for (GroupData gr : groups ){
                if (gr.getId() == (group.getId())){
                    break;
                }
            }
        addToGroup(contact, group);
        }
    }


}