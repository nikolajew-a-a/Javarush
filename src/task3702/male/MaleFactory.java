package task3702.male;

import task3702.AbstractFactory;
import task3702.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age){
        if(age <= KidBoy.MAX_AGE)
            return (new KidBoy());
        else if (age>KidBoy.MAX_AGE & age <= TeenBoy.MAX_AGE)
            return (new TeenBoy());
        else if(age>TeenBoy.MAX_AGE)
            return (new Man());
        else
            return (null);
    }

}
