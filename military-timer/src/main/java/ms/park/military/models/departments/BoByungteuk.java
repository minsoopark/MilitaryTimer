package ms.park.military.models.departments;

public class BoByungteuk extends Byungteuk {
    @Override
    public String getLabel() {
        return "보충역 산업기능요원";
    }

    @Override
    public int getWorkingMonths() {
        return 26;
    }
}
