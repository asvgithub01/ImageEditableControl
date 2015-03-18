package com.gigigo.asv.imageedit;

import android.app.Activity;
import android.os.Bundle;

import com.gigigo.asv.imageedit.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {

    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_fullscreen);
        setContentView(R.layout.activity_fullscreen);

       // this.addContentView(new EditableImageView(this),new ViewGroup.LayoutParams());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
     //   delayedHide(100);
    }



}
