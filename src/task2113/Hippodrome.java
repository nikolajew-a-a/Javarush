package task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }
    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException {
        Horse horse1 = new Horse("poni",3,0);
        Horse horse2 = new Horse("loli",3,0);
        Horse horse3 = new Horse("mustang",3,0);

        List<Horse> allHorses = new ArrayList<>();
        allHorses.add(horse1);
        allHorses.add(horse2);
        allHorses.add(horse3);

        game = new Hippodrome(allHorses);

        game.getHorses();

        game.run();

        game.printWinner();

    }

    private List<Horse> horses = new ArrayList<>();

    public List<Horse> getHorses(){
        return  horses;
    }

    public void move(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).move();
        }
    }

    public void print(){
        for (int i = 0; i < horses.size(); i++){
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }

    }
    
    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);} }


    public Horse getWinner(){
        int horseNumb = 0;
        double maxDistance = 0;
        Horse winner = new Horse(null,0,0);
        for (int i = 0; i<this.horses.size();i++)
        {
            Horse horse = this.horses.get(i);
            double horseDistance = horse.getDistance();
            if (horseDistance>maxDistance){
                maxDistance = horseDistance;
                horseNumb = i;
            }
        }
        winner = this.horses.get(horseNumb);
        return winner;
    }
    
    public void printWinner() {
        Horse winner = getWinner();
        System.out.println("Winner is " + winner.getName() + "!");
    }

}
