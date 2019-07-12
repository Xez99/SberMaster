package Model;


import java.io.*;
import java.util.ArrayList;

public class InitData {
    private static ArrayList<TaskGroup> taskPool;
    private static File csvFile;

    public static void parseCsv(File file) {
        csvFile = file;
        taskPool = new ArrayList<>();
        String line;
        String cells[];
        try ( BufferedReader bk = new BufferedReader(new FileReader(csvFile))) {
            bk.readLine(); // If first string is trash, don't touch
            while ((line = bk.readLine()) != null) {
                line = line.replace("\"", "");
                /*cells = new String[]{"", "", "", "", "", "", "", "", ""};

                int index = 0;
                for (String i : line.split(";")){
                    cells[index++] = i;
                }*/
                cells = line.split(";");
                //System.out.println(line);
                //System.out.println(Arrays.toString(cells));
                TaskGroup taskGroup = new TaskGroup(
                        cells[0].equals("-") ? -99999 : Integer.parseInt(cells[0]),              // ID
                        cells[1],                                                               // Задача
                        cells[2].equals("-") ? 0: Integer.parseInt(cells[2]),                   // Кв.м
                        cells[3].equals("-") || cells[3].equals("-10 K") ? Integer.MAX_VALUE : Integer.parseInt(cells[3]),   // Приоритет
                        cells[4].equals("-") ? 0 : Integer.parseInt(cells[4]),                   // Всего задач
                        cells[5].equals("-") ? 0 : Integer.parseInt(cells[5]),                   // Активных задач
                        cells[6].equals("-") ? -0f :Float.parseFloat(cells[6]),                   // Среднее время
                        cells[7].equals("-") ? 0 : Integer.parseInt(cells[7]),                   // Максимальное время
                        cells[8].equals("-") ? 0 : Integer.parseInt(cells[8])                    // Выполненно
                );

                taskPool.add(taskGroup);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void printCsv () {
        for (TaskGroup i : taskPool) {
            System.out.println(i);
        }
    }
/*
    private static void getInitData(){
        csvFile = new File("/Users/xez/Documents/initData.csv");
        String line;
        String cells[];
        try ( BufferedReader bk = new BufferedReader(new FileReader(csvFile))) {
            bk.readLine(); // If first string is trash, don't touch
            while ((line = bk.readLine()) != null) {
                line = line.replace("\"", "");
                cells = new String[]{"", "", "", "", "", "", "", "", ""};

                int index = 0;
                for (String i : line.split(";")){
                    cells[index++] = i;
                }
                cells = line.split(";");
                //System.out.println(line);
                //System.out.println(Arrays.toString(cells));
                TaskGroup taskGroup = new TaskGroup(
                        cells[0].equals("-") ? -99999 : Integer.parseInt(cells[0]),              // ID
                        cells[1],                                                               // Задача
                        cells[2].equals("-") ? 0: Integer.parseInt(cells[2]),                   // Кв.м
                        cells[3].equals("-") || cells[3].equals("-10 K") ? Integer.MAX_VALUE : Integer.parseInt(cells[3]),   // Приоритет
                        cells[4].equals("-") ? 0 : Integer.parseInt(cells[4]),                   // Всего задач
                        cells[5].equals("-") ? 0 : Integer.parseInt(cells[5]),                   // Активных задач
                        cells[6].equals("-") ? -0f :Float.parseFloat(cells[6]),                   // Среднее время
                        cells[7].equals("-") ? 0 : Integer.parseInt(cells[7]),                   // Максимальное время
                        cells[8].equals("-") ? 0 : Integer.parseInt(cells[8])                    // Выполненно
                );

                taskPool.add(taskGroup);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

*/

}
