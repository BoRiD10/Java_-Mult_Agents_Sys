public interface Producer { //Определяют некоторый функционал
    String getName();
    void runProducing();//метод
    double getPrice(int value);
    void buy(int value);
}
