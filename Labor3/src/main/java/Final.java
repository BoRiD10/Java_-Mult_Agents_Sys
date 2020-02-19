import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Final extends OneShotBehaviour {
    private String Route;
    private String Agent;
    private String firstAg;

    Final(String Route, String Agent, String firstAg){
        this.Route = Route;
        this.Agent = Agent;
        this.firstAg = firstAg;
    }

    @Override
    public void action() {
        ACLMessage routeMsg = new ACLMessage(ACLMessage.INFORM);
        AID aid = new AID(firstAg, false);
        routeMsg.addReceiver(aid);
        routeMsg.setContent(Route + " " + Agent);
        routeMsg.setProtocol("Final");
        myAgent.send(routeMsg);
    }
}
