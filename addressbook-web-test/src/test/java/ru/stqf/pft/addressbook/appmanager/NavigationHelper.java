package ru.stqf.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
      click(By.linkText("groups"));
    }
    public void gotoHome() {
      click(By.linkText("home"));
    }

    public void gotoContactCreation() {
      click(By.linkText("add new"));
    }
}
