package ms.park.military.models.departments;

public class Rotc extends Department {
    @Override
    public String getLabel() {
        return "ROTC";
    }

    @Override
    public int getWorkingMonths() {
        return 52;
    }
}
