package com.example.threadapp

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import android.os.Handler
import android.os.Message


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
        if(!myThread.isAlive) myThread.start();
    }

    private fun downloadPreExecute(){
        bt_startThHa?.visibility = View.GONE;
        pb_progressionThHa1!!.visibility = View.VISIBLE;
        Toast.makeText(vi_main_ui?.getContext(),"Le traitement de la tâche de fond commence", Toast.LENGTH_SHORT).show()
    }

    private fun downloadProgressUpdate(progress: Int){

        Log.i("var progress :", progress.toString())
        pb_progressionThHa1!!.progress = progress
        Log.i("pb_main_progression",pb_progressionThHa1!!.progress.toString())

    }

    private fun downloadPostExecute(){

        Toast.makeText(
            vi_main_ui?.getContext(),
            "Le traitement de la tâche de fond est terminé",
            Toast.LENGTH_SHORT
        ).show()
        bt_startThHa?.visibility = View.VISIBLE;
        pb_progressionThHa1!!.visibility = View.GONE;

    }

    private val myHandler: Handler = object: Handler(){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                MESSAGE_PRE_EXECUTE -> downloadPreExecute()
                MESSAGE_PROGRESS_UPDATE -> downloadProgressUpdate(msg.arg1)
                MESSAGE_POST_EXECUTE -> downloadPostExecute()
                else -> {}
            }
        }

    }

    private var myThread = Thread(){
        try {
            sendPreExecuteMessage()
            for (i in 0..19) {
                sendProgressMessage(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            sendPostExecuteMessage()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun sendPostExecuteMessage(){
        var postExecuteMessage = Message()
        postExecuteMessage.what = MESSAGE_POST_EXECUTE
        myHandler.sendMessage(postExecuteMessage)
    }

    private fun sendPreExecuteMessage(){
        val preExecuteMessage = Message()
        preExecuteMessage.what = MESSAGE_PRE_EXECUTE
        myHandler.sendMessage(preExecuteMessage)
    }

    private fun sendProgressMessage(i: Int){
        val progressMessage = Message()
        progressMessage.what = MESSAGE_PROGRESS_UPDATE
        progressMessage.arg1 = i * 10
        myHandler.sendMessage(progressMessage)
    }

}