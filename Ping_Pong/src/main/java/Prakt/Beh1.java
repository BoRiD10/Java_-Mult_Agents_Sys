package Prakt;

import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Beh1 extends Behaviour {
private ACLMessage receivedMsg = null;

    @Override
    public void onStart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Serv.Tennis.toString());
        dfd.addServices(sd);
        try {
            DFAgentDescription[] foundAgents = DFService.search(getAgent(), dfd);
            for (DFAgentDescription foundAgent : foundAgents) {
                if (foundAgent.getName().getLocalName().equals(getAgent().getLocalName())){
                    System.out.println(foundAgent.getName().getLocalName()+": Ping");
                }
                if (!foundAgent.getName().getLocalName().equals(getAgent().getLocalName())){
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setContent("Ping");
                    msg.addReceiver(foundAgent.getName());
                    getAgent().send(msg);
                }

            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.MatchContent("Pong");
        receivedMsg = getAgent().receive();
        if (receivedMsg != null){
            System.out.println("Какой фантастический розыгрыш!!! Что творит этот агент?!?!?!");
            block();
        }
    }

    @Override
    public boolean done() {
        return receivedMsg != null;
    }

    @Override
    public int onEnd() {
        getAgent().addBehaviour(new Beh2());
        return super.onEnd();
    }
}

