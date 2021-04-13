package commands;

import collection.Receiver;
import fields.Flat;
import message.MessageColor;
import utils.SerializableAnswerToClient;

import java.io.IOException;
import java.io.Serializable;

public class ShowCommand extends Command implements Serializable {
    private static final long serialVersionUID = 62;

    @Override
    public SerializableAnswerToClient execute(Receiver receiver, Flat flat, String[] args) {
        try {
            return receiver.printAllElements();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка соединения");
        }
    }
}
