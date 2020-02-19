import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.Collections;

public class MyBehaviour extends Behaviour {
    private AID topic;
    private boolean Alles = false;
    private String Winner;
    private int Sellers = 3;
    private double i;
    private ArrayList<Double> ListValue = new ArrayList<>();
    private ArrayList<String> ListSellers = new ArrayList<>();

    MyBehaviour(AID topic){
        this.topic = topic;
    }
    @Override
    public void action() {
        MessageTemplate mt  = MessageTemplate.and(
                MessageTemplate.MatchTopic(topic),
                MessageTemplate.MatchProtocol("ActuallyValue"));
        ACLMessage receivedMsg = myAgent.receive(mt);
        if (receivedMsg != null){
            System.out.println("Аgent: " + receivedMsg.getSender().getLocalName()
                    + " Requested: " + receivedMsg.getContent());
            if (receivedMsg.getContent().equals("Left")){
                Sellers -= 1;
            }
            if (Sellers > 1){
                if (!receivedMsg.getContent().equals("Left")){
                    ListValue.add(Double.parseDouble(receivedMsg.getContent()));
                }
                ListSellers.add(receivedMsg.getSender().getLocalName());
                if (ListValue.size() == Sellers){
                    i = Collections.min(ListValue);
                    Winner = ListSellers.get(ListValue.indexOf(i));
                    System.out.println("Current min: " + i);
                    ACLMessage messageAboutValue = new ACLMessage(ACLMessage.INFORM);
                    messageAboutValue.setProtocol("Value");
                    messageAboutValue.addReceiver(topic);
                    messageAboutValue.setContent(Double.toString(i));
                    myAgent.send(messageAboutValue);
                    ListSellers.clear();
                    ListValue.clear();
                }
            }
            else {
                Alles = true;
            }
        }
        else{
            block();
        }
    }

    @Override
    public int onEnd() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(topic);
        msg.setProtocol("Winner");
        msg.setContent(Winner +  " " + i);
        myAgent.send(msg);
        return super.onEnd();
    }

    @Override
    public boolean done() {
        return Alles;
    }
}
