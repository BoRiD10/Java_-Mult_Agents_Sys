import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class CreateTopic extends OneShotBehaviour {

    @Override
    public void action() {
        String topicName = "Topic" + myAgent.getLocalName();
        AID createTopic = createTopic(topicName);
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Auction");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] foundAgents = DFService.search(getAgent(), dfd);
            for (DFAgentDescription foundAgent : foundAgents) {
                if (!foundAgent.getName().getLocalName().equals(getAgent().getLocalName())){
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setContent(topicName);
                    msg.addReceiver(foundAgent.getName());
                    getAgent().send(msg);
                }

            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }





































    }

//        AID topic = subsTopic(topicName);

//        myAgent.addBehaviour(new Behaviour() {
//            @Override
//            public void action() {
//                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
//                msg.addReceiver(topic);
//                msg.setContent("Hello");
//                getAgent().send(msg);
//            }
//
//            @Override
//            public boolean done() {
//                return false;
//            }
//        });
//    }
//
//
    private AID createTopic(String topicName){
        TopicManagementHelper topicHelper;
        AID jadeTopic = null;
        try {
            topicHelper = (TopicManagementHelper)
                    myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
            jadeTopic = topicHelper.createTopic(topicName);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return jadeTopic;
    }
//
//    private AID subsTopic(String name){
//        TopicManagementHelper topicHelper = null;
//        AID jadeTopic = null;
//        try {
//            topicHelper = (TopicManagementHelper)
//                    myAgent.getHelper(TopicManagementHelper.SERVICE_NAME);
//            jadeTopic = topicHelper.createTopic(name);
//            topicHelper.register(jadeTopic);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        return jadeTopic;
//    }

}
