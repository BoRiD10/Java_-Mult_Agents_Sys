import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitInvite extends Behaviour {
    private double minValue;
    private boolean stop = false;

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchProtocol("Invite");
        ACLMessage invite = myAgent.receive(mt);
        if (invite != null){
            AID topic = subsTopic(invite.getContent());
            switch (myAgent.getLocalName()){
                case "Subs1":
                    minValue = 3.5;
                    break;
                case "Subs2":
                    minValue = 4.5;
                    break;
                case "Subs3":
                    minValue = 3.8;
            break;
            }
//          Первое предложение.
            double firstBet = minValue * 2;
            ACLMessage mesAboitFirstBet = new ACLMessage(ACLMessage.INFORM);
            mesAboitFirstBet.setProtocol("ActuallyValue");
            mesAboitFirstBet.setContent(Double.toString(minValue*2));
            mesAboitFirstBet.addReceiver(topic);
            myAgent.send(mesAboitFirstBet);
            myAgent.addBehaviour(new Value(topic, minValue, firstBet));
            stop = true;
        } else block();
    }

    private AID subsTopic(String name){
        TopicManagementHelper topicHelper;
        AID jadeTopic = null;
        try {
            topicHelper = (TopicManagementHelper)
                    myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
            jadeTopic = topicHelper.createTopic(name);
            topicHelper.register(jadeTopic);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return jadeTopic;
    }

    @Override
    public boolean done() {
        return stop;
    }

}

