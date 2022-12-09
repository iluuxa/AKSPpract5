package stu.ilexa;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class ChatController {

    DBConnection dbConnection = new DBConnection();
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ArrayList<OutputMessage> send(Message message) throws Exception {
        if (message.getText().equals("")){return dbConnection.selectAll();}
        String time = new SimpleDateFormat("HH:mm - dd.MM.yy").format(new Date());
        dbConnection.insert(new OutputMessage(0,time,message.getFrom(),message.getText()));
        return dbConnection.selectAll();
    }
}
