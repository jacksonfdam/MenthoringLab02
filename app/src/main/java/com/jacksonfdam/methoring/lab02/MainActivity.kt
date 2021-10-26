package com.jacksonfdam.methoring.lab02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jacksonfdam.methoring.lab02.databinding.ActivityMainBinding
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var info: Info
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        DaggerMagicBox.create().poke(this)
        setContentView(view)
        view.textView.text = info.text
    }
}

@Module
class Bag {
    @Provides
    fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }
}

class Info(val text: String)

// @Component(modules = [Bag::class, OtherBag::class, MoreBag::Class])
@Component(modules = [Bag::class])
interface MagicBox {
    fun poke(app: MainActivity)
}
