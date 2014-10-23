package ms.park.military.models.departments;

public class Army extends Department {
    @Override
    public String getLabel() {
        return "육군";
    }

    @Override
    public int getWorkingMonths() {
        return 21;
    }
}
