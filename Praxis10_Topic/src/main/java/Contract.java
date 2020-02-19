import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class Contract extends Behaviour {

    private ArrayList<String> Value = new ArrayList<>();

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchProtocol("Winner");
        ACLMessage winContr = myAgent.receive(mt);
        if (winContr != null){
            Value.addAll(Arrays.asList(winContr.getContent().split(" ")));
            System.out.println("The Deal ic concluded with: " + Value.get(0) + " Transaction amount: " + Value.get(1));
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
