package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды remove_first
 */
public class RemoveFirstCommand extends Command {

    public RemoveFirstCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("remove_first : удалить первый элемент из коллекции");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (receiver.removeFirst()){
                Messages.normalMessageOutput("Первый элемент в коллекции был успешно удален");
            } else {
                Messages.normalMessageOutput("В коллекции нет элементов, нечего удалять");
            }
        } else {
            Messages.normalMessageOutput("Непавильны ввод агрументов, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
