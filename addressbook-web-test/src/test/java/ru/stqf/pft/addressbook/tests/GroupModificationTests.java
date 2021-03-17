package ru.stqf.pft.addressbook.tests;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqf.pft.addressbook.model.GroupData;
import ru.stqf.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
            Groups before = app.db().groups();
            GroupData modifiedGroup = before.iterator().next();
            GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test5").withHeader("test2").withFooter("test3");
            app.goTo().groupPage();
            app.group().modify(group);
            Groups after = app.db().groups();
            assertThat(after.size(), equalTo(before.size()));
            assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        }
    }