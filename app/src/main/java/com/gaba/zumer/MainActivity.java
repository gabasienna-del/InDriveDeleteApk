package com.gaba.zumer;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Логируем любые непойманные исключения в файл
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            try {
                File f = new File(getExternalFilesDir(null), "crash.txt");
                PrintWriter pw = new PrintWriter(new FileWriter(f, true));
                pw.println("==== Crash ====");
                e.printStackTrace(pw);
                pw.close();
            } catch (Exception ignore) {}
            System.exit(1);
        });

        setContentView(R.layout.activity_main);
    }
}
