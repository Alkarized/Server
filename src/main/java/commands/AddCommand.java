package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.util.Scanner;

/**
 * Класс команды add
 */
public class AddCommand extends Command {

    public AddCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("add {element} : добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        if(args.length == 1){
            if (receiver.addElement(scanner)){
                Messages.normalMessageOutput("Элемент успешно добавлен, УРЯЯ!!");
            } else {
                Messages.normalMessageOutput("Что-то пошло не так, либо вы написали end, так что не произошло добавления элемента");
            }
        } else {
            Messages.normalMessageOutput("Неправильно введенные аргументы, просьба написать так: area,name,numberOfRooms без пробелов. \n Все через запятую, так будет дальше использоваться");
        }
    }
}
