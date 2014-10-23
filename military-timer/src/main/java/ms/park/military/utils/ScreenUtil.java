package ms.park.military.utils;

import android.content.Context;

public class ScreenUtil {

    /**
     * dpToPx와 흡사하나 DisplayMetrics.density를 캐시하며, 리턴 값을 int로 cast 해준다.
     *
     * @param dip Device Independent Pixel
     * @return Raster Pixel as int
     */
    public static int toPx(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density);
    }
}
