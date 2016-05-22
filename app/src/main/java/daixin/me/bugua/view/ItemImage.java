package daixin.me.bugua.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by shidai on 2016/5/4.
 */
public class ItemImage extends ImageView {
    private int originalWidth;
    private int originalHeight;

    public ItemImage(Context context) {
        super(context);
    }

    public ItemImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth > 0 && originalHeight > 0) {
            float ratio = (float) originalWidth / (float)originalHeight ;

            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);

            if (width > 0) {
                height = (int) ((float) width / ratio);
            }
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
