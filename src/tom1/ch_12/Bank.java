package tom1.ch_12;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Bank {

    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();

    public Bank(int n,double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount ){

        bankLock.lock();
        try {

            if (accounts[from] < amount) return;
            Logger.getGlobal().info(String.valueOf(Thread.currentThread()));
            accounts[from] -= amount;
            Logger.getGlobal().info( "сумма: " + amount + " списано с " + from + " на " + to);
            accounts[to] += amount;
            Logger.getGlobal().info("Баланс: " + getTotalBalance());
        }finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        double sum = 0;
        for (double a : accounts){
            sum += a;
        }
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
