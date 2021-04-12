package commands;

import collection.Receiver;
import fields.Flat;
import utils.SerializableAnswerToClient;

import java.io.Serializable;

public abstract class Command implements Serializable{
    private static final long serialVersionUID = 12311;
    protected transient Receiver receiver;

    public Command() {

    }

    public abstract SerializableAnswerToClient execute(Receiver receiver, Flat flat, String[] args);



}
