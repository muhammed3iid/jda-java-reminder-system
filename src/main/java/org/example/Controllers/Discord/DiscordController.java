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
import io.github.cdimascio.dotenv.Dotenv;

public class DiscordController implements IController {

    JDA discordBot;
    MessageChannel outputChannel;
    SingletonModel modelInstance;
    Random random;
    static final Dotenv dotenv = Dotenv.load();
    static final String botToken = dotenv.get("BOT_TOKEN");
    static final String outputChannelID = dotenv.get("OUTPUT_CHANNEL_ID");
    static final int delayTime = Integer.parseInt(dotenv.get("DELAY_TIME"));

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
