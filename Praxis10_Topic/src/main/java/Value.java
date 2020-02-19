import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Value extends Behaviour {
    private AID topic;
    private double Bet;
    private double minValue;
    private boolean stop = false;

    Value(AID topic, Double minValue, Double Bet){
        this.topic = topic;
        this.minValue = minValue;
        this.Bet = Bet;
    }

    @Override
    public void action() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol("Value"),
                MessageTemplate.MatchTopic(topic));
        ACLMessage torg = myAgent.receive(mt);
        if (torg != null){
            ACLMessage newTorg = new ACLMessage(ACLMessage.INFORM);
            newTorg.setProtocol("ActuallyValue");
//          Если можем уменьшаем, если ненадо оставляем, если предел, то дропаем
            if (Double.parseDouble(torg.getContent()) < Bet){
                Bet *= 0.9;
                if (Bet < minValue){
                    newTorg.setContent("Left");
                    newTorg.addReceiver(topic);
                    stop = true;
                }else {
                    newTorg.setContent(Double.toString(Bet));
                    newTorg.addReceiver(topic);
                }
            }else {
                newTorg.setContent(Double.toString(Bet));
                newTorg.addReceiver(topic);
            }
//            System.out.println("Агент: " + myAgent.getLocalName() + " Отправил: " + newTorg.getContent());
            myAgent.send(newTorg);
        } else block();
    }

    @Override
    public boolean done() {
        return stop;
    }
}
