package tomerbu.edu.transitionsdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {
    private ImageView ivCheeseImage;
    private TextView tvCheeseDescription;
    private Cheese data;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar();
        findViews();

        fillData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            revealThatCheese();


    }
    private void findViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ivCheeseImage = (ImageView) findViewById(R.id.ivCheese);
        tvCheeseDescription = (TextView) findViewById(R.id.tvDescription);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void fillData() {
        Intent intent = getIntent();
        if (intent != null) {
            data = intent.getParcelableExtra(MyRecyclerAdapter.EXTRA_CHEESE);
            if (data != null) {
                tvCheeseDescription.setText(data.getDescription());
                ivCheeseImage.setImageResource(data.getImageResID());
            }
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void revealThatCheese() {
        ivCheeseImage.setVisibility(View.INVISIBLE);
        getWindow().getSharedElementEnterTransition().addListener(new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                ViewAnimationUtils.createCircularReveal(ivCheeseImage, 0, 0, 0, ivCheeseImage.getHeight()).start();
                ivCheeseImage.setVisibility(View.VISIBLE);
            }
        });
    }

    /***
     * Must override for the transition animation to perform
     * @param item A Menu Item that was selected
     * @return True if handled.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
