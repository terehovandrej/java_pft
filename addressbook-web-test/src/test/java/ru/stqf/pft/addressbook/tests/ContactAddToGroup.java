package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import ru.stqf.pft.addressbook.model.GroupData;
import ru.stqf.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddToGroup extends TestBase{
    private ContactData addedContact;
    private GroupData targetGroup;
    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        skipIfNotFixed(705);
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
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();

        for (GroupData group : groups) {
            if (!contact.getGroups().contains(group)) {
                targetGroup = group;
                addedContact = contact;
            }else {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test2"));
                app.goTo().gotoHome();
                Groups after = app.db().groups();
                addedContact = contact;
                targetGroup = group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()).withName("test2");
            }
            break;
        }
        app.goTo().gotoHome();
        app.contact().filterGroupByName("[all]");
        Contacts contactsBeforeAdd = app.db().contacts();
        Groups groupsBeforeAdd = new Groups(contactsBeforeAdd.getContactById(addedContact.getId()).getGroups());
        app.contact().addToGroup(addedContact, targetGroup);
        app.goTo().gotoHome();
        Contacts contactsAfterAdd = app.db().contacts();
        Groups groupsAfterAdd = new Groups(contactsAfterAdd.getContactById(addedContact.getId()).getGroups());
        Groups modifiedBeforeAdd = groupsBeforeAdd.withAdded(targetGroup);
        assertThat(groupsAfterAdd, equalTo(modifiedBeforeAdd));
    }
}





