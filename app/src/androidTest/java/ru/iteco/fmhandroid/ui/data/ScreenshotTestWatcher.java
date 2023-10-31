package ru.iteco.fmhandroid.ui.data;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import androidx.test.runner.screenshot.Screenshot;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

public class ScreenshotTestWatcher extends TestWatcher {
    private static Activity currentActivity;

    @Override
    protected void failed(Throwable e, Description description)
    {
        Bitmap bitmap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
        {
            bitmap = getInstrumentation().getUiAutomation().takeScreenshot();
        }
        else
        {
            // only in-app view-elements are visible.
            bitmap = Screenshot.capture(getCurrentActivity()).getBitmap();
        }

        // Save to external storage '/storage/emulated/0/Android/data/[package name app]/cache/screenshots/'.
        File folder = new File(InstrumentationRegistry.getInstrumentation().getTargetContext().getExternalCacheDir().getAbsolutePath() + "/screenshots/");
        if (!folder.exists())
        {
            folder.mkdirs();
        }

        storeBitmap(bitmap, folder.getPath() + "/" + getFileName(description));
    }

    private String getFileName(Description description)
    {
        String className = description.getClassName();
        String methodName = description.getMethodName();
        String dateTime = Calendar.getInstance().getTime().toString();

        return className + "-" + methodName + "-" + dateTime + ".png";
    }

    private void storeBitmap(Bitmap bitmap, String path)
    {
        BufferedOutputStream out = null;
        try
        {
            out = new BufferedOutputStream(new FileOutputStream(path));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Activity getCurrentActivity()
    {
        getInstrumentation().runOnMainSync(new Runnable()
        {
            public void run()
            {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext())
                {
                    currentActivity = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }
}
