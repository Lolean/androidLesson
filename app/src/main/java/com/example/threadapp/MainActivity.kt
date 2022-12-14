package com.example.threadapp

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

     lateinit var myThread : ThreadTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun xmlClickEvent(v : View){
        when(v.id){
            bt_main_text.id -> txtSwap()
            bt_thread.id -> threadLaunch()
            bt_async1.id -> asyncTask1(v)
            bt_TH.id -> threadHandlerLaunch(v)

        }
    }

     private fun txtSwap(){
        if(tv_main_txt.getText().equals("Hello World!")) tv_main_txt.setText("Hello Student!")
         else tv_main_txt.setText("Hello World!")
     }

    private fun threadLaunch(){
        if (bt_thread.getText().equals("Launch thread")) {
            myThread = ThreadTest(pb_main)
            myThread.Go()
            bt_thread.setText("Thread Stop !")
            Toast.makeText(getApplicationContext(), "Activation du Thread", Toast.LENGTH_LONG).show()
        }
        else {
            myThread.Stop()
            Toast.makeText(getApplicationContext(), "Désactivation du Thread", Toast.LENGTH_LONG)
                .show()
            bt_thread.setText("Launch thread")
        }
    }

    private fun asyncTask1(v: View){
        val asyncrotask = AsTask(v, bt_async1, pb_second)
        asyncrotask.execute("paramètres --->", "<--- de traitement")
    }

    private fun threadHandlerLaunch(v : View){

        val bckgTsk1 = BackgroundTask(v,bt_TH,pb_Th1);
        bckgTsk1.start();
        val bckgTsk2 = BackgroundTask(v,bt_TH,pb_Th2);
        bckgTsk2.start();

    }


}