package com.example.threadapp

import android.view.View
import android.widget.Button
import android.widget.ProgressBar

class BackgroundTask {

    private val MESSAGE_PRE_EXECUTE = 1
    private val MESSAGE_PROGRESS_UPDATE = 2
    private val MESSAGE_POST_EXECUTE = 3

    private var pb_progressionThHa1: ProgressBar? = null
    private var bt_startThHa: Button? = null
    private var vi_main_ui: View? = null

    constructor(v: View, bt: Button, pb: ProgressBar){
        pb_progressionThHa1 = pb
        bt_startThHa = bt
        vi_main_ui = v
    }

    fun start(){
        if(!myThread.isAlive()) myThread.Go();
    }

}