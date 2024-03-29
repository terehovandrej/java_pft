package ru.stqf.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
        public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().gotoContactCreation();
            app.contact().createContact(new ContactData().withName("Andrey").withLastName("Terekhov")
                    .withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withHomePhone("89651237160")
                    .withWorkPhone("89651237160").withAddress("pushkina"));
            app.goTo().gotoHome();
        }
    }
    @Test
    public void testContactModification() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("Vasiliy")
                .withLastName("Ivanov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com")
                .withHomePhone("89651237160").withWorkPhone("89651237160").withAddress("pushkina");
        app.contact().filterGroupByName("[all]");
        app.contact().modify(contact);
        app.goTo().gotoHome();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}