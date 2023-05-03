package org.example;

import org.example.Controllers.Discord.DiscordController;
import org.example.Controllers.IController;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException, InterruptedException {
        IController controller = new DiscordController();
        controller.generate();
    }
}