package com.epam.jmp.garbagecollection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class LandingActivity extends Activity implements View.OnClickListener {

    private static final int BUTTON_SMALL = R.id.small;
    private static final int BUTTON_NORMAL = R.id.normal;
    private static final int BUTTON_LARGE = R.id.large;
    private static final int BUTTON_XLARGE = R.id.xlarge;

    private static final String FORMAT_FILE = "%s_%d.jpg";

    private static final int LENGTH_DATA = 5;

    private static final String PREFIX_SMALL = "small";
    private static final String PREFIX_NORMAL = "normal";
    private static final String PREFIX_LARGE = "large";
    private static final String PREFIX_XLARGE = "xlarge";

    private String[] getFiles(int id) {
        final String[] files = new String[LENGTH_DATA];
        for (int i = 0; i < files.length; i++) {
            files[i] = String.format(FORMAT_FILE, getScreenType(id), i);
        }
        return files;
    }

    private String getScreenType(int id) {
        if (id == BUTTON_SMALL) return PREFIX_SMALL;
        if (id == BUTTON_NORMAL) return PREFIX_NORMAL;
        if (id == BUTTON_LARGE) return PREFIX_LARGE;
        if (id == BUTTON_XLARGE) return PREFIX_XLARGE;
        throw new RuntimeException("Undefined screen type");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        findViewById(BUTTON_SMALL).setOnClickListener(this);
        findViewById(BUTTON_NORMAL).setOnClickListener(this);
        findViewById(BUTTON_LARGE).setOnClickListener(this);
        findViewById(BUTTON_XLARGE).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        final Intent intent = new Intent(this, SampleActivity.class);
        intent.putExtra(SampleActivity.EXTRA_FILES, getFiles(id));
        intent.putExtra(SampleActivity.EXTRA_IS_OPTIMIZED, ((CheckBox) findViewById(R.id.optimize)).isChecked());
        startActivity(intent);
    }

}
