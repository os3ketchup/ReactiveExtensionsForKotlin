package com.example.reactiveextensionsforkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.reactiveextensionsforkotlin.databinding.ActivityMain2Binding
import com.example.reactiveextensionsforkotlin.databinding.ItemDialogBinding
import com.example.reactiveextensionsforkotlin.models.Consumer
import com.example.reactiveextensionsforkotlin.models.adapter.MyAdapter
import com.example.reactiveextensionsforkotlin.models.dao.AppDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.Schedulers.io
import io.reactivex.schedulers.Schedulers.io

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var myAdapter: MyAdapter
    lateinit var appDataBase: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        appDataBase = AppDataBase.getInstance(this)

        binding.apply {
            myFloating.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity2).create()
                val itemDialog = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialog.root)
                itemDialog.dialogButtonSave.setOnClickListener {
                    val consumer = Consumer(
                        itemDialog.dialogName.text.toString(),
                        itemDialog.dialogNumber.text.toString(),
                    )

                    appDataBase.myDao().addConsumer(consumer)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {t->
                            Toast.makeText(this@MainActivity2, "$t", Toast.LENGTH_SHORT).show()
                        }
                    dialog.cancel()
                }
                dialog.show()
            }
            appDataBase.myDao().getAllConsumer().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    myAdapter = MyAdapter(it)
                    binding.rv.adapter = myAdapter
                }
        }
    }
}