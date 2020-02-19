import java.util.Random;

public class RandomRateRandomProduced extends FixedRateRandomProduced {
    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    private double value = 0;
    private Random r = new Random();

    @Override
    public String getName() {
        return name;
    }

    private String name = "None";

    RandomRateRandomProduced(String name) {
        this.name = name;
    }

    @Override
    public void runProducing() {
        //выбор шага,
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int Rate = (int) (2000 + r.nextDouble() * 5000); //выбор шага,
                System.out.println(Rate);
                while (true) {
                    try {
                        Thread.sleep(Rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setValue(value + r.nextDouble() * 20);
                    if (value > 100) {
                        setValue(100);
                    }
                    System.out.println("value RandRand = " + value);
                }
            }
        });
        thread.start();
    }
}
