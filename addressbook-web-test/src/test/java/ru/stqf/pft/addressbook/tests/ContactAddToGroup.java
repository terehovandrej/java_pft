package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import ru.stqf.pft.addressbook.model.GroupData;
import ru.stqf.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().gotoContactCreation();
            app.contact().createContact(new ContactData().withName("Andrey").withLastName("Terekhov")
                    .withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withHomePhone("89651237160")
                    .withWorkPhone("89651237160").withAddress("pushkina"));
        }
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().gotoHome();

    }
    @Test
    public void testContactAddToGroup() throws Exception {
        Contacts contacts = app.db().contacts();
        ContactData addedContact = contacts.iterator().next();
        Groups groups = app.db().groups();
        GroupData targetGroup = groups.iterator().next();
        app.contact().deleteRelationIfExist(addedContact, targetGroup);
        app.goTo().gotoHome();
        app.contact().filterGroupByName("[all]");
        Contacts contactsBeforeAdd = app.db().contacts();
        Groups groupsBeforeAdd = new Groups(contactsBeforeAdd.getContactById(addedContact.getId()).getGroups());
        app.contact().addToGroup(addedContact, targetGroup);
        app.goTo().gotoHome();
        Contacts contactsAfterAdd = app.db().contacts();
        Groups groupsAfterAdd = new Groups(contactsAfterAdd.getContactById(addedContact.getId()).getGroups());
        System.out.println("groups Before Add " + groupsBeforeAdd);
        System.out.println("groups After Add " + groupsAfterAdd );
        assertThat(groupsAfterAdd, equalTo(groupsBeforeAdd.withAdded(targetGroup)));
    }
}
