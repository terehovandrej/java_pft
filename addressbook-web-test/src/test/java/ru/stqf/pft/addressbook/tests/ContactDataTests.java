package ru.stqf.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqf.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDataTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (! app.contact().isThereAContact()){
            app.goTo().gotoContactCreation();
            app.contact().createContact(new ContactData().withName("Andrey").withLastName("Terekhov").withMobilePhone("89651237160").withEmail("terehovandrej@gmail.com").withGroup("test1"));
            app.goTo().gotoHome();
        }
    }

    @Test
    public void testContactData(){
        app.goTo().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactDataFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactDataFromEditForm)));
        assertThat(contact.getAddress(), equalTo((contactDataFromEditForm.getAddress())));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals("")).map(ContactDataTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("-()", "");
    }
}
