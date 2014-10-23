package ms.park.military.models.departments;

public class HyByungteuk extends Byungteuk {
    @Override
    public String getLabel() {
        return "현역 산업기능요원";
    }

    @Override
    public int getWorkingMonths() {
        return 34;
    }
}
