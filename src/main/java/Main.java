import java.math.BigInteger;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {


    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public final static BigInteger[][] ONE = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};

    public static BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] res = {
                {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])), a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},
                {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])), a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))}
        };
        return res;
    }
    public static BigInteger[][] pow(BigInteger[][] a, long k) {

        if (k == 0) return ONE;
        if (k == 1) return a;
        if (k == 2) return mul(a, a);
        if (k % 2 == 1) return mul(ONE, pow(a, k - 1));
        return pow(pow(a, k / 2), 2);
    }

    public static void main(String args[])
    {
        BigInteger[][] rez;
        long n = 0L;
        long startTime = 0L;
        long timeSpent = 0L;

        startTime = System.nanoTime();

        if(args.length == 0)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of Fibonacci elements");
            try {
                n = sc.nextLong();
            }
            catch (Exception ex)
            {
                System.out.println("Invalid input");
                return;
            }
        }
        else {
            n = new Long(args[0]);
        }

        for (long i = 1L ; i <= n; i = i + 3)
        {
            rez = pow(ONE, i);
            if(i == n)
                System.out.print(rez[0][0]+" ");
            else if(i == n - 1)
                System.out.print(rez[0][0]+" "+rez[0][1]+" ");
            else
                System.out.print(rez[0][0]+" "+rez[0][1]+" "+rez[1][1]+" ");
        }
        timeSpent = System.nanoTime() - startTime;
        System.out.println();
        logger.info("Time "+Long.toString(timeSpent)+" ns");

    }
}
