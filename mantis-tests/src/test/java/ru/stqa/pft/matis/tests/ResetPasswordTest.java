package ru.stqa.pft.matis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.model.MailMessage;
import ru.stqa.pft.model.UsersData;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase{
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testReset() throws IOException, MessagingException, ServiceException {
        skipIfNotFixed(1);
        UsersData user = app.db().users().iterator().next();
        String email = user.getEmail();
        String username = user.getUserName();
        String password = "password2";
        app.reset().signIn("administrator", "password");
        app.reset().goToUserManage();
        app.reset().selectUser(username);
        app.reset().clickReset();
        List<MailMessage> mailMessage = app.mail().waitForMail(1, 10000);
        findConfirmationLink(mailMessage, email);
        String confirmLink = findConfirmationLink(mailMessage, email);
        app.registration().finish(confirmLink, password);
        HttpSession session = app.newSession();
        assertTrue(session.login(username, password));
        assertTrue(session.isLoggedInAs(username));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
