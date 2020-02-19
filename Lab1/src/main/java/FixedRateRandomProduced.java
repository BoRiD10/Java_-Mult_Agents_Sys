import java.util.Random;

public class FixedRateRandomProduced implements Producer { //класс FRRP вызывает итерфейс Producer
    public double getValue() {
        return value;
    }

    private double value = 0; //Объявление переменной стоимость
    private Random r = new Random(); //создание оператора рандомного числа

    FixedRateRandomProduced() {
    }

    public String getName() {
        return name;
    }

    private String name = "None";

    FixedRateRandomProduced(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public void runProducing() { //реализация первого метода, по получению цен
        //создание и запуск потока
        //в run определяется весь тот код, который выполняется при запуске потока
        //цикл пока правда
        //Возможность предупреждения и разрешения исключительной ситуации
        //код, по обработке исключения;
        //рассказывает нам, что произошло, и где в коде это произошло.
        //Вывод стоимости в консоль
        // создание потока?
        Thread thread = new Thread(new Runnable() { //создание и запуск потока
            public void run() { //в run определяется весь тот код, который выполняется при запуске потока
                while (true) { //цикл пока правда
                    try { //Возможность предупреждения и разрешения исключительной ситуации
                        Thread.sleep(5000);
                    } catch (InterruptedException e) { //код, по обработке исключения;
                        e.printStackTrace(); //рассказывает нам, что произошло, и где в коде это произошло.
                    }
                    setValue(value + r.nextDouble() * 20);
                    if (value > 100) {
                        setValue(100);
                    }
                    System.out.println("value FixRand = " + value); //Вывод стоимости в консоль
                }
            }
        });
        thread.start(); //запуск потока
    }

    public double getPrice(int boughtValue) {//формируем цену
        System.out.println(getName()+" "+getValue());
        if (value >= boughtValue) {
            return (100 - getValue()) * 2;//цена
        }
        else {
            return -1;
        }
    }

    public void buy(int boughtValue) {
        this.value -= boughtValue;// выкуп продукции
    }
}