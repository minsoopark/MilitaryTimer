package ms.park.military.models.departments;

public class Gongik extends Department {
    @Override
    public String getLabel() {
        return "사회복무요원";
    }

    @Override
    public int getWorkingMonths() {
        return 24;
    }
}
