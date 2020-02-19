import jade.core.Agent;

public class AgentMain extends Agent {
    @Override
    protected void setup() {
        String firstAg = "Ag1";
        String desiredAg = "Ag6";
        if (getLocalName().equals(firstAg)){
            addBehaviour(new FindBeh(desiredAg, firstAg));
        } else {
            addBehaviour(new TrackBeh(firstAg));
        }
    }
}
