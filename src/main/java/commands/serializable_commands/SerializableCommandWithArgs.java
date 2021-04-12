package commands.serializable_commands;

import commands.Command;

public class SerializableCommandWithArgs extends SerializableCommandStandard {
    private static final long serialVersionUID = 102;

    private final String[] args;

    public SerializableCommandWithArgs(Command command, String[] args) {
        super(command);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
