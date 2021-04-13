package utils;

import collection.CollectionManager;
import collection.Receiver;
import fields.Flat;
import server.Connection;

import java.util.ArrayList;

public class ProgramStarter {
    private final Receiver receiver;
    private final Connection connection;
    private final CollectionManager collectionManager;

    public ProgramStarter(String host, int port, String[] args){
        connection = new Connection(1707);
        collectionManager = new CollectionManager(new CSVFileReader().getFileNameFromArgs(args));
        receiver = new Receiver(connection, collectionManager);
    }

    public void startProgram(){
        addAllFlatsFromCSV();
        connection.start();
    }

    public void addAllFlatsFromCSV() {
        CSVParser csvParser = new CSVParser();
        CSVFileReader csvFileReader = new CSVFileReader();
        ArrayList<String> listOfLines;
        if ((listOfLines = csvFileReader.readAllLines(collectionManager.getFile())) != null) {
            for (String listOfLine : listOfLines) {
                Flat flat;
                String[] args = csvParser.parseLineToArray(listOfLine);
                if (args != null) {
                    if ((flat = csvParser.parseArrayToFlat(args)) != null) {
                        collectionManager.getCollection().add(flat);
                    } else {
                        //Messages.normalMessageOutput("Программа не смогла считать " + i + " строку, ошибка в формате самих полей, пропускам ее.");
                    }
                } else {
                    //Messages.normalMessageOutput("Ошибка в строке " + i + ", неправильно составлена CSV таблица, что-то не так с кавычками, пропуск строки");
                }
            }
            //Messages.normalMessageOutput("Запись данных из файла закончилась");
        }

    }
}
