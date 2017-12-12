package week5.something.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class Player implements EntityBase, Collidabale
{
    private Bitmap [ ] bmp = new Bitmap [1];
    private boolean b_IsDone = false;

    private Vector2 Pos = new Vector2(0,0);
    private Vector2 Dir = new Vector2(0,0);
    private Vector2 OriginPos = new Vector2(0,0);
    private Vector2 ViewDir = new Vector2(0,0);

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
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ship2_1);
        bmp[0] =  Bitmap.createScaledBitmap(bmp[0], 244, 130, true);

        Pos.Set(x,y);
        Dir.Set(dir);
        OriginPos.Set(x,y);
    }

    @Override
    public void Update(float _dt) {
        Pos.x += Dir.x * _dt * 500;
        Pos.y += Dir.y * _dt * 500;





    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp[0], Pos.x - bmp[0].getWidth() * 0.5f, Pos.y - bmp[0].getHeight() * 0.5f, null);
    }

    public static Player Create(float x, float y, Vector2 dir)
    {

        Player result = new Player();
        EntityManager.Instance.AddEntity(result, x ,y,dir);
        return result;
    }

    @Override
    public String GetType() {
        return "Player";
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
    public void SetPosX(float _posx){
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
        return bmp[0].getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidabale _other) {
        if (_other.GetType().equals("Player"))
        {
            SetIsDone(true);
        }
    }
}
