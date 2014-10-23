package ms.park.military.models.departments;

public class Marine extends Department {
    @Override
    public String getLabel() {
        return "해병대";
    }

    @Override
    public int getWorkingMonths() {
        return 21;
    }
}
