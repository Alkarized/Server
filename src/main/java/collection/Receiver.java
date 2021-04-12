package collection;

import collection.CollectionManager;
import commands.*;
import commands.serializable_commands.SerializableCommandStandard;
import commands.serializable_commands.SerializableCommandWithArgs;
import commands.serializable_commands.SerializableCommandWithObject;
import commands.serializable_commands.SerializableCommandWithObjectAndArgs;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import server.Connection;
import utils.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Receiver {
    private final Connection connection;
    private final CollectionManager collectionManager;

    public Receiver(Connection connection, CollectionManager collectionManager) {
        this.connection = connection;
        connection.setReceiver(this);
        this.collectionManager = collectionManager;
    }


    public SerializableAnswerToClient getInfoAboutAllCommands() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW,
               "help : вывести справку по доступным командам\n" +
                       "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                       "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                       "add {element} : добавить новый элемент в коллекцию\n" +
                       "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                       "remove_by_id id : удалить элемент из коллекции по его id\n" +
                       "clear : очистить коллекцию\n" +
                       "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                       "exit : завершить программу (без сохранения в файл)\n" +
                       "remove_first : удалить первый элемент из коллекции\n" +
                       "head : вывести первый элемент коллекции\n" +
                       "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                       "min_by_coordinates : вывести любой объект из коллекции, значение поля coordinates которого является минимальным\n" +
                       "count_less_than_number_of_rooms numberOfRooms : вывести количество элементов, значение поля numberOfRooms которых меньше заданного\n" +
                       "print_field_descending_number_of_rooms : вывести значения поля numberOfRooms всех элементов в порядке убывания"
        );
    }

    public SerializableAnswerToClient getInfoAboutCollection() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.printInfoAboutCollection());
    }

    public SerializableAnswerToClient clear() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.clearCollection());
    }

    public SerializableAnswerToClient removeFirst() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.removeFirstElement());
    }

    public SerializableAnswerToClient printFieldDescendingNumberOfRooms() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.printFieldDescendingNumberOfRooms());
    }

    public SerializableAnswerToClient getHead() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.getHeadOfCollection());
    }

    public SerializableAnswerToClient countLessNumberOfRooms(String[] args) throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.countLessNumberOfRooms(Integer.parseInt(args[1])));
    }

    public SerializableAnswerToClient printElementWithMinCoordinates() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.findElementWithMinCoordinates());
    }

    public SerializableAnswerToClient removeById(String[] args) throws IOException, ClassNotFoundException {
        return collectionManager.removeElementById(Long.parseLong(args[1]));
    }

    public SerializableAnswerToClient printAllElements() throws IOException, ClassNotFoundException {
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, collectionManager.printAllElements());
    }

    public SerializableAnswerToClient addElement(Flat flat) throws IOException, ClassNotFoundException {
        return collectionManager.addElement(flat);
    }

    public SerializableAnswerToClient updateElementById(Flat flat, String[] args) throws IOException, ClassNotFoundException {
        return collectionManager.updateElement((Long.parseLong(args[1])), flat);
    }

    public SerializableAnswerToClient removeLowerElements(Flat flat) throws IOException, ClassNotFoundException {
        return collectionManager.removeLower(flat);
    }
}
