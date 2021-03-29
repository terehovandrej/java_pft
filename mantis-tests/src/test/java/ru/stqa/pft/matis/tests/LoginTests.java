package ru.stqa.pft.matis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException, ServiceException {
    skipIfNotFixed(1);
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "password"));
    assertTrue(session.isLoggedInAs("administrator"));
  }
}
