package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    String bot_Token = "1808591345:AAF7vqaGtNuotGbfHsfiOxyYr2QteAJE7fc";
    String userName = "testvs_bot";

    @Override
    public void onUpdateReceived(Update update) {
        String chat_id = update.getMessage().getChatId().toString();
        System.out.println("charId: " + chat_id);
        SendMessage smg = new SendMessage();
        smg.setChatId(update.getMessage().getChatId().toString());
        if (update.hasMessage()) {
            smg.setText("Hello dunyo");
            try {
                execute(smg);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello world in update.hashMessage()");
            Message message = update.getMessage();
            String text = message.getText();
            System.out.println("text: " + text);
            if (text != null && text.startsWith("http")) {
                try {
                    execute(smg);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                try {
                    smg.setText("Iyy");
                    Desktop.getDesktop().browse(new URI(text));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(update.getMessage().getText());
            sendWebpageLink(update.getMessage().getChatId().toString());
        } else if (update.hasCallbackQuery()) {
            System.out.println("Hello world in update.hasCallbackQuery()");
            //update.getCallbackQuery(); click buttons
        }
    }

    private void sendWebpageLink(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Hello dunyo");

        InlineKeyboardButton urlButton = new InlineKeyboardButton();
        urlButton.setText("Open Form");
        urlButton.setUrl("https://t.me/hardsoftware_bot/TestHardSoftWare");

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(urlButton);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(markup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotToken() {
        return bot_Token;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }
}