package commands;

import fields.Flat;
import collection.Receiver;
import message.MessageColor;
import utils.SerializableAnswerToClient;

import java.io.IOException;
import java.io.Serializable;

public class AddCommand extends Command implements Serializable {
    private static final long serialVersionUID = 50;

    @Override
    public SerializableAnswerToClient execute(Receiver receiver, Flat flat, String[] args) {
        try {
            return receiver.addElement(flat);
        } catch (IOException | ClassNotFoundException e) {
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка соединения");
        }
    }
}