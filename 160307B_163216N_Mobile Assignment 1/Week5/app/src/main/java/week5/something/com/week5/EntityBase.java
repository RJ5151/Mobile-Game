package week5.something.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

public interface EntityBase
{
    boolean IsDone();
    void SetIsDone(boolean _IsDone);

    void Init(SurfaceView _view,float x, float y,Vector2 dir);
    void Update(float _dt);
    void Render(Canvas _canvas);
}
