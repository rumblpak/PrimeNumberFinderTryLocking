package fau.rheyser.primenumber;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class MainMenu extends Activity implements OnClickListener {
	
	private static final String TAG = "Primes";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Buttons
        View startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
    
    /** Ask the user what difficulty level they want */
    private void openStartDialog() {
       new AlertDialog.Builder(this)
            .setTitle(R.string.start_title)
            .setItems(R.array.threads,
             new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialoginterface,
                      int i) {
                   start(i);
                }
             })
            .show();
    }
    
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.start_button:
			openStartDialog();
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}
	
	private void start(int i) {
	      Log.d(TAG, "number of threads: " + (i+1));
	      Intent intent = new Intent(this, Run.class);
	      intent.putExtra(Run.numberthreads, i);
	      startActivity(intent);
	   }
}