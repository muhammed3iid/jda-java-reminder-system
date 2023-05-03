package org.example.Controllers.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.Controllers.IController;
import org.example.Models.SingletonModel;

import javax.security.auth.login.LoginException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DiscordController implements IController {

    JDA discordBot;
    MessageChannel outputChannel;
    SingletonModel modelInstance;
    Random random;

    static final String botToken = "MTEwMjQwMjE2MjE3MDAwMzQ3Nw.G6IHdb.ykYRLN7nQd-gfQxsZJX0zqh4T7mWNJRFXOLsWU";
    static final String outputChannelID = "1103055666211471392";
    static final int delayTime = 60;

    public DiscordController() throws LoginException, InterruptedException {
        discordBot = JDABuilder.createDefault(botToken).enableIntents(GatewayIntent.GUILD_MESSAGES).build();
        discordBot.addEventListener(new EventListener());
        discordBot.awaitReady();
        outputChannel = discordBot.getTextChannelById(outputChannelID);
        modelInstance = SingletonModel.getInstance();
        random = new Random();
    }

    public void generate() throws InterruptedException {
        while (true){
            if(!modelInstance.getList().isEmpty()){
                sendMsg(getRandomMsgFromList());
            }
            startDelay(delayTime);
        }
    }

    public void sendMsg(String msg){
        outputChannel.sendMessage(msg).queue();
    }

    public String getRandomMsgFromList(){
        return modelInstance.getList().get(random.nextInt(modelInstance.getList().size()));
    }

    public void startDelay(int delayTime) throws InterruptedException {
        TimeUnit.SECONDS.sleep(delayTime);
    }

}
