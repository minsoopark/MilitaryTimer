package ms.park.military.models;

import ms.park.military.models.departments.Department;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Human implements Serializable {

    private String name;
    private Date inDate;
    private Date outDate;
    private Department department;

    public Human(String name) {
        this.name = name;
    }

    public Human(String name, Date inDate, Department department) {
        this.name = name;
        this.inDate = inDate;
        this.department = department;
        initOutDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInDate() {
        return inDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setInDate(Date inDate, Department department) {
        this.inDate = inDate;
        this.department = department;
        initOutDate();
    }

    public Date getOutDate() {
        return this.outDate;
    }

    private void initOutDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inDate);
        calendar.add(Calendar.MONTH, department.getWorkingMonths());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        this.outDate = calendar.getTime();
    }
}
