package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Platform implements EntityBase, Collidabale
{
    private Bitmap[ ] bmp = new Bitmap [1];
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
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.platform);
        bmp[0] =  Bitmap.createScaledBitmap(bmp[0], 244, 130, true);
        animationIndex = 0;
        Random ranGen = new Random();

        Dir.Set(dir);
        Pos.x =  1980.0f;
        Pos.y = ranGen.nextFloat() * 350.0f +350.0f;
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

    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp[animationIndex], Pos.x - bmp[animationIndex].getWidth() * 0.5f, Pos.y - bmp[animationIndex].getHeight() * 0.5f, null);
    }

    public static Platform Create(float x, float y, Vector2 dir)
    {

        Platform result = new Platform();
        EntityManager.Instance.AddEntity(result, x ,y,dir);
        return result;
    }

    @Override
    public String GetType() {
        return "Platform";
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
        return bmp[animationIndex].getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidabale _other) {

    }
}
