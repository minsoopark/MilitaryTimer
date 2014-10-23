package ms.park.military.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import ms.park.military.R;
import ms.park.military.adapters.DepartmentPagerAdapter;
import ms.park.military.models.Human;
import ms.park.military.models.departments.Army;
import ms.park.military.models.departments.Department;
import ms.park.military.preferences.MilitaryPreference;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends ActionBarActivity {

    private EditText etName;
    private NumberPicker npInYear;
    private NumberPicker npInMonth;
    private NumberPicker npInDay;
    private ViewPager vpDepartment;
    private Button btnPrev;
    private Button btnNext;

    private int year = 2014;
    private int month = 10;
    private int day = 20;

    private Department department = new Army();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle(
                Html.fromHtml(
                        String.format(
                                "<font color=\"#ffffff\">%s</font>",
                                getString(R.string.title_activity_add)
                        )
                )
        );

        etName = (EditText) findViewById(R.id.et_name);
        npInYear = (NumberPicker) findViewById(R.id.np_in_year);
        npInMonth = (NumberPicker) findViewById(R.id.np_in_month);
        npInDay = (NumberPicker) findViewById(R.id.np_in_day);
        vpDepartment = (ViewPager) findViewById(R.id.vp_department);
        btnPrev = (Button) findViewById(R.id.btn_prev);
        btnNext = (Button) findViewById(R.id.btn_next);

        initView();
        initEvent();
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        npInYear.setMinValue(0);
        npInMonth.setMinValue(1);
        npInDay.setMinValue(1);

        npInYear.setMaxValue(9999);
        npInMonth.setMaxValue(12);
        npInDay.setMaxValue(31);

        npInYear.setValue(calendar.get(Calendar.YEAR));
        npInMonth.setValue(calendar.get(Calendar.MONTH) + 1);
        npInDay.setValue(calendar.get(Calendar.DAY_OF_MONTH));

        vpDepartment.setAdapter(new DepartmentPagerAdapter(this));
        vpDepartment.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                float normalizedPosition = Math.abs(Math.abs(position) - 1);
                view.setAlpha(normalizedPosition);
            }
        });
    }

    private void initEvent() {
        NumberPicker.OnValueChangeListener listener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                year = npInYear.getValue();
                month = npInMonth.getValue();
                day = npInDay.getValue();
            }
        };

        npInYear.setOnValueChangedListener(listener);
        npInMonth.setOnValueChangedListener(listener);
        npInDay.setOnValueChangedListener(listener);

        vpDepartment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                department = Department.DEPARTMENTS[i];
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpDepartment.setCurrentItem(
                        Math.max(
                                vpDepartment.getCurrentItem() - 1,
                                0
                        )
                );
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpDepartment.setCurrentItem(
                        Math.min(
                                vpDepartment.getCurrentItem() + 1,
                                vpDepartment.getAdapter().getCount()
                        )
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Human human = new Human(
                    etName.getText().toString(),
                    new Date(year - 1900, month - 1, day, 0, 0),
                    department
            );
            MilitaryPreference.addHuman(this, human);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
