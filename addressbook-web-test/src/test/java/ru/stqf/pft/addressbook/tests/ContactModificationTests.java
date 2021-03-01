package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.goTo().gotoContactCreation();
            app.getContactHelper().createContact(new ContactData().withName("Andrey").withLastName("Terekhov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withGroup("test1"));
            app.goTo().gotoHome();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editSelectedContacts(before.size());
        ContactData contact = new ContactData().withName("Andrey").withLastName("Ivanov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withGroup("test1");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.goTo().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}