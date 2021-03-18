package ru.stqf.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqf.pft.addressbook.model.GroupData;
import ru.stqf.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }


    @Test
    public void testGroupDeletion()throws Exception {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));

    }
}
