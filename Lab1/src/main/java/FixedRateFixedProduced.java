import java.util.Random;

public class FixedRateFixedProduced extends FixedRateRandomProduced {
    private double value = 0;
    private Random r = new Random();

    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getValue() {
        return value;
    }

    private String name = "None";

    FixedRateFixedProduced(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public void runProducing() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setValue(getValue() + 10.0);
                    if (getValue() > 100) {
                        setValue(100);
                    }
                    System.out.println("value FixFix = " + getValue());
                }
            }
        });
        thread.start();
    }
}
