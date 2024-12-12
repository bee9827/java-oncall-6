package oncall;

public class WorkerScheduler {
    private final Workers weekdays;
    private final Workers weekends;

    private Worker prevWorker;
    private Worker nextWeekdayWorker;
    private Worker nextWeekendWorker;

    public WorkerScheduler(Workers weekdays, Workers weekends) {
        this.weekdays = weekdays;
        this.weekends = weekends;
    }


    public Worker getNextWeekDayWorker(){
        if(nextWeekdayWorker != null){
            Worker worker = nextWeekdayWorker;
            nextWeekdayWorker = null;
            setPrevWorket(worker);
            return worker;
        }

        Worker worker = weekdays.getNextWorker();
        if(prevWorker == worker){
            nextWeekdayWorker = worker;
            worker = weekends.getNextWorker();
        }
        setPrevWorket(worker);
        return worker;
    }


    public Worker getNextWeekendWorker(){
        if(nextWeekendWorker != null){
            Worker worker = nextWeekendWorker;
            nextWeekendWorker = null;
            setPrevWorket(worker);
            return worker;
        }

        Worker worker = weekends.getNextWorker();
        if(prevWorker == worker){
            nextWeekendWorker = worker;
            worker = weekdays.getNextWorker();
        }
        setPrevWorket(worker);
        return worker;
    }

    private void setPrevWorket(Worker worker) {
        prevWorker = worker;
    }
}
