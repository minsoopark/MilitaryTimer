package ms.park.military.models.departments;

import java.io.Serializable;

public abstract class Department implements Serializable {
    public static final Department[] DEPARTMENTS = {
            new Army(),
            new AirForce(),
            new Navy(),
            new Marine(),
            new Rotc(),
            new Gongik(),
            new HyByungteuk(),
            new BoByungteuk()
    };

    public abstract String getLabel();
    public abstract int getWorkingMonths();
}
