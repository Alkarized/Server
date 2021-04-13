package commands.serializable_commands;

import commands.Command;

import java.io.Serializable;

public class SerializableCommandStandard implements Serializable {
    private static final long serialVersionUID = 100;

    public SerializableCommandStandard() {

    }

    private Command command;

    public SerializableCommandStandard(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
