package org.example.Controllers.Discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.Models.SingletonModel;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    static final String inputChannelID = "";            // Enter your channel id

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){

        if (event.getAuthor().isBot()) return;
        if (event.getChannel() != event.getGuild().getTextChannelById(inputChannelID)){
            return;
        }

        SingletonModel modelSingleton = SingletonModel.getInstance();
        Message msg = event.getMessage();
        String msgContent = msg.getContentRaw();
        modelSingleton.getList().add(msgContent);

    }
}

