package com.example.threadapp

import android.os.AsyncTask
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class AsTask : AsyncTask<String, Int, String>{

    private var pb: ProgressBar? = null
    private var bt: Button? = null
    private var v: View? = null

    constructor(vi: View?, btn: Button?, p: ProgressBar?){
        pb = p
        bt = btn
        v = vi
    }

    override fun onPreExecute() {
        super.onPreExecute()
        bt?.visibility = View.GONE
        pb?.visibility = View.VISIBLE
        Toast.makeText(
            v?.getContext(),"Démarrage de la tâche de fond AsyncTask", Toast.LENGTH_LONG).show()

    }

    override fun doInBackground(vararg params: String?): String? {
        Log.i("Paramètre de ",params[0].toString() + params[1].toString())
        var result = ""
        for (i in 0..19) {
            onProgressUpdate(i * 5)
            result += i.toString()
            SystemClock.sleep(500)
        }
        return result
    }

    fun onProgressUpdate(progress: Int){
        pb!!.setProgress(progress)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Toast.makeText(
            v?.getContext(),"Fin de l'exécution de la tâche de fond async task "+result,Toast.LENGTH_LONG)
            .show()
        pb!!.visibility = View.GONE
        bt!!.visibility = View.VISIBLE
    }

}