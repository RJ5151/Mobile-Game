package week5.something.com.week5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;


public class CreditPage extends Activity implements OnClickListener {

    //Define Button as an object
    private Button btn_back;

    @Override // Annotation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //This is using layouts! Not want
        setContentView(R.layout.creditpage);
        //setContentView(new GameView(this));

        // Set Listener to button
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

    }

    // Invokde a callback on clicked event on a view
    public void onClick(View v){
        Intent intent = new Intent();

        if (v == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
        }

        startActivity(intent);
    }
}

