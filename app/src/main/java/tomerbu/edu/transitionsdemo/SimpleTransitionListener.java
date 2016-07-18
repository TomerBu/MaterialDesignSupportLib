package tomerbu.edu.transitionsdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Transition;

/**
 * Created by tomerbuzaglo on 18/07/2016.
 * Copyright 2016 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public abstract class SimpleTransitionListener implements Transition.TransitionListener {
    @Override
    public void onTransitionStart(Transition transition) {

    }

    @Override
    public void onTransitionEnd(Transition transition) {

    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
