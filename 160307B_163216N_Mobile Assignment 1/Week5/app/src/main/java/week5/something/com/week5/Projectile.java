package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Projectile implements EntityBase, Collidabale
{
    private Bitmap bmp = null;
    private boolean b_IsDone = false;

    private Vector2 Pos = new Vector2(0,0);
    private Vector2 Dir = new Vector2(0,0);
    private Vector2 OriginPos = new Vector2(0,0);
    private Vector2 ViewDir = new Vector2(0,0);
    private float lifeTime;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.bullet);
        bmp =  Bitmap.createScaledBitmap(bmp, 122, 65, true);
        lifeTime = 5.f;

    //    xPos = ranGen.nextFloat() * _view.getWidth();
    //    yPos = ranGen.nextFloat() * _view.getHeight();

    //    Pos.x = ranGen.nextFloat() * 100.0f - 50.f;
    //    Pos.y = ranGen.nextFloat() * 100.0f - 50.f;
        Pos.Set(x,y);
        Dir.Set(dir);
        OriginPos.Set(x,y);
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

        if (lifeTime < 0.0f)
            SetIsDone(true);

        Pos.x += Dir.x * _dt * 200;
        Pos.y += Dir.y * _dt * 200;
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, Pos.x - bmp.getWidth() * 0.5f, Pos.y - bmp.getHeight() * 0.5f, null);
    }

    public static Projectile Create(float x, float y, Vector2 dir)
    {

        Projectile result = new Projectile();
        EntityManager.Instance.AddEntity(result, x ,y, dir);
        return result;
    }

    @Override
    public String GetType() {
        return "Projectile";
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
    public float Radius() {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidabale _other) {
        if (_other.GetType().equals("AI"))
        {
            SetIsDone(true);
        }
    }
}
