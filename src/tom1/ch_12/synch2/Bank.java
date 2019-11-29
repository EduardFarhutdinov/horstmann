package tom1.ch_12.synch2;


import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        bankLock = new ReentrantLock();
        Arrays.fill(accounts,initialBalance);
        sufficientFunds = bankLock.newCondition();
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException
    {
        while (accounts[from] < amount){
            wait();
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

    public synchronized double getTotalBalance()
    {
            double sum = 0;

            for (double a : accounts)
                sum += a;

            return sum;


    }

    /**
     * Gets the number of accounts in the bank.
     * @return the number of accounts
     */
    public int size()
    {
        return accounts.length;
    }
}
