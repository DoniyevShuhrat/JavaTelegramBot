package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import spark.Spark;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//psvm
public class Main {
    private static final int PORT = 8080;
    public static void main(String[] args) throws TelegramApiException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        Spark.port(PORT);

        Spark.get("/", (req, res) -> {
            res.redirect("/form");
            return null;
        });

        Spark.get("/form", (req, res) -> {
            StringBuilder html = new StringBuilder();
            html.append("<html>");
            html.append("<head><title>Telegram Bot UI</title></head>");
            html.append("<body>");
            html.append("<form action=\"/submit\" method=\"post\">");
            html.append("<label for=\"name\">Name:</label>");
            html.append("<input type=\"text\" name=\"name\" id=\"name\"><br>");
            html.append("<label for=\"email\">Email:</label>");
            html.append("<input type=\"email\" name=\"email\" id=\"email\"><br>");
            html.append("<label for=\"message\">Message:</label>");
            html.append("<textarea name=\"message\" id=\"message\"></textarea><br>");
            html.append("<input type=\"submit\" value=\"Submit\">");
            html.append("</form>");
            html.append("</body>");
            html.append("</html>");
            return html.toString();
        });

        Spark.post("/submit", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String message = req.queryParams("message");

            // Process the form data and send it to the Telegram bot
            //sendToTelegram(name, email, message);

            return "Form submitted successfully!";
        });
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyBot());

    }
}