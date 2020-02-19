import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class TrackBeh extends Behaviour {
    private String firstAg;
    TrackBeh(String firstAg){
        this.firstAg = firstAg;
    }
    @Override
    public void action() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageTemplate mtRoute = MessageTemplate.MatchProtocol("Route");
        ACLMessage receiveRoute = myAgent.receive(mtRoute);
        MessageTemplate mt = MessageTemplate.MatchProtocol("InitFind");
        ACLMessage receive = myAgent.receive(mt);
        if (receiveRoute != null && receive != null){
            String desiredAg = receive.getContent();
            ArrayList<String> Send = new ArrayList<>(Arrays.asList(receiveRoute.getContent().split(" ")));
            double lenght = Double.parseDouble(Send.get(0));
            Send.remove(0);
            String name = myAgent.getLocalName();
            AgentConfig agentConfig = WorkWithCfgs.unMarshalAny(AgentConfig.class, "file" + name + ".xml");
            ArrayList<String> sendNew = new ArrayList<>();
            for (Neighbour neighbour : agentConfig.getNeighbours()) {
                sendNew.add(neighbour.getName());
            }
            for (Neighbour neighbour : agentConfig.getNeighbours()) {
                for (String send : Send) {
                    if (send.equals(neighbour.getName())) {
                        sendNew.remove(neighbour.getName());
                    }
                }
            }
            double lenghtOld = lenght;
            for (Neighbour neighbour : agentConfig.getNeighbours()) {
                for (String send : sendNew) {
                    if (neighbour.getName().equals(send)) {
                        lenght = lenghtOld;
                        StringBuilder sender = new StringBuilder();
                        for (String s : Send) {
                            sender.append(s).append(" ");
                        }
                        String message = sender + myAgent.getLocalName();
                        lenght += neighbour.getWeight();
                        String value = Double.toString(lenght);
                        message = value + " " + message;
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        AID aid = new AID(send, false);
                        msg.setProtocol("Route");
//                        System.out.println(myAgent.getLocalName() + " отправляет к " + send + " : " + message);
                        msg.setContent(message);
                        msg.addReceiver(aid);
                        myAgent.send(msg);
                        ACLMessage findMsg = new ACLMessage(ACLMessage.INFORM);
                        findMsg.setContent(desiredAg);
                        findMsg.addReceiver(aid);
                        findMsg.setProtocol("InitFind");
                        myAgent.send(findMsg);
                        if (send.equals(desiredAg)){
                            myAgent.addBehaviour(new Final(message, send, firstAg));
                        }

                    }
                }
            }
        }
        block();
    }

    @Override
    public boolean done() {
        return false;
    }
}
