import jade.core.AID;
import jade.core.ServiceException;
import jade.core.behaviours.Behaviour;
import jade.core.messaging.TopicManagementHelper;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class DistributorBeh extends Behaviour {
    private AID topic;

    @Override
    public void onStart() {
        String topicName = "Topic" + myAgent.getLocalName();
        AID createTopic = createTopic(topicName);
        topic = subsTopic(topicName);
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

    @Override
    public void action() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(topic);
        msg.setContent("Hello");
        getAgent().send(msg);
        System.out.println(topic.getLocalName());
    }

    @Override
    public boolean done() {
        return false;
    }

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
