package com.xo.thirdpartyexample;


import com.xoid.xodatainterface.XoDataProvider;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
  private static final String TAG = "MainActivity";
  
  private final Handler handler = new Handler();
  
  private XoDataProvider xoDataProvider = null;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    xoDataProvider = new XoDataProvider(this);
    
    final TextView data2 = (TextView) findViewById(R.id.data2);
    xoDataProvider.requestStudentData(new XoDataProvider.DataCallback() {
      @Override
      public void onDataAvailable(final String data) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            data2.setText(data);
          }
        });
      }
      
      @Override
      public void onError(final String description) {
        handler.post(new Runnable() {
          @Override
          public void run() {
            data2.setText("XoDataProvider error: " + description);
          }
        });
        Log.e(TAG, "XoDataProvider error: " + description);
      }
    });
  }
 
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
