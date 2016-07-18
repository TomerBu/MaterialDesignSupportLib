package tomerbu.edu.transitionsdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class FabScrollBehaviour extends FloatingActionButton.Behavior {
    private FloatingActionButton otherFab;

    public FabScrollBehaviour(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);

        if (dyConsumed >= 0 && dyUnconsumed >= 0) {
            if (otherFab.getVisibility() != View.VISIBLE) {
                child.show();
                child.setScaleX(1.1f);
                child.setScaleY(1.1f);
            }
        } else child.hide();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        otherFab = (FloatingActionButton) coordinatorLayout.findViewById(R.id.fab);

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
}