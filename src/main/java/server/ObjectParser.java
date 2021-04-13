package server;

import collection.Receiver;
import commands.serializable_commands.SerializableCommandStandard;
import commands.serializable_commands.SerializableCommandWithArgs;
import commands.serializable_commands.SerializableCommandWithObject;
import commands.serializable_commands.SerializableCommandWithObjectAndArgs;
import utils.SerializableAnswerToClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ObjectParser {
    public ByteBuffer parseObjectToByteBuffer(Object object, Receiver receiver){
        SerializableAnswerToClient serializableAnswerToClient = null;
        if (object instanceof SerializableCommandWithObjectAndArgs){
            SerializableCommandWithObjectAndArgs command = (SerializableCommandWithObjectAndArgs)object;
            serializableAnswerToClient = command.getCommand().execute(receiver, command.getFlat(), command.getArgs());
        } else if (object instanceof SerializableCommandWithObject) {
            SerializableCommandWithObject command = (SerializableCommandWithObject)object;
            serializableAnswerToClient = command.getCommand().execute(receiver, command.getFlat(), null);
        } else if (object instanceof SerializableCommandWithArgs){
            SerializableCommandWithArgs command = (SerializableCommandWithArgs)object;
            serializableAnswerToClient = command.getCommand().execute(receiver, null, command.getArgs());
        } else if (object instanceof SerializableCommandStandard){
            SerializableCommandStandard command = (SerializableCommandStandard)object;
            serializableAnswerToClient = command.getCommand().execute(receiver, null, null);
        }
        receiver.save();
        return parseAnswerToByteBuffer(serializableAnswerToClient);
    }

    private ByteBuffer parseAnswerToByteBuffer(SerializableAnswerToClient serializableAnswerToClient){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10000);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializableAnswerToClient);
            byteBuffer.put(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteBuffer;
    }
}
