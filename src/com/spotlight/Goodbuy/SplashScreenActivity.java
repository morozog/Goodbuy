/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spotlight.Goodbuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
/**
 *
 * @author Mafioso
 */
public class SplashScreenActivity extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 5000;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        
        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(new Intent("com.spotlight.Goodbuy.GoodbuyActivity"));
                    stop();
                }
            }
        };
        splashTread.start();    
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}
