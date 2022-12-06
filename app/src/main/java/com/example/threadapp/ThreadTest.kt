package com.example.threadapp

import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.atomic.AtomicBoolean

class ThreadTest {

    private var pb_main: ProgressBar? = null
    private var is_Running: AtomicBoolean = AtomicBoolean(false)
    private lateinit var monThread: Thread

    constructor(pb:ProgressBar){
        pb_main = pb
    }

    fun Go(){

        pb_main!!.progress = 0
        monThread = Thread(Runnable(){
            var i=0
            while (i<20 && is_Running.get()) {
                pb_main!!.incrementProgressBy(5)
                try{
                    Thread.sleep(500)
                }catch(e:InterruptedException){
                    e.printStackTrace()
                }
                i++
            }
        })
        is_Running.set(true)
        monThread!!.start()
    }

    fun Stop(){
        is_Running.set(false)
        monThread!!.interrupt()
        pb_main!!.setProgress(0)
    }

}