import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Register extends OneShotBehaviour {
    private String topicName;
    private AID topic;

    Register(String topicName, AID topic){
        this.topicName = topicName;
        this.topic = topic;
    }

    @Override
    public void action() {

        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Service.Seller.toString());
        dfd.addServices(sd);
        try {
            DFAgentDescription[] foundAgents = DFService.search(getAgent(), dfd);
            for (DFAgentDescription foundAgent : foundAgents) {
                if (!foundAgent.getName().getLocalName().equals(getAgent().getLocalName())) {
                    ACLMessage invite = new ACLMessage(ACLMessage.INFORM);
                    invite.setProtocol("Invite");
                    invite.addReceiver(foundAgent.getName());
                    invite.setContent(topicName);
                    myAgent.send(invite);
                }
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        myAgent.addBehaviour(new Contract());
        myAgent.addBehaviour(new parallelBehaviour(
                new MyBehaviour(topic),
                new MyWaker(myAgent, 20000)
        ));
    }
}
