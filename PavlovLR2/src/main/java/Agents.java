import jade.core.AID;
import jade.core.Agent;

public class Agents extends Agent {

    @Override
    protected void setup() {
        int x = (int) (Math.random()*1);
        double step = 0.5;
        if (getLocalName().equals("Agent1")){
            addBehaviour(new Beh1(x,step,"Agent1"));
        }
        addBehaviour(new Beh2());
        addBehaviour(new Beh3());
    }

    public double calc(double x){
        switch (getLocalName()){
            case "Agent1":
                return -x*x+5;
            case "Agent2":
                return 2*x+2;
            case "Agent3":
                return Math.sin(x);
        }
        return x;
    }
    public AID Round (String agentName){
        switch (agentName) {
            case "Agent1":
                return new AID("Agent2", false);
            case "Agent2":
                return new AID("Agent3", false);
            case "Agent3":
                return new AID("Agent1", false);
        }
        return new AID();
    }
}
