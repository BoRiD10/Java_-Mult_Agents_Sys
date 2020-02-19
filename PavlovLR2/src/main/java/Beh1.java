import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Beh1 extends Behaviour {
    private double x;
    private double step;// Шаг расчета
    private boolean Turn = true;
    private int iter = 0;//Количество сообщений
    private ArrayList<Double> Fx1 = new ArrayList<>(), Fx2 = new ArrayList<>(), Fx3 = new ArrayList<>();//Значения функций

    //  Зададим входные параметры для поведения
    Beh1(double x, double step, String workAgent){
        this.x = x; this.step = step;
    }

    @Override
    public void onStart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Работает: " + myAgent.getLocalName());
//      Формирование листа X
        StringBuilder mesAboutX = new StringBuilder();
        double[] nums = new double[]{ x - step, x, x + step};
        for (Double x : nums){
            mesAboutX.append(x).append(" ");
        }

//      Список получателей
        ArrayList<String> agentNames = new ArrayList<>();
        agentNames.add("Agent1");agentNames.add("Agent2");agentNames.add("Agent3");
        agentNames.remove(getAgent().getLocalName());

//      Отправка X
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        agentNames.stream ().map(el -> new AID(el,false)).collect(Collectors.toList()).forEach(msg::addReceiver);
        msg.setProtocol("valueX");
        msg.setContent(String.valueOf(mesAboutX));
        getAgent().send(msg);
//      Список значений функции для работающего агента
        for (double num : nums){
            double numFunc = ((Agents) getAgent()).calc(num);
            switch (getAgent().getLocalName()) {
                case "Agent1":
                    Fx1.add(numFunc);
                    break;
                case "Agent2":
                    Fx2.add(numFunc);
                    break;
                case "Agent3":
                    Fx3.add(numFunc);
                    break;
            }
        }
    }

    @Override
    public void action() {
//      Получение значений от других агентов
        MessageTemplate mesTemp = MessageTemplate.MatchProtocol("Fx");
        ACLMessage msg = myAgent.receive(mesTemp);
        if (msg != null){
            switch (msg.getSender().getLocalName()){
                case "Agent1":
                    for (String Fx : msg.getContent().split(" ")) {
                        Fx1.add(Double.parseDouble(Fx));
                    }
                    iter++;
                    break;
                case "Agent2":
                    for (String Fx : msg.getContent().split(" ")) {
                        Fx2.add(Double.parseDouble(Fx));
                    }
                    iter++;
                    break;
                case "Agent3":
                    for (String Fx : msg.getContent().split(" ")) {
                        Fx3.add(Double.parseDouble(Fx));
                    }
                    iter++;
                    break;
            }
//          Если полученно сообщение от обоих агентов, то идем дальше
            if (iter == 2) {
                Turn = false;
            }
        }else {
            block();
        }

    }
    @Override
    public int onEnd() {
        ArrayList<Double> func = new ArrayList<>();
//      Формирование суммарного массива
        for (int i=0; i<Fx1.size();i++){
            func.add(Fx1.get(i)+Fx2.get(i)+Fx3.get(i));
        }
//      Нахождение максимума функции
        double maxFx = func.get(0);
        int idMax = func.indexOf(maxFx);

        for (int i = 0; i < func.size() ; i++) {
            if (func.get(i) > maxFx) {
                maxFx = func.get(i);
                idMax = i;
            }
        }
//      Анализ полученых результатов и планирование дальнейших действий
        switch (idMax) {
            case 0: x = x-step;
                break;
            case 1: step = step/2;
                break;
            case 2: x = x + step;
                break;
        }
//      Если достаточная точность расчетов достигнута, то Alles.
        if (step < 0.1) {
            myAgent.doDelete();
            System.out.println("Максимальное значение функции: " + func.get(1));
            System.out.println("Значение Х: " + x);
            ArrayList<String> agentNames = new ArrayList<>();
            agentNames.add("Agent1");agentNames.add("Agent2");agentNames.add("Agent3");
            agentNames.remove(getAgent().getLocalName());
            ACLMessage msgOut = new ACLMessage(ACLMessage.INFORM);
            agentNames.stream ().map(el -> new AID(el,false)).collect(Collectors.toList()).forEach(msgOut::addReceiver);
            msgOut.setProtocol("Out");
            msgOut.setContent("End");
            getAgent().send(msgOut);
        }
//      Изменение очереди
        AID aid = ((Agents) getAgent()).Round(myAgent.getLocalName());
        ACLMessage msg1 = new ACLMessage(ACLMessage.REQUEST);
        msg1.addReceiver(aid);
        msg1.setProtocol("yourTurn");
        msg1.setContent(x + " " + step);
        getAgent().send(msg1);
        return super.onEnd();
    }

    @Override
    public boolean done() {
        return !Turn;
    }
}
