package week5.something.com.week5;

public interface Collidabale
{
    String GetType();
    float GetPosX();
    float GetPosY();

    void SetPosX(float _posx);
    void SetPosY(float _posy);

    float Radius();
    void SetDir(Vector2 dir);
    Vector2 GetDir();
    void SetViewDir(Vector2 dir);
    Vector2 GetViewDir();
    void OnHit(Collidabale _other);
}
