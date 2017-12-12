package week5.something.com.week5;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private SurfaceView view = null;
    private float lifetimespawn = 0.3f;
    private float lifetimeproj = 1.f;
    private LinkedList<EntityBase> m_EntityList = new LinkedList<EntityBase>();

    private EntityManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<>();
        for (EntityBase currEntity : m_EntityList)
        {
            currEntity.Update(_dt);

            if (currEntity.IsDone())
            {
                removalList.add(currEntity);
            }
        }

        for (int i = 0; i < m_EntityList.size(); ++i)
        {
            EntityBase currEntity = m_EntityList.get(i);

            if (currEntity instanceof  Collidabale)
            {
                //Converting currentEntity into a collidable
                Collidabale first = (Collidabale)currEntity;
                if(((Collidabale) currEntity).GetType() == "Player")
                {
                    lifetimespawn -= _dt;
                    if(lifetimespawn < 0)
                    {
                        Vector2 Playerpos = new Vector2(((Collidabale) currEntity).GetPosX(),((Collidabale) currEntity).GetPosY());
                        AI.Create(0,0, Playerpos);
                        lifetimespawn = 4.f;

                        Vector2 PlatformDir = new Vector2(-1,0);
                        Platform.Create(0,0,PlatformDir);
                    }
                }
                for (int j = i + 1; j < m_EntityList.size(); ++j)
                {
                    EntityBase  otherEntity = m_EntityList.get(j);
                    if(((Collidabale) currEntity).GetType() == "Player" && ((Collidabale) otherEntity).GetType() == "JumpButton")
                    {
                        ((Collidabale) currEntity).SetDir(((Collidabale) otherEntity).GetDir());
                    }
                    if(((Collidabale) currEntity).GetType() == "Player" && ((Collidabale) otherEntity).GetType() == "AI")
                    {
                        Vector2 hello = new Vector2(((Collidabale) currEntity).GetPosX() - ((Collidabale) otherEntity).GetPosX(),((Collidabale) currEntity).GetPosY() - ((Collidabale) otherEntity).GetPosY()).Normalize();
                        ((Collidabale) otherEntity).SetDir(hello);
                    }
                    if(((Collidabale) currEntity).GetType() == "Player" && ((Collidabale) otherEntity).GetType() == "ShootButton")
                    {
                        if(((Collidabale) otherEntity).GetDir().x !=0 || ((Collidabale) otherEntity).GetDir().y !=0)
                        {
                            lifetimeproj -= _dt;
                            if(lifetimeproj < 0)
                            {
                                Projectile.Create(((Collidabale) currEntity).GetPosX(),((Collidabale) currEntity).GetPosY(), ((Collidabale) otherEntity).GetDir());
                                lifetimeproj = 0.3f;
                            }
                        }

                    }
                    if (otherEntity instanceof Collidabale)
                    {

                        Collidabale second = (Collidabale)otherEntity;
                        if((((Collidabale) currEntity).GetType() == "Projectile" && ((Collidabale) otherEntity).GetType()=="AI") ||(((Collidabale) currEntity).GetType() == "AI" && ((Collidabale) otherEntity).GetType()=="Projectile") ) {
                            if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.Radius(), second.GetPosX(), second.GetPosY(), second.Radius())) {
                                //If first and second are collidables...
                                first.OnHit(second);
                                second.OnHit(first);
                                playerInfo.getInstance().Score++;
                            }
                        }
                        if((((Collidabale) currEntity).GetType() == "Player" && ((Collidabale) otherEntity).GetType()=="AI") ||(((Collidabale) currEntity).GetType() == "AI" && ((Collidabale) otherEntity).GetType()=="Player") )
                        {
                            if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.Radius(), second.GetPosX(),second.GetPosY(),second.Radius()))
                            {
                                first.OnHit(second);
                                second.OnHit(first);
                                playerInfo.getInstance().Hearts--;
                            }
                        }

                        if((((Collidabale) currEntity).GetType() == "Player" && ((Collidabale) otherEntity).GetType()=="Platform") ||(((Collidabale) currEntity).GetType() == "Platform " && ((Collidabale) otherEntity).GetType()=="Player") )
                        {
                            if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(), first.Radius(), second.GetPosX(),second.GetPosY(),second.Radius()))
                            {
                                first.OnHit(second);
                                second.OnHit(first);
                                playerInfo.getInstance().OnGround = true;
                            }
                        }

                    }

                }

            }

            if (currEntity.IsDone())
                removalList.add(currEntity);
        }

        for (EntityBase currEntity : removalList)
        {
            m_EntityList.remove(currEntity);
        }

        removalList.clear();

    }

    public float getplayerY()
    {
        for (int i = 0; i < m_EntityList.size(); ++i)
        {
            EntityBase currEntity = m_EntityList.get(i);
            if (currEntity instanceof  Collidabale)
            {
                Collidabale first = (Collidabale) currEntity;
                if (((Collidabale) currEntity).GetType() == "Player")
                {
                    return ((Collidabale) currEntity).GetPosY();
                }
            }
        }
        return 0;
    }

    public float getplayerX()
    {
        for (int i = 0; i < m_EntityList.size(); ++i)
        {
            EntityBase currEntity = m_EntityList.get(i);
            if (currEntity instanceof  Collidabale)
            {
                Collidabale first = (Collidabale) currEntity;
                if (((Collidabale) currEntity).GetType() == "Player")
                {
                    return ((Collidabale) currEntity).GetPosX();
                }
            }
        }
        return 0;
    }

    public void setplayerX(float _posx)
    {
 for (int i = 0; i < m_EntityList.size(); ++i)
    {
        EntityBase currEntity = m_EntityList.get(i);
        if (currEntity instanceof  Collidabale)
        {
            Collidabale first = (Collidabale) currEntity;
            if (((Collidabale) currEntity).GetType() == "Player")
            {
                ((Collidabale) currEntity).SetPosX(_posx);
            }
        }
    }

    }

    public void setplayerY(float _posy)
    {
        for (int i = 0; i < m_EntityList.size(); ++i)
        {
            EntityBase currEntity = m_EntityList.get(i);
            if (currEntity instanceof  Collidabale)
            {
                Collidabale first = (Collidabale) currEntity;
                if (((Collidabale) currEntity).GetType() == "Player")
                {
                    ((Collidabale) currEntity).SetPosY(_posy);
                }
            }
        }

    }


    public void Render(Canvas _canvas)
    {
        for (EntityBase currEntity : m_EntityList)
        {
            currEntity.Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity, float x, float y, Vector2 dir)
    {
        _newEntity.Init(view,x,y,dir);
        m_EntityList.add(_newEntity);
    }

    public void Clear()
    {
        m_EntityList.clear();
    }
}

