package utils.csv;

import message.MessageColor;
import message.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для чтения и работы с CSV файлом
 */
public class CSVFileReader {

    /**
     * Метод - для реализции правильного названия файла
     *
     * @param args - аргументы командной строки
     * @return правильное название файла
     */
    public String getFileNameFromArgs(String[] args) {
        String fileName = null;
        if (args.length == 1) {
            fileName = args[0] + setFormat(checkFormat(args[0]));
            checkAllPermissions(new File(fileName));
            Messages.normalMessageOutput("Полученный файл - " + fileName, MessageColor.ANSI_GREEN);
        } else {
            Messages.normalMessageOutput("Неправильный ввод, что-то не так с аргументами при запуске программы, " +
                    "попробуйте еще раз", MessageColor.ANSI_RED);
            System.exit(1);
        }
        return fileName;
    }

    private boolean checkFormat(String arg) {
        StringBuilder lastFourWords = new StringBuilder();
        if (arg.length() >= 4) {
            for (int i = 4; i > 0; i--) {
                lastFourWords.append(arg.charAt(arg.length() - i));
            }
        }

        return lastFourWords.toString().toLowerCase().equals(".csv");
    }

    private String setFormat(boolean isNotNeedToSet) {
        if (isNotNeedToSet) {
            return "";
        } else {
            return ".csv";
        }
    }

    private void checkAllPermissions(File file) {
        if (file.exists()) {
            if ((!file.canWrite()) && (!file.canRead())) {
                Messages.normalMessageOutput("Файл нельзя считать и что-то в него записать, давай исправляй, сударь!", MessageColor.ANSI_RED);
                System.exit(1);
            } else if ((!file.canWrite()) && file.canRead()) {
                Messages.normalMessageOutput("Уууупс, а в файлик-то ничего записать нельзя, а смысл в чем?" +
                        " Зайкрой и открой нормально", MessageColor.ANSI_RED);
                System.exit(1);
            } else if (file.canWrite() && (!file.canRead())) {
                Messages.normalMessageOutput("Ну что-то явно пошло не так, дай права на чтение пощупать.", MessageColor.ANSI_RED);
                System.exit(1);
            }
        }
    }

    /**
     * Считывает все строки в CSV файле
     *
     * @param file - файл, где читать строки
     * @return Массив строк
     */
    public ArrayList<String> readAllLines(File file) {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            StringBuilder line = new StringBuilder("");
            while (true) {
                int data = reader.read();
                if (data == -1) {
                    break;
                } else {
                    line.append((char) data);
                }
            }
            String[] lines = String.valueOf(line).split(System.getProperty("line.separator"));
            return new ArrayList<>(Arrays.asList(lines));
        } catch (FileNotFoundException e) {
            Messages.normalMessageOutput("Файл не был найден, возможно он еще не существует, не беда", MessageColor.ANSI_RED);
            return null;
        } catch (IOException e) {
            Messages.normalMessageOutput("Какая-то ошибка с IO, выключаемся", MessageColor.ANSI_RED);
            System.exit(1);
            return null;
        }
    }
}
