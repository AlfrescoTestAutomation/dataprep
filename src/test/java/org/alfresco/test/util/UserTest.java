package org.alfresco.test.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest
{
    String userName = System.currentTimeMillis() + "@test.com";
    String password = "password";
    String email = userName;
    String shareUrl = "http://127.0.0.1:8080/share";

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createUserInvalidUserName() throws Exception
    {
        User.create(shareUrl, "admin", "admin", null, password, email);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createUserInvalidPassword() throws Exception
    {
        User.create(shareUrl, "admin", "admin", userName, null, email);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createUserInvalidShareUrl() throws Exception
    {
        User.create(null, "admin", "admin", userName, password, email);
    }

    @Test
    public void creatEnterpriseUser() throws Exception
    {
        boolean result = User.create(shareUrl, "admin", "admin", userName, password, email);
        Assert.assertTrue(result);
    }

    @Test
    public void createSameEnterpriseUser() throws Exception
    {
        String userName = "sameUser";
        String password = "password";

        User.create(shareUrl, "admin", "admin", userName, password, email);
        boolean result = User.create(shareUrl, "admin", "admin", userName, password, email);
        Assert.assertFalse(result);
    }
}