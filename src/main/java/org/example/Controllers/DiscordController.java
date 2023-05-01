package org.example.Controllers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.example.Model;

import javax.security.auth.login.LoginException;
import java.util.Random;

public class DiscordController implements IController{

    JDA discordBot;
    MessageChannel channel;
    String botToken;
    String channelID;
    Model model;
    Random random;
    String msg;

    public DiscordController() throws LoginException, InterruptedException {
        botToken = "MTEwMjQwMjE2MjE3MDAwMzQ3Nw.G6IHdb.ykYRLN7nQd-gfQxsZJX0zqh4T7mWNJRFXOLsWU";
        channelID = "1102430983615160422";
        discordBot = JDABuilder.createDefault(botToken).build();
        discordBot.awaitReady();
        channel = discordBot.getTextChannelById(channelID);
        model = new Model();
        random = new Random();
    }

    public void generate(){
        msg  = getRandomElementFromList();
        sendMsg(msg);
    }

    public void sendMsg(String msg){
        channel.sendMessage(msg).queue();
    }

    public String getRandomElementFromList(){
        return model.getList().get(random.nextInt(model.getList().size()-1));
    }

}
