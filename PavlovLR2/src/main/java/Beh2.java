import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Beh2 extends Behaviour {

    @Override
    public void action() {
//  Ожидание сообщения от работающего агента с Х, формирование значений остальных функций и отправка.
        StringBuilder Msg = new StringBuilder();
        MessageTemplate mtOut = MessageTemplate.MatchProtocol("Out");
        ACLMessage recMsgOut = myAgent.receive(mtOut);
        if (recMsgOut != null){
            if (recMsgOut.getContent().equals("End")){
                myAgent.doDelete();
            }
        }
        MessageTemplate mt = MessageTemplate.MatchProtocol("valueX");
        ACLMessage receivedMsg = myAgent.receive(mt);
        if (receivedMsg != null){
            for (String x: receivedMsg.getContent().split(" ")) {
                Msg.append(((Agents) getAgent()).calc(Double.parseDouble(x))).append(" ");
            }
            ACLMessage reply = receivedMsg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(String.valueOf(Msg));
            reply.setProtocol("Fx");
            getAgent().send(reply);
            System.out.println(getAgent().getLocalName() + " " + Msg);
        }
        block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
