package com.epam.jmp.classloader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class HomeActivity extends Activity {
    private static final String MODULE1_FILE = "module1.apk";
    private static final String MODULE1_NAME_CLASS = "com.epam.jmp.classloading.module1.MyModule";

    private static final String MODULE2_FILE = "module2.apk";
    private static final String MODULE2_NAME_CLASS = "com.epam.jmp.classloading.module2.MyModule";

    private static final String NAME_METHOD = "getMessage";

    enum Module {
        MODULE1(MODULE1_FILE, MODULE1_NAME_CLASS, NAME_METHOD),
        MODULE2(MODULE2_FILE, MODULE2_NAME_CLASS, NAME_METHOD);

        private final String nameFile;
        private final String nameClass;
        private final String nameMethod;

        Module(String nameFile, String nameClass, String nameMethod) {
            this.nameFile = nameFile;
            this.nameClass = nameClass;
            this.nameMethod = nameMethod;
        }

        public String getNameClass() {
            return nameClass;
        }

        public String getNameFile() {
            return nameFile;
        }

        public String getNameMethod() {
            return nameMethod;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = v.getId();
                if (id == R.id.button1) {
                    Toast.makeText(getApplicationContext(), getMessage(Module.MODULE1), Toast.LENGTH_LONG).show();
                } else if (id == R.id.button2) {
                    Toast.makeText(getApplicationContext(), getMessage(Module.MODULE2), Toast.LENGTH_LONG).show();
                }
            }
        };
        findViewById(R.id.button1).setOnClickListener(listener);
        findViewById(R.id.button2).setOnClickListener(listener);
    }

    private String getMessage(Module module) {
        String msg = "";
        try {
            final String libPath = Environment.getExternalStorageDirectory() + "/" + module.getNameFile();
            final File tmpDir = getDir("dex", 0);
            final DexClassLoader classLoader = new DexClassLoader(libPath, tmpDir.getAbsolutePath(), null, this.getClass().getClassLoader());
            final Class<Object> classToLoad = (Class<Object>) classLoader.loadClass(module.getNameClass());
            final Object myInstance = classToLoad.newInstance();
            final Method doSomething = classToLoad.getMethod(module.getNameMethod());
            msg = (String) doSomething.invoke(myInstance);
        } catch (Exception e) {
            msg = "ERROR: " + e.getMessage();
            Log.e("ClassLoader", msg);
        } finally {
            return msg;
        }
    }

}
