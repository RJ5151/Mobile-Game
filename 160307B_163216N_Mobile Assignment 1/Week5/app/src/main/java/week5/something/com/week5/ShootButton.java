package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class ShootButton implements EntityBase, Collidabale
{
    private Bitmap [ ] bmp = new Bitmap [2];
    private boolean b_IsDone = false;

    private Vector2 Pos = new Vector2(0,0);
    private Vector2 OriginPos = new Vector2(0,0);
    private Vector2 Dir = new Vector2(0,0);
    private Vector2 ViewDir = new Vector2(0,0);
    private float lifeTime;
    private short buttonindex;
    private Paint shootbuttonpaint;
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
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.shootpressed);
        bmp[0] =  Bitmap.createScaledBitmap(bmp[0], 200, 200, true);
        bmp[1] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.shoot);
        bmp[1] =  Bitmap.createScaledBitmap(bmp[1], 200, 200, true);
        lifeTime = 10.f;
        buttonindex = 1;
        Random ranGen = new Random();

    //    xPos = ranGen.nextFloat() * _view.getWidth();
    //    yPos = ranGen.nextFloat() * _view.getHeight();

    //    Pos.x = ranGen.nextFloat() * 100.0f - 50.f;
    //    Pos.y = ranGen.nextFloat() * 100.0f - 50.f;
        Pos.Set(x,y);
        OriginPos.Set(x,y);
        Dir.Set(dir);
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

     //   if (lifeTime < 0.0f)
      //      SetIsDone(true);

        //Pos.x += Dir.x * _dt;
        //Pos.y += Dir.y * _dt;

        if (TouchManager.Instance.isDown()||TouchManager.Instance.isMove())
        {
            float imgRadius = bmp[buttonindex].getHeight() *0.5f;

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, Pos.x, Pos.y, imgRadius)) {
                Dir.Set(1, 0);
                Dir.Normalize();
                buttonindex = 0;
            }

        }
        else
        {
            Dir.Set(0,0);
            buttonindex = 1;
        }
        
        Log.i("position", "Pos:" +Pos.x + "  " + Pos.y );

    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp[buttonindex], Pos.x - bmp[buttonindex].getWidth() * 0.5f, Pos.y - bmp[buttonindex].getHeight() * 0.5f, null);
    }

    public static ShootButton Create(float x, float y, Vector2 dir)
    {

        ShootButton result = new ShootButton();
        EntityManager.Instance.AddEntity(result, x ,y,dir);
        return result;
    }

    @Override
    public String GetType() {
        return "ShootButton";
    }

    @Override
    public float GetPosX() {
        return Pos.x;
    }

    @Override
    public float GetPosY() {
        return Pos.y;
    }

    @Override
    public void SetPosX(float _posx) {
        Pos.x = _posx;
    }

    @Override
    public void SetPosY(float _posy) {
        Pos.y = _posy; }



    @Override
    public float Radius() {
        return bmp[buttonindex].getHeight() * 0.5f;
    }

    @Override
    public void SetDir(Vector2 dir) {
        Dir.x = dir.x;
        Dir.y = dir.y;
    }

    @Override
    public Vector2 GetDir() {
        return Dir;
    }

    @Override
    public void SetViewDir(Vector2 dir) {
        ViewDir.Set(dir);
    }

    @Override
    public Vector2 GetViewDir() {
        return ViewDir;
    }

    @Override
    public void OnHit(Collidabale _other) {
        if (_other.GetType().equals("ShootButton"))
        {
            SetIsDone(true);
        }
    }
}
