package Prakt;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class Ag1 extends Agent {
    @Override
    protected void setup() {
        regAg();
        if (getLocalName().equals("Ag1")){
            addBehaviour(new Beh1());
        } else addBehaviour(new Beh2(){
        });
    }

    private void regAg(){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(Serv.Tennis.toString());
        sd.setName(Serv.Tennis.toString()+getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
