public class PowerConfig {
    void createField(){
        Config a = new Config();
        a.setAgentName("Consumer1");
        a.getPower().add(new Power(1,2));
        a.getPower().add(new Power(2,2));
        a.getPower().add(new Power(3,2));
        a.getPower().add(new Power(4,2));
        a.getPower().add(new Power(5,2));
        a.getPower().add(new Power(6,4));
        a.getPower().add(new Power(7,4));
        a.getPower().add(new Power(8,6));
        a.getPower().add(new Power(9,6));
        a.getPower().add(new Power(10,4));
        a.getPower().add(new Power(11,4));
        a.getPower().add(new Power(12,4));
        a.getPower().add(new Power(13,4));
        a.getPower().add(new Power(14,4));
        a.getPower().add(new Power(15,4));
        a.getPower().add(new Power(16,4));
        a.getPower().add(new Power(17,8));
        a.getPower().add(new Power(18,8));
        a.getPower().add(new Power(19,8));
        a.getPower().add(new Power(20,8));
        a.getPower().add(new Power(21,8));
        a.getPower().add(new Power(22,2));
        a.getPower().add(new Power(23,2));
        a.getPower().add(new Power(0,2));

        Config b = new Config();
        b.setAgentName("Consumer2");
        b.getPower().add(new Power(1,1));
        b.getPower().add(new Power(2,1));
        b.getPower().add(new Power(3,1));
        b.getPower().add(new Power(4,1));
        b.getPower().add(new Power(5,1));
        b.getPower().add(new Power(6,2));
        b.getPower().add(new Power(7,2));
        b.getPower().add(new Power(8,2));
        b.getPower().add(new Power(9,6));
        b.getPower().add(new Power(10,6));
        b.getPower().add(new Power(11,6));
        b.getPower().add(new Power(12,4));
        b.getPower().add(new Power(13,4));
        b.getPower().add(new Power(14,4));
        b.getPower().add(new Power(15,4));
        b.getPower().add(new Power(16,4));
        b.getPower().add(new Power(17,8));
        b.getPower().add(new Power(18,6));
        b.getPower().add(new Power(19,6));
        b.getPower().add(new Power(20,6));
        b.getPower().add(new Power(21,6));
        b.getPower().add(new Power(22,6));
        b.getPower().add(new Power(23,1));
        b.getPower().add(new Power(0,1));

        WorkWithCfgs.marshalAny(Config.class, a, "Consumer1.xml");
        WorkWithCfgs.marshalAny(Config.class, b, "Consumer2.xml");
    }
}
