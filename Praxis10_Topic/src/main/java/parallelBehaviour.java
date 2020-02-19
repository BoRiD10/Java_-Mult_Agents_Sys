import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;

public class parallelBehaviour extends ParallelBehaviour {
    private Behaviour a, b;

    public parallelBehaviour(Behaviour a, Behaviour b) {
        super(WHEN_ALL);
        this.a = a;
        this.b = b;
    }

    @Override
    public void onStart() {
        addSubBehaviour(a);
        addSubBehaviour(b);
    }
}
