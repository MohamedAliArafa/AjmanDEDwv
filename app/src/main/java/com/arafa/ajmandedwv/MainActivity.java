package com.arafa.ajmandedwv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(rvFeed)
//    RecyclerView rvFeed;
    @BindView(R.id.btnCreate)
    FloatingActionButton fabCreate;
    @BindView(R.id.content)
    CoordinatorLayout clContent;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.circle_menu)
    CircleMenuLayout mCircleMenuLayout;


    private String[] mItemTexts = new String[] { "About ", "ContactUS", "Guide",
            "Register", "Services", "Word" };
    private int[] mItemImgs = new int[] { R.drawable.about,
            R.drawable.contact, R.drawable.guide,
            R.drawable.register, R.drawable.services,
            R.drawable.word };


//    @BindView(R.id.circle_frame)
//    FrameLayout circleFrame;
//    @BindView(R.id.circle_root)
//    RelativeLayout circleRoot;
//
//    float viewRotation;
//    double fingerRotation;
//    double newFingerRotation;

    private MenuItem inboxMenuItem;
    private FeedAdapter feedAdapter;
    private boolean pendingIntroAnimation;

    private static final int ANIM_DURATION_TOOLBAR = 700;
    private static final int ANIM_DURATION_FAB = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;
        }
        ButterKnife.bind(this);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener()
        {

            @Override
            public void itemClick(View view, int pos)
            {
                Toast.makeText(MainActivity.this, mItemTexts[pos],
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void itemCenterClick(View view)
            {
                Toast.makeText(MainActivity.this,
                        "you can do something just like ccb  ",
                        Toast.LENGTH_SHORT).show();

            }
        });

//        circleRoot.setOnTouchListener(this);
//        createCircle();
        setupToolbar();
//        setupFeed();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
    }

//    private void createCircle() {
//        int numViews = 8;
//        for(int i = 0; i < numViews; i++)
//        {
//            // Create some quick TextViews that can be placed.
//            TextView v = new TextView(this);
//            // Set a text and center it in each view.
//            v.setText("View " + i);
//            v.setGravity(Gravity.CENTER);
//            v.setBackgroundColor(0xffff0000);
//            // Force the views to a nice size (150x100 px) that fits my display.
//            // This should of course be done in a display size independent way.
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(150, 100);
//            // Place all views in the center of the layout. We'll transform them
//            // away from there in the code below.
//            lp.gravity = Gravity.CENTER;
//            // Set layout params on view.
//            v.setLayoutParams(lp);
//
//            // Calculate the angle of the current view. Adjust by 90 degrees to
//            // get View 0 at the top. We need the angle in degrees and radians.
//            float angleDeg = i * 360.0f / numViews - 90.0f;
//            float angleRad = (float)(angleDeg * Math.PI / 180.0f);
//            // Calculate the position of the view, offset from center (300 px from
//            // center). Again, this should be done in a display size independent way.
//            v.setTranslationX(300 * (float)Math.cos(angleRad));
//            v.setTranslationY(300 * (float)Math.sin(angleRad));
//            // Set the rotation of the view.
//            v.setRotation(angleDeg + 90.0f);
//            circleFrame.addView(v);
//        }
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        final float x = event.getX();
//        final float y = event.getY();
//
//        final float xc = circleRoot.getWidth()/2;
//        final float yc = circleRoot.getHeight()/2;
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                viewRotation = circleFrame.getRotation();
//                fingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                newFingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));
//                circleRoot.setRotation((float)(viewRotation + newFingerRotation - fingerRotation));
//                break;
//            case MotionEvent.ACTION_UP:
//                fingerRotation = newFingerRotation = 0.0f;
//                break;
//        }
//
//        return true;
//    }

//    private void setupFeed() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvFeed.setLayoutManager(linearLayoutManager);
//        feedAdapter = new FeedAdapter(this);
//        rvFeed.setAdapter(feedAdapter);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
        inboxMenuItem.setActionView(R.layout.menu_item_view);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        fabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionbarSize = Utils.dpToPx(56);
        int actionbarWidth = toolbar.getWidth();
//        toolbar.setTranslationY(-actionbarSize);
//        ivLogo.setTranslationY(-actionbarSize);
        ivLogo.setTranslationX(-actionbarWidth/2 - ivLogo.getWidth());
        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        ivLogo.animate()
                .translationX(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        inboxMenuItem.getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    }

    private void startContentAnimation() {
        fabCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
//        feedAdapter.updateItems(true);
    }

}
