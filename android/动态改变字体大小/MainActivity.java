package com.vgaw.androidtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// TODO: 2015-12-30 always get one full line and one extra word in the second line who should at the end of first line
public class MainActivity extends AppCompatActivity {

    private static final float MIN_TEXT_SIZE = 24.0f;
    private static final float PRECISION = 10f;
    private static final float MAX_TEXT_SIZE = 44.0f;
    private static float min;
    private static float max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final EditText et_description = (EditText) findViewById(R.id.et_description);
        et_description.setTextSize(TypedValue.COMPLEX_UNIT_SP, MAX_TEXT_SIZE);
        min = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, MIN_TEXT_SIZE, et_description.getResources().getDisplayMetrics());
        max = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, MAX_TEXT_SIZE, et_description.getResources().getDisplayMetrics());

        et_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (before < count) {
                    // add word
                    autoFit(et_description, true);
                } else {
                    // delete word
                    autoFit(et_description, false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Re-sizes the textSize of the TextView so that the text fits within the bounds of the View.
     */
    private void autoFit(TextView view, boolean isAdd) {
        float oldTextSize = view.getTextSize();

        CharSequence text = view.getText();

        int targetWidth = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();

        TextPaint paint = view.getPaint();

        float nowTextSize = getAutoFitTextSize(text, paint, targetWidth, oldTextSize, isAdd, false);

        if (nowTextSize != oldTextSize) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, nowTextSize);
            Toast.makeText(MainActivity.this, String.valueOf(view.getTextSize()), Toast.LENGTH_SHORT).show();
        }
    }

    private float getAutoFitTextSize(CharSequence text, TextPaint paint,
                                     int targetWidth, float nowTextSize, boolean isAdd, boolean isTrue) {
        if (nowTextSize < min) {
            return min;
        }
        if (nowTextSize > max) {
            return max;
        }
        paint.setTextSize(nowTextSize);
        StaticLayout layout = new StaticLayout(text, paint, targetWidth, Layout.Alignment.ALIGN_NORMAL,
                1.0f, 0.0f, true);
        int lineCount = layout.getLineCount();
        if (isAdd) {
            if (lineCount > 1) {
                nowTextSize -= PRECISION;
                return getAutoFitTextSize(text, paint, targetWidth, nowTextSize, isAdd, isTrue);
            }
            return nowTextSize;
        } else {
            if (lineCount == 1) {
                isTrue = true;
                nowTextSize += PRECISION;
                return getAutoFitTextSize(text, paint, targetWidth, nowTextSize, isAdd, isTrue);
            }
            if (isTrue){
                return nowTextSize - PRECISION;
            }else {
                return nowTextSize;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   /* */

    /**
     * Recursive binary search to find the best size for the text.
     *//*
    private static float getAutofitTextSize(CharSequence text, TextPaint paint,
                                            float targetWidth, int maxLines, float low, float high, float precision,
                                            DisplayMetrics displayMetrics) {
        float mid = (low + high) / 2.0f;
        int lineCount = 1;
        StaticLayout layout = null;

        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mid,
                displayMetrics));

        if (maxLines != 1) {
            layout = new StaticLayout(text, paint, (int)targetWidth, Layout.Alignment.ALIGN_NORMAL,
                    1.0f, 0.0f, true);
            lineCount = layout.getLineCount();
        }

        if (lineCount > maxLines) {
            // For the case that `text` has more newline characters than `maxLines`.
            if ((high - low) < precision) {
                return low;
            }
            return getAutofitTextSize(text, paint, targetWidth, maxLines, low, mid, precision,
                    displayMetrics);
        }
        else if (lineCount < maxLines) {
            return getAutofitTextSize(text, paint, targetWidth, maxLines, mid, high, precision,
                    displayMetrics);
        }
        else {
            float maxLineWidth = 0;
            if (maxLines == 1) {
                maxLineWidth = paint.measureText(text, 0, text.length());
            } else {
                for (int i = 0; i < lineCount; i++) {
                    if (layout.getLineWidth(i) > maxLineWidth) {
                        maxLineWidth = layout.getLineWidth(i);
                    }
                }
            }

            if ((high - low) < precision) {
                return low;
            } else if (maxLineWidth > targetWidth) {
                return getAutofitTextSize(text, paint, targetWidth, maxLines, low, mid, precision,
                        displayMetrics);
            } else if (maxLineWidth < targetWidth) {
                return getAutofitTextSize(text, paint, targetWidth, maxLines, mid, high, precision,
                        displayMetrics);
            } else {
                return mid;
            }
        }
    }*/
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
