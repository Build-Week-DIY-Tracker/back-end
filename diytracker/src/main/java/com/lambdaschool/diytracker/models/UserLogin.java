package com.lambdaschool.diytracker.models;

import com.lambdaschool.diytracker.logging.Loggable;

//only here so that when I do the swagger docs I can provide a model to login
@Loggable
public class UserLogin
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
