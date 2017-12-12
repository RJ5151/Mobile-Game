package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class AI implements EntityBase, Collidabale
{
    private Bitmap bmp ;
    private boolean b_IsDone = false;

    private Vector2 Pos = new Vector2(0,0);
    private Vector2 Dir = new Vector2(0,0);
    private Vector2 OriginPos = new Vector2(0,0);
    private Vector2 ViewDir = new Vector2(0,0);
    private short animationIndex;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.enemy);
        bmp =  Bitmap.createScaledBitmap(bmp, 244, 130, true);
        Random ranGen = new Random();

        Dir.Set(dir);
        Pos.x = ranGen.nextFloat() * 580.0f + 1200.0f;
        Pos.y = ranGen.nextFloat() * 1078.0f;
        while(Pos.x == Dir.x)
        {
            Pos.x = ranGen.nextFloat() * 100.0f - 50.f;
        }
        while(Pos.y == Dir.y)
        {
            Pos.x = ranGen.nextFloat() * 100.0f - 50.f;
        }
        OriginPos.x = Dir.x - Pos.x;
        OriginPos.y = Dir.y - Pos.y;
        Dir.Set((OriginPos).Normalize());
        OriginPos.Set(x,y);
    }

    @Override
    public void Update(float _dt) {



        Pos.x += Dir.x * _dt * 100;
        Pos.y += Dir.y * _dt * 100;


        animationIndex  ++;

        animationIndex  %= 4;
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, Pos.x - bmp.getWidth() * 0.5f, Pos.y - bmp.getHeight() * 0.5f, null);
    }

    public static AI Create(float x, float y, Vector2 dir)
    {

        AI result = new AI();
        EntityManager.Instance.AddEntity(result, x ,y,dir);
        return result;
    }

    @Override
    public String GetType() {
        return "AI";
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
        if (_other.GetType().equals("Projectile") ||_other.GetType().equals("Player") )
        {
            SetIsDone(true);
        }
    }
}
