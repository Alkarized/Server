package commands;

import ru.itmo.collection.Receiver;
import ru.itmo.utils.Messages;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс команды save
 */
public class SaveCommand extends Command {

    public SaveCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            try {
                if(receiver.saveCollection()){
                    Messages.normalMessageOutput("Сохранение произошло, возрадуйся смертный");
                } else {
                    Messages.normalMessageOutput("В коллекции нет элементов, какой тут что-то сохранять, сначала добавь что-то");
                }
            } catch (FileNotFoundException e) {
                Messages.normalMessageOutput("Какие-то проблемы с сохранением данных в файл");
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
