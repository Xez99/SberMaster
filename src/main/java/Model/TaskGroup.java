package Model;

public class TaskGroup {
    private int id;
    private String name;
    private int squareMeters;
    private int priority;
    private int tasks;
    private int activeTasks;
    private float averageTime;
    private int maxTime;
    private int completed;

    TaskGroup(
            int id, String name,
            int squareMeters,
            int priority,
            int tasks,
            int activeTasks,
            float averageTime,
            int maxTime,
            int completed
    ){
        this.id = id;
        this.name = name;
        this.squareMeters = squareMeters;
        this.priority = priority;
        this.tasks = tasks;
        this.activeTasks = activeTasks;
        this.averageTime = averageTime;
        this.maxTime = maxTime;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TaskGroup {" +
                "id = " + id +
                ", name = \'" + name + '\'' +
                ", squareMeters = " + squareMeters +
                ", priority = " + priority +
                ", activeTasks = " + activeTasks +
                ", averageTime = " + averageTime +
                ", maxTime = " + maxTime +
                ", completed = " + completed +
                '}';
    }
}
