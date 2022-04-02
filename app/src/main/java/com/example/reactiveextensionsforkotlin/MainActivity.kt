package com.example.reactiveextensionsforkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.AndroidCharacter
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.AndroidViewModel
import com.example.reactiveextensionsforkotlin.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val observable = createButtonClickObservable()
        observable.subscribeOn(Schedulers.computation()).
            map {
                it.plus(it)
            }
            .subscribe {
                binding.textView.text = it
            }

    }

    /*private fun createButtonClickObservable(): Observable<String> {
        return Observable.create{emitter->
            binding.buttonFirst.setOnClickListener {
                emitter.onNext("emitter test")
            }
            emitter.setCancellable(null)
        }*/

    private fun createButtonClickObservable(): Observable<String> {
        return Observable.create{emitter->
            binding.editText.addTextChangedListener {
                emitter.onNext(it.toString())
            }
            emitter.setCancellable(null)
        }
    }
}