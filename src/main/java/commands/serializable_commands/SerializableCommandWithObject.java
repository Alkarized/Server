package commands.serializable_commands;

import commands.Command;
import fields.Flat;

public class SerializableCommandWithObject extends SerializableCommandStandard {
    private static final long serialVersionUID = 101;

    private Flat flat;

    public SerializableCommandWithObject(Command command, Flat flat) {
        super (command);
        this.flat = flat;
    }

    public Flat getFlat() {
        return flat;
    }
}
