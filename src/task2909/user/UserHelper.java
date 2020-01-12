package task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);

    public void printUsers() {
        userAnya.printInfo();
        /*System.out.println("Имя: " + userAnya.getName());
        System.out.println("Фамилия: " + userAnya.getSurname());*/
        userAnya.printAdditionalInfo();

        userRoma.printInfo();
        /*System.out.println("Имя: " + userRoma.getName());
        System.out.println("Фамилия: " + userRoma.getSurname());*/
        userRoma.printAdditionalInfo();
    }

    public int calculateAverageAge() {
        User userUra = new User("Юра", "Карп", 28);
        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;
    }

    public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {
        /*base.set(base.get() + age / 100);
        base.set((int) (base.get() * (hasWork ? 1.1 : 0.9)));
        base.set((int) (base.get() * (hasHouse ? 1.1 : 0.9)));*/

        int res = base.get() + age / 100;
        if (hasWork)
            res *= 1.1;
        else
            res *= 0.9;

        if (hasHouse)
            res *= 1.1;
        else
            res *= 0.9;

        return res;
    }

    public String getBossName(User user) {
        /*Work work = user.getWork();
        return work.getBoss();*/
        return user.getBoss();
    }
}