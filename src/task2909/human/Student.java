package task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;
    private int course;



    public int getCourse() {
        return course;
    }
    public Student(String name, int age, double averageGrade) {
        super(name,age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    public void incAverageGrade(double delta){
        setAverageGrade(getAverageGrade() + delta);
    }

    public void setCourse(int value) {
        this.course=value;
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        endOfSession = date;
    }

    public void setAverageGrade(double value){
        this.averageGrade=value;
    }
    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }
}