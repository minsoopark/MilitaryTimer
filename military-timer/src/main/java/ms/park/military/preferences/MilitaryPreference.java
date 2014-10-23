package ms.park.military.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import ms.park.military.models.Human;
import ms.park.military.utils.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MilitaryPreference {
    public static void addHuman(Context context, Human human) {
        SharedPreferences pref = context.getSharedPreferences("humans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        List<Human> prevHumans;
        try {
            String humansStr = pref.getString("humans_str", null);
            prevHumans = (List<Human>) ObjectSerializer.deserialize(humansStr);
            prevHumans = prevHumans == null ? new ArrayList<Human>() : prevHumans;
            prevHumans.add(human);

            editor.putString("humans_str", ObjectSerializer.serialize(new ArrayList<Human>(prevHumans)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();
    }

    public static void deleteHuman(Context context, int index) {
        SharedPreferences pref = context.getSharedPreferences("humans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        List<Human> prevHumans;
        try {
            String humansStr = pref.getString("humans_str", null);
            prevHumans = (List<Human>) ObjectSerializer.deserialize(humansStr);
            prevHumans = prevHumans == null ? new ArrayList<Human>() : prevHumans;
            prevHumans.remove(index);

            editor.putString("humans_str", ObjectSerializer.serialize(new ArrayList<Human>(prevHumans)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.commit();
    }
    
    public static List<Human> getHumans(Context context) {
        SharedPreferences pref = context.getSharedPreferences("humans", Context.MODE_PRIVATE);
        
        List<Human> humans = new ArrayList<Human>();
        try {
            String humansStr = pref.getString("humans_str", null);
            humans = (List<Human>) ObjectSerializer.deserialize(humansStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return humans == null ? new ArrayList<Human>() : humans;
    }
}
