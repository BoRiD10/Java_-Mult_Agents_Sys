package Prakt;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Beh2 extends Behaviour {
    private ACLMessage receivedMsg = null;
    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchContent("Ping");
        receivedMsg = getAgent().receive();
        if (receivedMsg != null){
            System.out.println(getAgent().getLocalName()+": Pong");
            ACLMessage reply = receivedMsg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent("Pong");
            getAgent().send(reply);
        }else {
            block();
        }
    }

    @Override
    public boolean done() {
        return receivedMsg != null;
    }

    @Override
    public int onEnd() {
        getAgent().addBehaviour(new Beh1());
        return super.onEnd();
    }
}
