package src;
public class Philosopher {
    private final int id;
    private int thoughts = 0;
    private int meals = 0;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int id) {
        this.id = id;
        this.leftFork = TelnetServer.forks[id];
        this.rightFork = TelnetServer.forks[(id + 1) % TelnetServer.NUMBER_OF_PHILOSOPHERS];
    }

    public int getId() {
        return id;
    }

    public void think() {
        try {
            int thinkingTime = (int) (Math.random() * 200 + 300); 
            thoughts++;
            System.out.println("Filosofo " + id + " está pensando pela " + thoughts + "ª vez por " + thinkingTime + " ms.");
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void eat() throws InterruptedException {
        while (meals < 10) { 
            think();
            while (leftFork.isBeingUsed() || rightFork.isBeingUsed()) {
                Thread.sleep(10); 
            }

            leftFork.pickUp(this);
            rightFork.pickUp(this);
            meals++;
            System.out.println("Filosofo " + id + " esta comendo pela " + meals + "ª vez.");
            Thread.sleep(500); 

            leftFork.release();
            rightFork.release();
            System.out.println("Filosofo " + id + " devolveu os garfos.");
            think();
        }

        System.out.println("Filosofo " + id + " alcançou o limite de 10 refeições.");
    }

    public void releaseForks() {
        leftFork.release();
        rightFork.release();
        System.out.println("Filosofo " + id + " devolveu os garfos.");
    }

    public String getStatus() {
        return "Filosofo " + id + " -> Pensou: " + thoughts + " vezes, Comeu: " + meals + " vezes.";
    }
}
