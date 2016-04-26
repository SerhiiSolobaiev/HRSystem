package my.entity;

public enum Status {

    ON_INTERVIEW(1, "STATUS_INTERVIEW"),
    ON_COURSES(2, "STATUS_ON_COURSES"),
    ON_WORK(3, "STATUS_ON_WORK"),
    INTERVIEW_REJECTION(4, "STATUS_INTERVIEW_REJECTION"),
    COURSES_REJECTION(5, "STATUS_COURSES_REJECTION");

    int id;
    String status;

    Status(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return status;
    }

}
