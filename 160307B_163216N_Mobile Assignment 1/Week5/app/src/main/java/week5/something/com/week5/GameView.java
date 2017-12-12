package week5.something.com.week5;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{
    private SurfaceHolder holder = null;
    private UpdateThread updateThread = new UpdateThread(this);

    public GameView(Context _context)
    {
        //Do what the base surface view will do to setup
        super(_context);
        holder = getHolder();

        if (holder != null)
        {
            holder.addCallback(new SurfaceHolder.Callback()
            {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    //INIT
                    if (!updateThread.IsRunning())
                        updateThread.Initialize();

                    if (!updateThread.isAlive())
                        updateThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    updateThread.Terminate();
                }
            });
            {

            }
        }
    }
}

