import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "agentConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

    @XmlElement
    private String agentName;
    @XmlElementWrapper(name = "Power")
    @XmlElement(name = "power")
    private List<Power> power = new ArrayList<>();

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public List<Power> getPower() {
        return power;
    }

    public void setPower(List<Power> power) {
        this.power = power;
    }


}
