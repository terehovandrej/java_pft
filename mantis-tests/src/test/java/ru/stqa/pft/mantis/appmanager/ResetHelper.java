package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class ResetHelper extends HelperBase {

    public ResetHelper(ApplicationManager app){
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void signIn(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void goToUserManage() {
        click(By.xpath("(//div[@id='sidebar']//li)[6]"));
        click(By.xpath("(//div[@class='row']//li)[2]"));

    }
    public void selectUser(String username) {
        click(By.xpath(String.format("//a[text()='%s']", username)));
    }

    public void clickReset() {
        click(By.xpath("(//input[@type='submit'])[2]"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
