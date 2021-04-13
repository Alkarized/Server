package collection;

import collection.comparators.CoordinatesComparator;
import collection.comparators.NameComparator;
import collection.comparators.NumberOfRoomsComparator;
import fields.Flat;
import message.*;
import utils.SerializableAnswerToClient;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CollectionManager {
    private final File file;
    private final PriorityQueue<Flat> collection;
    private final Date date;

    public CollectionManager(String fileName) {
        this.file = new File(fileName);
        collection = new PriorityQueue<>(new NameComparator());
        date = new Date();
    }

    public PriorityQueue<Flat> getCollection() {
        return collection;
    }

    public String printInfoAboutCollection() {
        return ("Тип коллекции - " + getCollection().getClass().getName() + "\n" +
                "Дата иницализации - " + getDate() + "\n" +
                "Кол-во элементов - " + getCollection().size());

    }

    public File getFile() {
        return file;
    }

    public Date getDate() {
        return date;
    }

    public String clearCollection() {
        if (getCollection().size() > 0) {
            IdManager.clearSet();
            getCollection().clear();
            return "Коллекция успешно очищена! ";
        } else {
            return "Коллекция пуста! ";
        }
    }

    public PriorityQueue<Flat> sortCollectionByComp(Comparator<Flat> comp) {
        List<Flat> newList = new ArrayList<>(collection);
        newList.sort(comp);
        PriorityQueue<Flat> newCollection = new PriorityQueue<>(comp);
        newCollection.addAll(newList);
        return newCollection;
    }

    public String getHeadOfCollection() {
        if (getCollection().size() > 0) {
            return Objects.requireNonNull(getCollection().peek()).printInfoAboutElement();
        } else {
            return "Коллекция пуста! ";
        }
    }


    public String countLessNumberOfRooms(int number) {
        if (getCollection().size() > 0) {
            int count = 0;
            PriorityQueue<Flat> newQueue = new PriorityQueue<>(getCollection());
            while (newQueue.size() > 0) {
                if (newQueue.poll().getNumberOfRooms() < number) {
                    count++;
                }
            }
            return (new Integer(count)).toString();
        } else {
            return "-1";
        }
    }


    public String findElementWithMinCoordinates() {
        if (getCollection().size() > 0) {
            PriorityQueue<Flat> queue = sortCollectionByComp(new CoordinatesComparator());
            return Objects.requireNonNull(queue.peek()).printInfoAboutElement();
        } else {
            return "Коллекция пуста! ";
        }
    }


    public String printFieldDescendingNumberOfRooms() { //fixme
        if (getCollection().size() > 0) {
            PriorityQueue<Flat> queue = sortCollectionByComp(new NumberOfRoomsComparator());
            StringBuilder stringBuilder = new StringBuilder();
            queue.forEach((flat -> stringBuilder.append(flat.getNumberOfRooms())));
            return ("Значения поля numberOfRooms всех элементов в порядке убывания:\n " + stringBuilder.toString() + " ");
        } else {
            return "Коллекция пуста! ";
        }
    }

    public SerializableAnswerToClient removeElementById(Long id) { //todo
        if (getCollection().size() > 0) {
            if (!IdManager.checkUniq(id)) {
                getCollection().removeIf(flat -> flat.getId().equals(id));
                IdManager.removeUsedId(id);
                return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, "Элемент успешно удален! ");
            } else {
                return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Элемент с данным ID отсутствует в коллекции! ");
            }
        } else {
            return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, "Коллекция пуста! ");
        }
    }

    public String removeFirstElement() {
        if (getCollection().size() > 0) {
            IdManager.removeUsedId(Objects.requireNonNull(getCollection().poll()).getId());
            return "Первый элемент успешно удален! ";
        } else {
            return "Коллекция пуста! ";
        }
    }

    public String printAllElements() { //fixme
        if (getCollection().size() > 0) {
            //getCollection().forEach((Flat::printInfoAboutElement));
            StringBuilder stringBuilder = new StringBuilder();
            getCollection().forEach((flat -> stringBuilder.append(flat.printInfoAboutElement())));
            return stringBuilder.toString();
        } else {
            return "Коллекция пуста! ";
        }
    }


    public SerializableAnswerToClient addElement(Flat flat) {
        if (flat != null) {
            flat.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
            getCollection().add(flat);
            return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, "Элемент успешно добавлен в коллекцию! ");
        } else {
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка при добавлении элемента! Попробуйте еще раз. ");
        }
    }

    public SerializableAnswerToClient updateElement(Long id, Flat flat) {
        if (!IdManager.checkUniq(id) && getCollection().size() > 0 && (flat != null)) {
            PriorityQueue<Flat> queue = new PriorityQueue<>(new NameComparator());
            while (!getCollection().peek().getId().equals(id)) {
                queue.add(getCollection().poll());
            }
            try {
                flat.setCreationDate(new SimpleDateFormat("HH:mm:ss.SSS dd-MM-yyyy").parse(getCollection().poll().getCreationDate()));
            } catch (ParseException e) {
                e.printStackTrace();
                //Messages.normalMessageOutput("Какая-то проблема с датой ?");
                return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Какая-то проблема с датой");
            }

            flat.setId(id);
            getCollection().add(flat);
            while (queue.size() > 0) {
                getCollection().add(queue.poll());
            }
        } else {
            return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Ошибка!!! Возможные причины: \n\t" +
                    "> элемент с данным ID отсутствует в коллекции\n\t" +
                    "> коллекция пуста\n Попробуйте еще раз.");
        }
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, "Элемент с заданным ID успешно обновлен! ");
    }

    public SerializableAnswerToClient removeLower(Flat flat) {
        if (getCollection().size() > 0) {
            if (flat != null) {
                try {
                    while (true) {
                        if (getCollection().size() > 0 && getCollection().peek().compareTo(flat) < 0) {
                            IdManager.removeUsedId(getCollection().poll().getId());
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Что-то пошло не так... " + e.toString());
                }
            } else {
                //Messages.normalMessageOutput("Не удалось получить элемент для сравнения.");
                return new SerializableAnswerToClient(MessageColor.ANSI_RED, "Не удалось получить элемент для сравнения. ");
            }
        } else {
            //Messages.normalMessageOutput("В коллекции нет элементов, нечего удалять");
            return new SerializableAnswerToClient(MessageColor.ANSI_PURPLE, "В коллекции нет элементов, нечего удалять ");
        }
        //Messages.normalMessageOutput("Все элементы, меньше данного - удалены!");
        return new SerializableAnswerToClient(MessageColor.ANSI_YELLOW, "Все элементы, меньше данного - удалены!");
    }

}
