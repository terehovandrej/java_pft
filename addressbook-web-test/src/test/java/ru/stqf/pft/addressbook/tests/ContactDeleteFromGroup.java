package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import ru.stqf.pft.addressbook.model.GroupData;
import ru.stqf.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroup extends TestBase{
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
    public void testContactDeleteFromGroup() throws Exception {
        Contacts contacts = app.db().contacts();
        ContactData addedContact = contacts.iterator().next();
        Groups groups = app.db().groups();
        GroupData targetGroup = groups.iterator().next();
        app.contact().addRelationIfNotExist(addedContact, targetGroup);
        app.goTo().gotoHome();
        app.contact().filterGroupByName("[all]");
        Contacts contactsBeforeDelete = app.db().contacts();
        Groups groupsBeforeDelete = new Groups(contactsBeforeDelete.getContactById(addedContact.getId()).getGroups());
        app.contact().removeFromGroup(addedContact, targetGroup);
        app.goTo().gotoHome();
        Contacts contactsAfterDelete = app.db().contacts();
        Groups groupsAfterDelete = new Groups(contactsAfterDelete.getContactById(addedContact.getId()).getGroups());
        System.out.println("groups Before delete " + groupsBeforeDelete);
        System.out.println("groups After delete" + groupsAfterDelete);
        assertThat(groupsAfterDelete, equalTo(groupsBeforeDelete.without(targetGroup)));
    }
}
