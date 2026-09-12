package enums;

public enum TaskStatus {

    TODO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    public final String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
