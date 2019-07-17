package Model;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Functional Interface объекта, который можно парсить построчно
 * @author xez
 */
@FunctionalInterface
interface Parsable{
    void parsePerLine(String line, String[] separatedLine, String separator) throws ParseException;
}

/**
 * Получение исходных данных задачи
 * @author xez
 */
public class InitData {
    private static ArrayList<TaskGroup> taskPool = new ArrayList<>();
    private static HashMap<Integer, IdRoleTime> initData = new HashMap<>();
    private static File csvFile;

    /**
     * Парсинг файла через <b>BufferedReader</b>
     * @param csvFile – Файл CSV
     * @param separator – Разделитель, используемый в CSV
     * @param parsable - Объект имплементящий <b>Parsable</b> или лямбда: описывает действие над каждой строкой CSV
     */
    private static void parsing(File csvFile, String separator, Parsable parsable){
        String line;
        try(BufferedReader bf = new BufferedReader(new FileReader(csvFile))){
            bf.readLine();
            while ((line = bf.readLine()) != null){
                parsable.parsePerLine(line, null, separator);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, описывающий действие над каждой строкой CSV файла
     * @param csvFile – Файл CSV
     */
    public static void parseCsv(File csvFile) {
        parsing(csvFile, ";", (line , cells, separator) -> {
            line = line.replace("\"", "");
            cells = line.split(separator);
            TaskGroup taskGroup = new TaskGroup(
                    cells[0].equals("-") ? -99999 : Integer.parseInt(cells[0]),             // ID
                    cells[1],                                                               // Задача
                    cells[2].equals("-") ? 0 : Integer.parseInt(cells[2]),                  // Кв.м
                    cells[3].equals("-") || cells[3].equals("-10 K") ?
                            Integer.MIN_VALUE : Integer.parseInt(cells[3]),                 // Приоритет
                    cells[4].equals("-") ? 0 : Integer.parseInt(cells[4]),                  // Всего задач
                    cells[5].equals("-") ? 0 : Integer.parseInt(cells[5]),                  // Активных задач
                    cells[6].equals("-") ? -0f : Float.parseFloat(cells[6]),                // Среднее время
                    cells[7].equals("-") ? 0 : Integer.parseInt(cells[7]),                  // Максимальное время
                    cells[8].equals("-") ? 0 : Integer.parseInt(cells[8])                   // Выполненно
            );
            taskPool.add(taskGroup);
        });
    }

    /**
     * Получение начальных условий: ID, Среднее время, Критическое время, Роли
     */
    public static void getInitData(){
        final File initFile = new File("/Users/xez/Documents/initData.csv"); /* ЗАМЕНИТЬ ПУТЬ К ФАЙЛУ !!!! */
        parsing(initFile, ";", (line, cells, separator) ->{
            cells = line.split(separator);
            cells[1] = cells[0].replace(',', '.');
            cells[3] = cells[3].replace(',', '.');
            Role role = Role.parseString(cells[2]);

            if(!initData.containsKey(Integer.parseInt(cells[0]))){
                initData.put(Integer.parseInt(cells[0]), new IdRoleTime(
                                                            Integer.parseInt(cells[0]),     // ID
                                                            Float.parseFloat(cells[1]),     // Среднее время
                                                            null,                     // Роль
                                                            Float.parseFloat(cells[3])      // Критическое время
                                                        )
                );
            }
            initData.get(Integer.parseInt(cells[0])).addRole(role);

        });
    }

    /**
     * Вывод всех <b>TaskGroup</b> в консоль
     */
    public static void printCsv() {
        taskPool.forEach(System.out::println);
    }

    /**
     * Вывод начальных условий в консоль
     */
    public static void printInit() {
        initData.forEach((k, v) -> System.out.println(v));
    }

}

/**
 * Начальное условие
 */
class IdRoleTime{

    private int id;
    private float averageTime;
    private ArrayList<Role> roles;
    private float criticalTime;

    IdRoleTime(int id, float averageTime, ArrayList<Role> roles, float criticalTime){
        this.id = id;
        this.averageTime = averageTime;
        this.roles = roles;
        if(this.roles == null)
            this.roles = new ArrayList<>();
        this.criticalTime = criticalTime;
    }

    void addRole(Role role){
        roles.add(role);
    }

    @Override
    public String toString() {
        return "IdRoleTime{" +
                "id=" + id +
                ", averageTime=" + averageTime +
                ", roles=" + roles.size() +
                ", criticalTime=" + criticalTime +
                '}';
    }
}

