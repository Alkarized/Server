package commands;

import collection.Receiver;
import fields.Flat;
import message.MessageColor;
import utils.SerializableAnswerToClient;

import java.io.IOException;
import java.io.Serializable;

public class RemoveLowerCommand extends Command implements Serializable {
    private static final long serialVersionUID = 61;

    @Override
    public SerializableAnswerToClient execute(Receiver receiver, Flat flat, String[] args) {
        try {
            return receiver.removeLowerElements(flat);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка соединения");
        }
    }

}
