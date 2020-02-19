public class Main {
    public static void main(String[] args) {
//        FixedRateRandomProduced p = new FixedRateRandomProduced("FixRand"); //Cоздаем новый объект класса FRRP
//        p.runProducing(); //Вызываем метод runProd из FRRP
//        RandomRateRandomProduced r = new RandomRateRandomProduced("RandRand");
//        r.runProducing();
        FixedRateFixedProduced f = new FixedRateFixedProduced("FixFix");
        f.runProducing();

        Consumer consumer = new ConsumerImpI(); //Cоздаем новый объект класса Сonsumer
//        consumer.addProducer(p);// Вызываем метод addProd
//        consumer.addProducer2(r);
        consumer.addProducer2(f);
        consumer.startConsuming(); // Вызываем метод startCons
    }
}
