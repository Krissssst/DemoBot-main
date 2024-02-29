package com.example.SpringBot.service;

import com.example.SpringBot.config.Configuration;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingBot {
    private final Configuration configuration;

    public TelegramBot(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public String getBotUsername() {
        return configuration.getBootName();
    }

    @Override
    public String getBotToken() {
        return configuration.getBootToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId(); // Получаем индификатор пользователя
            String name = update.getMessage().getChat().getUserName();

            switch (messageText){
                case "/start" ->startCommand(chatId,name);
                default -> sendMessage(chatId,"Упс, что-то пошло не так( ");
            }
        }

    }

    private void startCommand(long chatId, String name){

        String message = "Салют, " + name + ", рад тебя видеть!";

        sendMessage(chatId,message);
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);

        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
