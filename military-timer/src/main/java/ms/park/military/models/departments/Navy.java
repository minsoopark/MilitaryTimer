package ms.park.military.models.departments;

public class Navy extends Department {
    @Override
    public String getLabel() {
        return "해군";
    }

    @Override
    public int getWorkingMonths() {
        return 23;
    }
}
