import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class HeatEnergyGeneration extends Behaviour {
    private double amount = 0;
    private AID topic;


    @Override
    public void action() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageTemplate mt = MessageTemplate.MatchProtocol("topicName");
        ACLMessage receivedMsg = getAgent().receive(mt);
        if (receivedMsg != null){
            topic = subsTopic(receivedMsg.getContent());
            System.out.println(topic);
        }else {
            block();
        }
        MessageTemplate mt2 = MessageTemplate.MatchTopic(topic);
        ACLMessage msg = getAgent().receive(mt2);
        if (msg != null){
            System.out.println(myAgent.getLocalName() + " : " + msg.getContent());
        }else {
            block();
        }


//        try {
//            Thread.sleep(12000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        amount += 2;
//        if (amount > 10) {
//            amount = 10;
//        }
//        System.out.println("Amount HeatEnergy = " + amount);
    }

    @Override
    public boolean done() {
        return false;
    }

    private AID subsTopic(String name){
        TopicManagementHelper topicHelper = null;
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
}
