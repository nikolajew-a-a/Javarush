package task2909.human;

public class UniversityPerson extends Human{
    private University university;

    public UniversityPerson(String name, int age) {
        super(name, age);
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }
}
