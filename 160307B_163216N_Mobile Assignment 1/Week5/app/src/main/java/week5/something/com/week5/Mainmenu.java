package week5.something.com.week5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;


public class Mainmenu extends Activity implements OnClickListener {

    //Define Button as an object
    private Button btn_start;
    private Button btn_credits;
    private boolean mIsBound = false;
    public AudioPlayer mServ;
  public Intent music;

    //Connect to audio
    //connect to musicservice
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((AudioPlayer.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };
    //bind service to activity
    void doBindService(){
        bindService(new Intent(this,AudioPlayer.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    protected void onStop()
    {
        super.onStop();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        doUnbindService();
        stopService(music);
    }

    @Override // Annotation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //This is using layouts! Not want
        setContentView(R.layout.mainmenu);
        //setContentView(new GameView(this));

        // Set Listener to button
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_credits = (Button)findViewById(R.id.btn_credits);
        btn_credits.setOnClickListener(this);

        doBindService();

        //start music service using intent
        music = new Intent();
        music.setClass(this,AudioPlayer.class);
        music.putExtra("songName", "audio1");    //first parameter is string key, second parameter is the name of the mp3 file
        startService(music);
    }

    // Invokde a callback on clicked event on a view
    public void onClick(View v){
        Intent intent = new Intent();

        if (v == btn_credits)
        {
            intent.setClass(this, CreditPage.class);
        }
        if (v == btn_start)
        {
            intent.setClass(this, GamePage.class);
        }


        startActivity(intent);
    }
}



