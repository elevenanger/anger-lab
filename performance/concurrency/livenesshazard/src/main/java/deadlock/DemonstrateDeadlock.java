package deadlock;

import deadlock.dynamiclockorder.Account;
import deadlock.dynamiclockorder.BankAccount;
import deadlock.dynamiclockorder.Transfer;

import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author : anger
 * 死锁演示
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1_000_000;

    public static void main(String[] args) {
        final Random random = new Random();
        final Account[] accounts = new Account[random.nextInt(100)];

        IntStream.range(0, accounts.length)
            .forEach(i -> accounts[i] = new BankAccount());

        class ThransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNTS);
                    int toAcct = random.nextInt(NUM_ACCOUNTS);

                    BigDecimal amount = new BigDecimal(random.nextInt(100));
                    Transfer.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new ThransferThread().start();
        }
    }
}
