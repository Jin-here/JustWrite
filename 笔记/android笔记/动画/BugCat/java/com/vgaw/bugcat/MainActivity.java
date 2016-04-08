package com.vgaw.bugcat;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_anim = (Button) findViewById(R.id.btn_anim);
        btn_anim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //demo1(btn_anim);
        //demo2(btn_anim);
        //demo3(btn_anim);
        //demo4(btn_anim);
        //demo5(btn_anim);
        //demo6(btn_anim);
        //demo7(btn_anim);
        //demo8(btn_anim);
        demo9(btn_anim);
    }

    private void demo1(final View target){
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0.5f);
        /*animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);*/
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                target.setAlpha(value);
                ((Button)target).setText(String.valueOf((int)(value * 100)));
            }
        });
        animator.start();
    }

    private void demo2(final View target){
        final ButtonLook startButtonLook = new ButtonLook("0", Color.GREEN, 1f);
        final ButtonLook endButtonLook = new ButtonLook("100", Color.RED, 0.5f);
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                ButtonLook transition = new ButtonLook((ButtonLook) startValue);
                transition.setText(String.valueOf((int) (fraction * 100)));
                //transition.setBackground(((ButtonLook) startValue).getBackground() + (int)( fraction * (((ButtonLook) endValue).getBackground() - ((ButtonLook) startValue).getBackground())));
                transition.setAlpha(((ButtonLook) startValue).getAlpha() + fraction * (((ButtonLook) endValue).getAlpha() - ((ButtonLook) startValue).getAlpha()));
                return transition;
            }
        }, startButtonLook, endButtonLook);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ButtonLook transition = (ButtonLook) animation.getAnimatedValue();
                ((Button)target).setText(transition.getText());
                target.setAlpha(transition.getAlpha());
                target.setBackgroundColor(transition.getBackground());
            }
        });
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    // TODO: 2015-12-12 调用类android.support.v7.widget.AppCompatButton而不是Button
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void demo3(View target){
        ObjectAnimator animator = ObjectAnimator.ofArgb(target, "textColor", Color.parseColor("#00ff00"), Color.parseColor("#ff0000"));
        animator.setDuration(1000);
        animator.start();
    }

    // 没事找事做
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void demo4(final View target){
        final ButtonLook startButtonLook = new ButtonLook("0", Color.GREEN, 1f);
        final ButtonLook endButtonLook = new ButtonLook("100", Color.RED, 0.5f);
        final ButtonLook buttonLook = new ButtonLook(startButtonLook);
        ObjectAnimator animator = ObjectAnimator.ofObject(buttonLook, "buttonLook", new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                ButtonLook transition = new ButtonLook((ButtonLook) startValue);
                transition.setText(String.valueOf((int) (fraction * 100)));
                // TODO: 2015-12-12 颜色变化并非所想的那样渐变，看来需要ofArgb
                //transition.setBackground(((ButtonLook) startValue).getBackground() + (int)( fraction * (((ButtonLook) endValue).getBackground() - ((ButtonLook) startValue).getBackground())));
                transition.setAlpha(((ButtonLook) startValue).getAlpha() + fraction * (((ButtonLook) endValue).getAlpha() - ((ButtonLook) startValue).getAlpha()));
                return transition;
            }
        }, startButtonLook, endButtonLook);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //target.setAlpha(((ButtonLook)animation.getAnimatedValue()).getAlpha());
                target.setAlpha(buttonLook.getAlpha());
            }
        });
        animator.setDuration(1000);
        animator.start();

    }

    /**
     * 旋转
     * @param target
     */
    private void demo5(View target){
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(target, holder);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }

    private void demo6(View target){
        ObjectAnimator animX = ObjectAnimator.ofFloat(target, "x", 50f);
        animX.setRepeatMode(ValueAnimator.REVERSE);
        animX.setRepeatCount(1);
        ObjectAnimator animY = ObjectAnimator.ofFloat(target, "y", 100f);
        animY.setRepeatMode(ValueAnimator.REVERSE);
        animY.setRepeatCount(1);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();

    }

    private void demo7(View target){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, pvhX, pvhY);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    /**
     * 虽然方便，但缺陷较大，使用局限性大，不能设置多个动画，不能设置Mode
     * @param target
     */
    private void demo8(View target){
        target.animate().xBy(50f).yBy(100f);
    }

    private void demo9(View target){
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.property_animator);
        set.setTarget(target);
        set.start();
    }

    /**
     * Button外观相关的类，提取了部分参数
     */
    class ButtonLook{
        private String text;
        private int background;
        private float alpha;

        public ButtonLook(){}

        public ButtonLook(ButtonLook buttonLook){
            this.text = buttonLook.getText();
            this.background = buttonLook.getBackground();
            this.alpha = buttonLook.getAlpha();
        }
        public ButtonLook(String text, int background, float alpha) {
            this.text = text;
            this.background = background;
            this.alpha = alpha;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getBackground() {
            return background;
        }

        public void setBackground(int background) {
            this.background = background;
        }

        public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
        }

        public void setButtonLook(ButtonLook buttonLook){
            this.alpha = buttonLook.alpha;
            this.background = buttonLook.background;
            this.text = buttonLook.text;
        }
    }
}
