package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class SampleBackground implements EntityBase
{

    private Bitmap bmp = null;
    private boolean b_IsDone = false;
    private Paint backgroundPaint;
    private float xPos, yPos, offset;
    private SurfaceView view = null;

    @Override
    public boolean IsDone() {
        return b_IsDone;
    }

    @Override
    public void SetIsDone(boolean _IsDone) {
        b_IsDone = _IsDone;
    }

    @Override
    public void Init(SurfaceView _view, float x, float y, Vector2 dir) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.backgnd);
        view = _view;
        offset = 0.f;
        xPos = x;
        yPos = 0.5f * view.getHeight();
    }


    @Override
    public void Update(float _dt) {
        xPos  -= _dt * 70;

        if (xPos < - view.getWidth()-300.f)

        {

            xPos = 2100;

        }
    }

    @Override
    public void Render(Canvas _canvas) {


        float xOffset = (float)Math.sin(offset) * bmp.getWidth() * 0.3f;
        _canvas.drawBitmap(bmp, xPos , yPos  , null);
    }

    public static SampleBackground Create(float xPos, float yPos, Vector2 dir)
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result, xPos, yPos, dir);

        return result;
    }
}
