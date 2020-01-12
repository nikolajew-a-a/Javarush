package task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private List<Human> children = new ArrayList<>();
    private int id;
    protected int age;
    protected String name;
    public Size size;
    private BloodGroup bloodGroup;
    //protected boolean isSoldier;

    public class Size{
        public int weight;
        public int height;
    }

    public String getPosition(){
        return "Человек";
    }

    public void live() {}

    public Human(String name, int age) {
        this.age = age;
        this.name = name;
    }


   /* public Human(boolean isSoldier) {
        this.isSoldier = isSoldier;
        this.id = nextId;
        nextId++;
    }*/

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human human){
        children.add(human);
    }

    public void removeChild(Human human){
        children.remove(human);
    }

    public int getId() {
        return id;
    }


    public void printSize() {
        System.out.println("Рост: " + size.weight + " Вес: " + size.height);
    }

    public void printData() {
        System.out.println(getPosition() + ": "+ name);
    }
}