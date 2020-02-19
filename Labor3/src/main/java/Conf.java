class Conf {
    void createField(){
        AgentConfig a = new AgentConfig();
        a.setAgentName("Ag1");
        a.getNeighbours().add(new Neighbour("Ag2",2));
        a.getNeighbours().add(new Neighbour("Ag3",1));
        a.getNeighbours().add(new Neighbour("Ag4",5));

        AgentConfig b = new AgentConfig();
        b.setAgentName("Ag2");
        b.getNeighbours().add(new Neighbour("Ag1",2));
        b.getNeighbours().add(new Neighbour("Ag5",9));

        AgentConfig c = new AgentConfig();
        c.setAgentName("Ag3");
        c.getNeighbours().add(new Neighbour("Ag1",1));
        c.getNeighbours().add(new Neighbour("Ag4",3));

        AgentConfig d = new AgentConfig();
        d.setAgentName("Ag4");
        d.getNeighbours().add(new Neighbour("Ag1",5));
        d.getNeighbours().add(new Neighbour("Ag3",3));
        d.getNeighbours().add(new Neighbour("Ag5",2));
        d.getNeighbours().add(new Neighbour("Ag6",9));

        AgentConfig e = new AgentConfig();
        e.setAgentName("Ag5");
        e.getNeighbours().add(new Neighbour("Ag4",2));
        e.getNeighbours().add(new Neighbour("Ag2",9));
        e.getNeighbours().add(new Neighbour("Ag6",6));

        AgentConfig f = new AgentConfig();
        f.setAgentName("Ag6");
        f.getNeighbours().add(new Neighbour("Ag5",6));
        f.getNeighbours().add(new Neighbour("Ag4",9));

        WorkWithCfgs.marshalAny(AgentConfig.class, a,"fileAg1.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, b,"fileAg2.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, c,"fileAg3.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, d,"fileAg4.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, e,"fileAg5.xml");
        WorkWithCfgs.marshalAny(AgentConfig.class, f,"fileAg6.xml");
    }
}
