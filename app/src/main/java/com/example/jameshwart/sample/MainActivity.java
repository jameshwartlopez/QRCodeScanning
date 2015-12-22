package com.example.jameshwart.sample;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends ActionBarActivity {

    TextView tvScannedResult;
    Button btnStartScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScannedResult = (TextView) findViewById(R.id.lblScannedResult);

        btnStartScan = (Button) findViewById(R.id.btnStartScan);
        btnStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initiate barcode scannning.
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
       //This will be the receiver of the qr code
       // You can also try generate sample qr code here http://www.qr-code-generator.com/ and try scanning qr code
        IntentResult scannedResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scannedResult != null){
            String re = scannedResult.getContents();

            if(re != null){
                Log.e("Scanned code:>",re);
                tvScannedResult.setText(re);
            }
        }

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
