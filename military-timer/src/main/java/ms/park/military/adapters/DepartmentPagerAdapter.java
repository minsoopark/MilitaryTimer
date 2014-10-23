package ms.park.military.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ms.park.military.R;
import ms.park.military.models.departments.Department;

public class DepartmentPagerAdapter extends PagerAdapter {

    private Context context;

    public DepartmentPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Department.DEPARTMENTS.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        TextView txtDepartment = new TextView(context);
        txtDepartment.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        txtDepartment.setSingleLine(true);
        txtDepartment.setGravity(Gravity.CENTER);
        txtDepartment.setText(Department.DEPARTMENTS[position].getLabel());
        txtDepartment.setTextSize(30);
        txtDepartment.setTextColor(context.getResources().getColor(R.color.primary_color));

        container.addView(txtDepartment);

        return txtDepartment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
