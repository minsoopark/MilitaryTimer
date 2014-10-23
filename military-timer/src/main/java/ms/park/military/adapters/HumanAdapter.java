package ms.park.military.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import ms.park.military.R;
import ms.park.military.models.Human;
import ms.park.military.preferences.MilitaryPreference;

import java.util.Date;
import java.util.List;

public class HumanAdapter extends RecyclerView.Adapter<HumanAdapter.HumanViewHolder> {

    private Context context;
    private List<Human> humans;

    public HumanAdapter(Context context, List<Human> humans) {
        this.context = context;
        this.humans = humans;
    }

    @Override
    public HumanViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_human, viewGroup, false);
        return new HumanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HumanViewHolder humanViewHolder, final int i) {
        final Human human = humans.get(i);

        Date today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);

        Date inDate = human.getInDate();
        Date outDate = human.getOutDate();

        humanViewHolder.txtName.setText(human.getName());
        humanViewHolder.txtDepartment.setText(human.getDepartment().getLabel());
        humanViewHolder.txtInDate.setText(
                String.format(
                        "%d.%02d.%02d", inDate.getYear() + 1900, inDate.getMonth() + 1, inDate.getDate()
                )
        );
        humanViewHolder.txtOutDate.setText(
                String.format(
                        "%d.%02d.%02d", outDate.getYear() + 1900, outDate.getMonth() + 1, outDate.getDate()
                )
        );

        humanViewHolder.sbProgress.setMax((int) (outDate.getTime() / (1000 * 3600 * 24) - inDate.getTime() / (1000 * 3600 * 24)));
        humanViewHolder.sbProgress.setProgress((int) (today.getTime() / (1000 * 3600 * 24) - inDate.getTime() / (1000 * 3600 * 24)));
        humanViewHolder.txtPercent.setText(String.format("%02d%%", humanViewHolder.sbProgress.getProgress() * 100 / humanViewHolder.sbProgress.getMax()));

        humanViewHolder.blanket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        humanViewHolder.blanket.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("삭제").setMessage("삭제하시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MilitaryPreference.deleteHuman(context, i);
                        notifyItemRemoved(i);
                        humans.remove(human);
                        dialog.dismiss();
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return humans.size();
    }

    public class HumanViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtDepartment;
        TextView txtInDate;
        TextView txtOutDate;
        TextView txtPercent;
        SeekBar sbProgress;
        View blanket;

        public HumanViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtDepartment = (TextView) itemView.findViewById(R.id.txt_department);
            txtInDate = (TextView) itemView.findViewById(R.id.txt_in_date);
            txtOutDate = (TextView) itemView.findViewById(R.id.txt_out_date);
            txtPercent = (TextView) itemView.findViewById(R.id.txt_percent);
            sbProgress = (SeekBar) itemView.findViewById(R.id.sb_progress);
            blanket = itemView.findViewById(R.id.blanket);
        }
    }
}
