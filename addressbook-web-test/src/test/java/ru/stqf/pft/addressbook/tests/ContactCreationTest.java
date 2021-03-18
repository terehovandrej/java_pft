package ru.stqf.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;
import ru.stqf.pft.addressbook.model.Contacts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().gotoHome();
        Contacts before = app.db().contacts();
        app.goTo().gotoContactCreation();
        app.contact().fillContactForm(contact);
        app.contact().submitContactCreation();
        app.goTo().gotoHome();
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));
    }
}
