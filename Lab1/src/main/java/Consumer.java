public interface Consumer {
    void addProducer (Producer p); //добавляет продюсера
    void addProducer1 (Producer f);
    void addProducer2 (Producer r);
    void startConsuming();//начинает потребление
}
