package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();

    private float timer = 0.f;
    private Bitmap bmp;
    float offset = 0.f;
    private Vector2 initpos = new Vector2(0,0);
    String score = null;
    String heart = null;
    Paint textPaint;

    private SampleGame()
    {

    }

    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        SampleBackground2.Create(30, 50, initpos);
        SampleBackground.Create(2100, 50, initpos);
        Player.Create(_view.getWidth() * 0.5f + 450,830 , initpos);

        JumpButton.Create(185 , 970, initpos);

        ShootButton.Create(1640 , 970, initpos);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.rgb(255, 0, 0));
        textPaint.setTextSize(30);
    }

    public void Update(float _deltaTime)
    {
      /*  timer += _deltaTime;
        if (timer > 1.0f)
        {
            SampleEntity.Create();

            timer = 0.f;
        }
//Setplayerx(getplayerx += 1);
        EntityManager.Instance.Update(_deltaTime);*/
    //  float movingx = EntityManager.Instance.getplayerX();

      //  EntityManager.Instance.setplayerX(movingx += 1);

        EntityManager.Instance.Update(_deltaTime);
        score = Integer.toString(playerInfo.getInstance().Score);
        heart = Integer.toString(playerInfo.getInstance().Hearts);
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
        _canvas.drawText("Score : ", 100 ,100, textPaint);
        _canvas.drawText(score, 280, 100, textPaint);
        _canvas.drawText("Health : ", 100 ,200, textPaint);
        _canvas.drawText(heart, 280, 200, textPaint);
    }
}

