import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FindBeh extends Behaviour {
    private String desiredAg;
    private String firstAg;
    private double minLenght = 100000;

    FindBeh(String desiredAg, String firstAg){
        this.desiredAg = desiredAg;
        this.firstAg = firstAg;
    }

    @Override
    public void onStart() {
        String name = myAgent.getLocalName();
        AgentConfig agentConfig = WorkWithCfgs.unMarshalAny(AgentConfig.class, "file" + name + ".xml");
        for (Neighbour neighbour : agentConfig.getNeighbours()) {
            String Route = "";
            Route = Route + neighbour.getWeight() + " " + myAgent.getLocalName();
            String receiver = neighbour.getName();
            AID aid = new AID(receiver, false);
            ACLMessage findMsg = new ACLMessage(ACLMessage.INFORM);
            findMsg.setContent(desiredAg);
            findMsg.addReceiver(aid);
            findMsg.setProtocol("InitFind");
            myAgent.send(findMsg);
            ACLMessage route = new ACLMessage(ACLMessage.INFORM);
            route.setProtocol("Route");
            route.addReceiver(aid);
            route.setContent(Route);
            myAgent.send(route);
//            System.out.println(myAgent.getLocalName() + " отправляет к " + receiver + " : " + Route);
            if (receiver.equals(desiredAg)){
                myAgent.addBehaviour(new Final(Route, receiver, firstAg));
            }
        }
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchProtocol("Final");
        ACLMessage recive = myAgent.receive();
        if (recive != null){
            ArrayList<String> Send = new ArrayList<>(Arrays.asList(recive.getContent().split(" ")));
            double lenght = Double.parseDouble(Send.get(0));
            Send.remove(0);
            if (lenght < minLenght){
                minLenght = lenght;
                System.out.println("Минимальный путь: " + Send + " Длина: " + minLenght);
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
