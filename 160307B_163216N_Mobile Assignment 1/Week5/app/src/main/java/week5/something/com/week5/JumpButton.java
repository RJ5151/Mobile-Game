package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class JumpButton implements EntityBase, Collidabale
{
    private Bitmap [ ] bmp = new Bitmap [2];
    private boolean b_IsDone = false;
    private Vector2 Pos = new Vector2(0,0);
    private Vector2 Dir = new Vector2(0,0);
    private Vector2 OriginPos = new Vector2(0,0);
    private boolean b_isJump,tojump;
    private Vector2 ViewDir = new Vector2(0,0);
    private Paint jumpbuttonpaint;
    private short buttonindex;
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
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jump);
        bmp[0] =  Bitmap.createScaledBitmap(bmp[0], 200, 200, true);
        bmp[1] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.jumppressed);
        bmp[1] =  Bitmap.createScaledBitmap(bmp[1], 200, 200, true);
        Pos.Set(x,y);
        Dir.Set(dir);
        OriginPos.Set(x,y);
        b_isJump = false;
        buttonindex = 0;
    }

    @Override
    public void Update(float _dt) {

        if (TouchManager.Instance.isDown())
        {
            float imgRadius = bmp[buttonindex].getHeight() * 0.5f;

            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, Pos.x, Pos.y, imgRadius))
            {

                    b_isJump = tojump = true;
                    buttonindex = 1;
            }


        }
        else {
            buttonindex = 0;
        }
        if(tojump==true ) {
            if (b_isJump == true) {
                Dir.Set(0, -1);
                Dir.Normalize();
                if (EntityManager.Instance.getplayerY() <= 500) {
                    b_isJump=false;
                }
            } else if (b_isJump == false) {
                Dir.Set(0, 1);
                Dir.Normalize();
                if (EntityManager.Instance.getplayerY() >= 830) {
                    tojump = false;
                    Dir.Set(0, 0);
                }
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp[buttonindex], Pos.x - bmp[buttonindex].getWidth() * 0.5f, Pos.y - bmp[buttonindex].getHeight() * 0.5f, null);
    }

    public static JumpButton Create(float x, float y, Vector2 dir)
    {

        JumpButton result = new JumpButton();
        EntityManager.Instance.AddEntity(result, x ,y,dir);
        return result;
    }

    @Override
    public String GetType() {
        return "JumpButton";
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
    public Vector2 GetDir() { return Dir;}

    @Override
    public void SetViewDir(Vector2 dir) {
        ViewDir.Set(dir);
    }

    @Override
    public Vector2 GetViewDir() {
        return ViewDir;
    }


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
    public void OnHit(Collidabale _other) {
        if (_other.GetType().equals("JumpButton"))
        {
            SetIsDone(true);
        }
    }
}
