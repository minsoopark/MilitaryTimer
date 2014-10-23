package ms.park.military.models.departments;

public class AirForce extends Department {
    @Override
    public String getLabel() {
        return "공군";
    }

    @Override
    public int getWorkingMonths() {
        return 24;
    }
}
