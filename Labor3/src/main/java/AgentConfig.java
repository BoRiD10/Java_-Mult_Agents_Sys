import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "agentConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentConfig {

    @XmlElement
    private String agentName;
    @XmlElementWrapper(name = "Neighbours")
    @XmlElement(name = "neighbour")
    private List<Neighbour> neighbours = new ArrayList<>();

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<String> neighbours) {
        neighbours = neighbours;
    }
}
