package week5.something.com.week5;

import java.io.Serializable;
import java.util.ArrayList;

public class playerInfo implements Serializable {

    private static volatile playerInfo aSoleInstance;
    public int Score = 0;
    public int Hearts = 3;
    public boolean OnGround = true;


    private playerInfo(){


        if (aSoleInstance != null){
            throw new RuntimeException("Use getInstance()!");
        }
    }

    public static playerInfo getInstance() {
        if (aSoleInstance == null) {
            synchronized (playerInfo.class) {
                if (aSoleInstance == null) aSoleInstance = new playerInfo();
            }
        }

        return aSoleInstance;
    }

    public void Init()
    {
        Score = 0;
        Hearts = 3;
        OnGround = true;
    }

    protected playerInfo readResolve() {
        return getInstance();
    }
}
