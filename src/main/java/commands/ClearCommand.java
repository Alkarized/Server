package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды clear
 */
public class ClearCommand extends Command {

    public ClearCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("clear : очистить коллекцию");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(receiver.clear()){
                Messages.normalMessageOutput("Очистка коллекции прошла успешно!");
            } else {
                Messages.normalMessageOutput("Ну чистить нечего, так что я почистил пустоту");
            }
        } else {
            Messages.normalMessageOutput("Неправильный ввод аргументов, давай по новой, все фигня");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}