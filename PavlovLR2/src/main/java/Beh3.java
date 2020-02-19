import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;

public class Beh3 extends Behaviour {

    @Override
    public void action() {
//      Ожидание сообщения о очередности.
        ArrayList<Double> NewData = new ArrayList<>();
        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchProtocol("yourTurn"));
        ACLMessage msg = myAgent.receive(template);
        if (msg != null) {
//          Отправка актуальных значений X и step
            for (String x: msg.getContent().split(" ")) {
                NewData.add(Double.parseDouble(x));
            }
            myAgent.addBehaviour(new Beh1( NewData.get(0),NewData.get(1), myAgent.getLocalName()));
        }
        block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
