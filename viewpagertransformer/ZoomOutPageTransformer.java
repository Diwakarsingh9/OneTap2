package com.apporio.onetap.viewpagertransformer;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.apporio.onetap.R;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}




//////////////////////
//public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
//    final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.fadeinimg);
//    final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.fadeoutimg);
//    anim_out.setAnimationListener(new Animation.AnimationListener()
//    {
//        @Override public void onAnimationStart(Animation animation) {}
//        @Override public void onAnimationRepeat(Animation animation) {}
//        @Override public void onAnimationEnd(Animation animation)
//        {
//            v.setImageResource(new_image);
//            anim_in.setAnimationListener(new Animation.AnimationListener() {
//                @Override public void onAnimationStart(Animation animation) {}
//                @Override public void onAnimationRepeat(Animation animation) {}
//                @Override public void onAnimationEnd(Animation animation) {}
//            });
//            v.startAnimation(anim_in);
//        }
//    });
//    v.startAnimation(anim_out);
//}
//}