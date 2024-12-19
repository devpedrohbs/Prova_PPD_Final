package src;
public class Fork {
    private boolean inUse = false;

    public synchronized boolean isBeingUsed() {
        return inUse;
    }

    public synchronized void pickUp(Philosopher philosopher) throws InterruptedException {
        while (inUse) {
            wait(); 
        }
        inUse = true;
        System.out.println("Filosofo " + philosopher.getId() + " pegou o garfo.");
    }

    public synchronized void release() {
        inUse = false;
        notify(); 
    }
}
