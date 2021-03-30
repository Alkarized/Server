package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды show
 */
public class ShowCommand extends Command {

    public ShowCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(!receiver.printAllElements()){
                Messages.normalMessageOutput("В коллекции нет элементов, вывод недоступен");
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
