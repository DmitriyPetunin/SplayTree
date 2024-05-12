import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Tree<Integer> splayTree;
    public static List<String> list = new ArrayList<>();
    public static List<Long> listTimeOfInsert = new ArrayList<>();
    public static List<Long> listTimeOfDelete = new ArrayList<>();
    public static List<Long> listTimeOfSearch = new ArrayList<>();
    public static Integer AverageTimeInsert;
    public static Integer AverageTimeSearch;
    public static Integer AverageTimeDelete;
    public static void main(String[] args) throws IOException {
        readFromFile();
        insertAllElements();
        searchHundredElements();
        deleteThousandElements();
        searchAverageTimeInsert();
        System.out.println("Среднее значение добавления элемента " + AverageTimeInsert + " наносекунд");
        searchAverageTimeSearch();
        System.out.println("Среднее значение поиска элемента " + AverageTimeSearch + " наносекунд");
        searchAverageTimeDelete();
        System.out.println("Среднее значение удаления элемента " + AverageTimeDelete+ " наносекунд");
    }

    public static int[] createArray(){
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++){
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }
    public static void writeToFile() throws IOException {
        int[] dataArray = createArray();
        FileWriter writer = new FileWriter("example.txt",false);
        for (int i = 0; i < dataArray.length; i++){
            writer.write(String.valueOf(dataArray[i]));
            writer.write("\n");
        }
        writer.close();
    }
    public static void readFromFile() throws IOException {
        Path path = Path.of("example.txt");
        list = Files.readAllLines(path);
    }
    public static void insertAllElements(){
        splayTree = new SplayTree<>();
        for (String s : list) {
            long startTime = System.nanoTime();
            splayTree.insert(Integer.parseInt(s));
            long endTime = System.nanoTime();
            listTimeOfInsert.add(endTime-startTime);
        }
    }
    public static void deleteThousandElements(){
        Random random = new Random();
        for (int i = 0; i < 1000; i++){
            int randomIndex = random.nextInt(list.size());
            long startTime = System.nanoTime();
            splayTree.delete(Integer.parseInt(list.get(randomIndex)));
            long endTime = System.nanoTime();
            listTimeOfDelete.add(endTime-startTime);
        }
    }
    public static void searchHundredElements(){
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            int randomValue = random.nextInt(10000);
            long startTime = System.nanoTime();
            splayTree.search(Integer.parseInt(list.get(randomValue)));
            long endTime = System.nanoTime();
            listTimeOfSearch.add(endTime-startTime);
        }
    }
    public static void searchAverageTimeInsert(){
        int localSum = 0;
        for (Long aLong : listTimeOfInsert) {
            localSum += aLong;
        }
        AverageTimeInsert = localSum / listTimeOfInsert.size();
    }
    public static void searchAverageTimeSearch(){
        int localSum = 0;
        for (Long aLong : listTimeOfSearch) {
            localSum += aLong;
        }
        AverageTimeSearch = localSum / listTimeOfSearch.size();
    }
    public static void searchAverageTimeDelete(){
        int localSum = 0;
        for (Long aLong : listTimeOfDelete) {
            localSum += aLong;
        }
        AverageTimeDelete = localSum / listTimeOfDelete.size();
    }
}