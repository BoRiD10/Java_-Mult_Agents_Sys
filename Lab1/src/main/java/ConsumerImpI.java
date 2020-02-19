import java.util.ArrayList;
import java.util.List;

public class ConsumerImpI implements Consumer {
    private List<Producer> producers = new ArrayList<>();//List- набор из объектов <>- обертка
    private Thread t;

    public void addProducer(Producer p) { producers.add(p); }

    public void addProducer1(Producer f) { producers.add(f); }

    public void addProducer2(Producer r) { producers.add(r); }

    public void startConsuming() {
        t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double bestPrice = Double.MAX_VALUE; //Объявление стартовой цены
                    Producer bestProducer = null; //лучший прод не найден
                    for (Producer producer : producers) { //перебор продюссеров
                        Double price = producer.getPrice(50);
                        System.out.println("prod = "+producer.getName()+" "+price);
                        if (bestPrice > price && price != -1) { //Если лучшая цена > новой цены, то
                            bestProducer = producer; //новый лучший прод
                            bestPrice = price; //новая цена
                            System.out.println("best = "+bestProducer.getName());

                        }
                    }
                    if (bestProducer != null) { //если есть продюссер, то
                        bestProducer.buy(50); //покупаем
                        System.out.println("Value has been bought successfully"+bestProducer.getName());
                    } else {
                        System.err.println("the value can not be consumed");
                    }
                }
            }
        });
        t.start();
    }
}