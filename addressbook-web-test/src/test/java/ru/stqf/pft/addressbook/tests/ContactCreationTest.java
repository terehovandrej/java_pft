package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test()
    public void testContactCreation() throws Exception {
        app.goTo().gotoHome();
        Contacts before = app.contact().all();
        app.goTo().gotoContactCreation();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withName("Andrey").withLastName("Terekhov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withGroup("test1").withPhoto(photo);
        app.contact().fillContactForm(contact);
        app.contact().submitContactCreation();
        app.goTo().gotoHome();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));
    }
}
