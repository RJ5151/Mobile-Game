package week5.something.com.week5;


public class Vector2
{
    float x;
    float y;

    public Vector2(float a, float b)
    {
        x = a;
        y = b;
    }

    public Vector2(final Vector2 rhs)
    {
        x = rhs.x;
        y = rhs.y;
    }

    public void Set(float a, float b)
    {
        x = a;
        y = b;
    }

    public void Set(Vector2 temp)
    {
        x = temp.x;
        y = temp.y;
    }

    public void SetZero()
    {
        x = y = 0.0f;
    }

    public boolean isEqual(float a, float b)
    {
        return a - b <= Math.ulp(1.0) && b - a <= Math.ulp(1.0);
    }

    public boolean isZero()
    {
        return isEqual(x, 0.f) && isEqual(y, 0.f) ;
    }

    public void add(final Vector2 vec2)
    {
       x += vec2.x;
       y += vec2.y;

    }

    public void minus(final Vector2 vec2)
    {
        x -= vec2.x;
        y -= vec2.y;

    }
    public void negative()
    {
        x = -x;
        y = -y;
    }

    public void multiplyScalar(float scalar)
    {
        x *= scalar;
        y *= scalar;
    }

    public void multiplyVec(Vector2 vec2)
    {
        x *= vec2.x;
        y *= vec2.y;
    }

    public boolean isEqualTo(Vector2 vec2)
    {
        return isEqual(x, vec2.x) && isEqual(y, vec2.y);
    }

    public boolean notEqualTo(Vector2 vec2)
    {
        return !isEqual(x, vec2.x) || !isEqual(y, vec2.y);
    }

    public void equals(Vector2 vec2)
    {
       x = vec2.x;
       y = vec2.y;
    }

    public float Length()
    {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float lengthSquared()
    {
        return x *x + y * y;
    }

    public float Dot(final Vector2 vec2)
    {
        return x * vec2.x + y * vec2.y;
    }

    public Vector2 Normalized()
    {
        float d = Length();
        if(d <= Math.ulp(1.0) && -d <= Math.ulp(1.0))
           throw new IllegalArgumentException("Argument 'divisor' is 0");
        return new Vector2(x /d, y /d);
    }

    public Vector2 Normalize()
    {
        float d = Length();
        if(d <= Math.ulp(1.0) && -d <= Math.ulp(1.0))
            throw new IllegalArgumentException("Argument 'divisor' is 0");
       x /= d;
       y /= d;

       return this;
    }
}

