package tom1.ch_12;

public class ThreadTest {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {

        var bank = new Bank(4,10000);
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++){
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0,1,amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            }catch (InterruptedException e){

            }
        };


        Runnable task2 = () -> {
            try {
                for(int i = 0; i < STEPS; i++){
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(2,3,amount);
                        Thread.sleep((int)(DELAY * Math.random()));
                }
            }catch (InterruptedException e){

            }
        };

       var t1 =  new Thread(task1);
       t1.setName("Поток 1");
        var t2 = new Thread(task2);
        t2.setName("Поток 2");
    t1.start();
    t2.start();
    }
}
