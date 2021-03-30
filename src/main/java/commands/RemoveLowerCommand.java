package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды remove_lower
 */
public class RemoveLowerCommand extends Command {

    public RemoveLowerCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        if (args.length != 1) {
            Messages.normalMessageOutput("Неправильные аргументы, попробуйте еще раз!");
        } else {
            receiver.removeLowerElements(scanner);
        }
    }
}
