import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class Generation extends Agent{

    @Override
    protected void setup() {
        if (getLocalName().equals("Distributor1") || getLocalName().equals("Distributor2")){
            regAg();
            addBehaviour(new DistributorBeh());
        }

        switch (getLocalName()){
//            case "WindAgent":
//                regAg();
//                addBehaviour(new WindEnergyGeneration());
            case  "HeatAgent":
                regAg();
                addBehaviour(new HeatEnergyGeneration());
//            case  "SunAgent":
//                regAg();
//                addBehaviour(new SunEnergyGeneration());
        }

//        else{
//
//        }

    }


    private void regAg(){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Auction");
        sd.setName("Auction" + getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
