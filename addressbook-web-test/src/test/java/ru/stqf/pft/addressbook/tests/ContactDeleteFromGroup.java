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
    private ContactData addedContact;
    private GroupData targetGroup;
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
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (contact.getGroups().contains(group)) {
                targetGroup = group;
                addedContact = contact;
                break;
            }
            app.contact().addRelationIfNotExist(contact, group);
            targetGroup = group;
            addedContact = contact;
            break;
        }
        app.goTo().gotoHome();
        app.contact().filterGroupByName("[all]");
        Contacts contactsBeforeDelete = app.db().contacts();
        Groups groupsBeforeDelete = new Groups(contactsBeforeDelete.getContactById(addedContact.getId()).getGroups());
        app.contact().removeFromGroup(addedContact, targetGroup);
        app.goTo().gotoHome();
        Contacts contactsAfterDelete = app.db().contacts();
        Groups groupsAfterDelete = new Groups(contactsAfterDelete.getContactById(addedContact.getId()).getGroups());
        assertThat(groupsAfterDelete, equalTo(groupsBeforeDelete.without(targetGroup)));
    }
}
