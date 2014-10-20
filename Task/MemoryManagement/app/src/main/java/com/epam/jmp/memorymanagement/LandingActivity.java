package com.epam.jmp.memorymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends Activity implements View.OnClickListener {

    private static final int BUTTON_SMALL = R.id.small;
    private static final int BUTTON_NORMAL = R.id.normal;
    private static final int BUTTON_LARGE = R.id.large;
    private static final int BUTTON_XLARGE = R.id.xlarge;

    private static final String FORMAT_FILE = "%s_%d.jpg";

    private static final String PREFIX_SMALL = "small";
    private static final String PREFIX_NORMAL = "normal";
    private static final String PREFIX_LARGE = "large";
    private static final String PREFIX_XLARGE = "xlarge";

    private String[] getFiles(int buttonId) {
        List<String> list=new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add(String.format(FORMAT_FILE, getScreenType(buttonId), i));
        }
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        return list.toArray(new String[list.size()]);
    }

    private String getScreenType(int buttonId) {
        if (buttonId == BUTTON_SMALL) return PREFIX_SMALL;
        if (buttonId == BUTTON_NORMAL) return PREFIX_NORMAL;
        if (buttonId == BUTTON_LARGE) return PREFIX_LARGE;
        if (buttonId == BUTTON_XLARGE) return PREFIX_XLARGE;
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
