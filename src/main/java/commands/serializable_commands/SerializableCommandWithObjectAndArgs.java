package commands.serializable_commands;

import commands.Command;
import fields.Flat;

public class SerializableCommandWithObjectAndArgs extends SerializableCommandWithObject {
    private static final long serialVersionUID = 103;
    private String[] args;

    public SerializableCommandWithObjectAndArgs(Command command, Flat flat, String[] args) {
        super (command, flat);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
