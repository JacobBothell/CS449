package edu.umkc.burrise.countmuchmore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

/* This app uses Android tool bar in AppCompatActivity.
   The tool bar can also be specified as a view element.
   Ref: https://blog.xamarin.com/android-tips-hello-toolbar-goodbye-action-bar/
        https://www.youtube.com/watch?t=49&v=5Be2mJzP-Uw
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    static final private String TAG = "Count Much More";

    private int strike = 0;
    private int ball   = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The following will print to LogCat.
        Log.i(TAG, "Starting onCreate...");
        setContentView(R.layout.activity_main);

        View strikeButton = findViewById(R.id.strike_button);
        // This class implements the onClickListener interface.
        // Passing 'this' to setOnClickListener means the
        //   onClick method in this class will get called
        //   when the button is clicked.
        strikeButton.setOnClickListener(this);

        View ballButton = findViewById(R.id.ball_button);
        ballButton.setOnClickListener(this);

        updateCount();
    }

    private void updateCount() {
        TextView t = (TextView)findViewById(R.id.strike_value);
        t.setText(Integer.toString(strike));
        TextView s = (TextView)findViewById(R.id.ball_value);
        s.setText(Integer.toString(ball));
    }

    private void reset() {
        strike = 0;
        ball = 0;
        updateCount();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strike_button:
                // Start count over if user tries to increment
                //   beyond 3.
                if (strike == 3) {
                    // Builder is an inner class so we have to qualify it
                    // with its outer class: AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //builder.setTitle("Dialog box");
                    builder.setMessage("He's OUT!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strike = 0;
                            ball = 0;
                            // Note, you have to call update count here because.
                            //   the call builder.show() below is non blocking.
                            updateCount();
                        }
                    });
                    builder.show();
                }
                else {
                    strike++;
                }
                break;
            case R.id.ball_button:
                // Start count over if user tries to increment
                //   beyond 4.
                if (ball == 4) {
                    // Builder is an inner class so we have to qualify it
                    // with its outer class: AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    //builder.setTitle("Dialog box");
                    builder.setMessage("Walk Him!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            strike = 0;
                            ball = 0;
                            // Note, you have to call update count here because.
                            //   the call builder.show() below is non blocking.
                            updateCount();
                        }
                    });
                    builder.show();
                }
                else {
                    ball++;
                }
                break;
        }
        updateCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.about_menu:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.reset_menu:
                reset();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
