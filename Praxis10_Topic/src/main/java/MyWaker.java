import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

class MyWaker extends WakerBehaviour {

    MyWaker(Agent a, long time) {
        super(a, time);
    }

    @Override
    protected void onWake() {
        System.out.println("Alles!");
    }

}
