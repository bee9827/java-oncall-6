package oncall;

import java.util.List;

public class Workers {
    private final List<Worker> workers;
    private int currentIdx = 0;

    public Workers(List<Worker> workers) {
        valid(workers);
        this.workers = workers;
    }

    public Worker getNextWorker(){
        Worker currentWorker = workers.get(currentIdx);
        currentIdx = (currentIdx + 1) % workers.size();

        return currentWorker;
    }

    private void valid(List<Worker> workers){
        if(workers.stream().distinct().count() != workers.size()){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }
}
