package rahul.example.facebookpost;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    
    private Button btn_post;
    private MyFbPost mfb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn_post = (Button)findViewById(R.id.btnfb);
        
        mfb = new MyFbPost(MainActivity.this);
        
        btn_post.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                
                mfb.postOnFb();
                
            }
        });
        
    }

      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
