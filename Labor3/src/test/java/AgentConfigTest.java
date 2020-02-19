import org.junit.Test;

public class AgentConfigTest {
    @Test
    public void createField(){
        AgentConfig a = new AgentConfig();
        a.setAgentName("Ag1");
        a.getNeighbours().add(new Neighbour("Ag2",2));
        a.getNeighbours().add(new Neighbour("Ag3",8));
        a.getNeighbours().add(new Neighbour("Ag4",4));

        AgentConfig b = new AgentConfig();
        b.setAgentName("Ag2");
        b.getNeighbours().add(new Neighbour("Ag1",2));
        b.getNeighbours().add(new Neighbour("Ag5",1));

        WorkWithCfgs.marshalAny(AgentConfig.class, a, "fileAg1.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, b,"fileAg2.xml");
    }
}
