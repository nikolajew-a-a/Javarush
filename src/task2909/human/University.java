package task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /*public University(String name, int age) {
        super(name, age, 0);
    }*/

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student stud=null;
        for (Student s:students) {
            if (s.getAverageGrade()==averageGrade){
                stud=s;
            }
        }
        return stud;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student stud = null;
        double maxAvaregeGrade=0;
        for (Student s:students) {
            if (s.getAverageGrade()>maxAvaregeGrade){
                maxAvaregeGrade=s.getAverageGrade();
                stud=s;
            }
        }
        return stud;
    }

    public Student getStudentWithMinAverageGrade(){
        Student stud = null;
        double minAverageGrade=100;
        for (Student s:students) {
            if(s.getAverageGrade()<minAverageGrade){
                minAverageGrade=s.getAverageGrade();
                stud=s;
            }
        }
        return stud;
    }

    public void expel(Student student){
        students.remove(student);
    }

}