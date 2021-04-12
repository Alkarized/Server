package commands;

import collection.Receiver;
import fields.Flat;
import message.MessageColor;
import utils.SerializableAnswerToClient;

import java.io.IOException;
import java.io.Serializable;

public class CountLessCommand extends Command implements Serializable {
    private static final long serialVersionUID = 52;

    @Override
    public SerializableAnswerToClient execute(Receiver receiver, Flat flat, String[] args) {
        try {
            return receiver.countLessNumberOfRooms(args);
        } catch (IOException | ClassNotFoundException e) {
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка соединения");
        }
    }
}
