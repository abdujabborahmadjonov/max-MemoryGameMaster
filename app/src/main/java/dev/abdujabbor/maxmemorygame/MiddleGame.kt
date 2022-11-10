package dev.abdujabbor.maxmemorygame

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_middle_game.*


class MiddleGame : AppCompatActivity() {

    private var cardClickManager = false

    private val images = arrayListOf(
        R.drawable.camel,
        R.drawable.camel,
        R.drawable.coala,
        R.drawable.coala,
        R.drawable.wolf,
        R.drawable.wolf,
        R.drawable.fox,
        R.drawable.fox,
        R.drawable.monkey,
        R.drawable.monkey,
        R.drawable.lion,
        R.drawable.lion

    )
    private var countImage = 0
    private var photoId = arrayOfNulls<Int>(2)
    private var imageIndex = arrayOfNulls<Int>(2)
    private var imageUsed =
        arrayOf(false, false, false, false, false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle_game)
        restart()

        val media = MediaPlayer.create(this,R.raw.fluttingduck)
        media.start()


    }

    fun restart() {
        images.shuffle()
        setClickListeners()
    }


    private fun start(image: ImageView, photo: Int, index: Int) {
        if (imageUsed[index]) {
            closeImage(image, index)
        } else {
            openImage(image, photo, index)
        }

    }

    private fun closeImage(image: ImageView, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                cardClickManager = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_2)
                image.setImageResource(R.drawable.code)
                image.startAnimation(animation2)
                imageUsed[index!!] = false
                countImage--
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        cardClickManager = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })


    }


    private fun openImage(image: ImageView, photo: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                cardClickManager = true

            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_2)
                image.startAnimation(animation2)
                image.setImageResource(photo)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        imageUsed[index] = true
                        imageIndex[countImage] = index
                        photoId[countImage] = photo
                        countImage++

                        if (countImage == 2) {
                            if (photoId[0] == photoId[1]) {
                                findImageView(imageIndex[0]).visibility = View.INVISIBLE
                                countImage--
                                findImageView(imageIndex[1]).visibility = View.INVISIBLE
                                countImage--
                                if (!imageUsed.contains(false)) {
                                    for (i in imageUsed.indices) {
                                        imageUsed[i] = false
                                    }
                                    rasm_12.visibility = View.VISIBLE
                                    rasm_1.visibility = View.VISIBLE
                                    rasm_2.visibility = View.VISIBLE
                                    rasm_3.visibility = View.VISIBLE
                                    rasm_4.visibility = View.VISIBLE
                                    rasm_5.visibility = View.VISIBLE
                                    rasm_6.visibility = View.VISIBLE
                                    rasm_7.visibility = View.VISIBLE
                                    rasm_8.visibility = View.VISIBLE
                                    rasm_9.visibility = View.VISIBLE
                                    rasm_10.visibility = View.VISIBLE
                                    rasm_11.visibility = View.VISIBLE
                                    restart()
                                }

                            } else {
                                closeImage(findImageView(imageIndex[0]), imageIndex[0])
                                cardClickManager = true
                                closeImage(findImageView(imageIndex[1]), imageIndex[1])
                            }
                        }
                        cardClickManager = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }
                })


            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

    }


    fun findImageView(index: Int?): ImageView {
        var imageview = rasm_1
        when (index) {
            0 -> imageview = rasm_1
            1 -> imageview = rasm_2
            2 -> imageview = rasm_3
            3 -> imageview = rasm_4
            4 -> imageview = rasm_5
            5 -> imageview = rasm_6
            6 -> imageview = rasm_7
            7 -> imageview = rasm_8
            8 -> imageview = rasm_9
            9 -> imageview = rasm_10
            10 -> imageview = rasm_11
            11 -> imageview = rasm_12

        }
        return imageview!!
    }


    private fun setClickListeners() {


        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                onTimerTick()
            }

            override fun onFinish() {
                onTimerFinish()
            }
        }.start()

    }

    private fun flipCards(image: ImageView) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        image.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_2)
                image.setImageResource(R.drawable.code)
                image.startAnimation(animation2)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }

    private fun onTimerTick() {

        rasm_1.setImageResource(images[0])
        rasm_2.setImageResource(images[1])
        rasm_3.setImageResource(images[2])
        rasm_4.setImageResource(images[3])
        rasm_5.setImageResource(images[4])
        rasm_6.setImageResource(images[5])
        rasm_7.setImageResource(images[6])
        rasm_8.setImageResource(images[7])
        rasm_9.setImageResource(images[8])
        rasm_10.setImageResource(images[9])
        rasm_11.setImageResource(images[10])
        rasm_12.setImageResource(images[11])

        rasm_1.isClickable = false
        rasm_2.isClickable = false
        rasm_3.isClickable = false
        rasm_4.isClickable = false
        rasm_5.isClickable = false
        rasm_6.isClickable = false
        rasm_7.isClickable = false
        rasm_8.isClickable = false
        rasm_9.isClickable = false
        rasm_10.isClickable = false
        rasm_11.isClickable = false
        rasm_12.isClickable = false
    }

    private fun onTimerFinish() {
        flipCards(rasm_1)
        flipCards(rasm_2)
        flipCards(rasm_3)
        flipCards(rasm_4)
        flipCards(rasm_5)
        flipCards(rasm_6)
        flipCards(rasm_7)
        flipCards(rasm_8)
        flipCards(rasm_9)
        flipCards(rasm_10)
        flipCards(rasm_11)
        flipCards(rasm_12)

        rasm_1.isClickable = true
        rasm_2.isClickable = true
        rasm_3.isClickable = true
        rasm_4.isClickable = true
        rasm_5.isClickable = true
        rasm_6.isClickable = true
        rasm_7.isClickable = true
        rasm_8.isClickable = true
        rasm_9.isClickable = true
        rasm_10.isClickable = true
        rasm_11.isClickable = true
        rasm_12.isClickable = true


    }

    fun cardClicked(view: View) {

        if (!cardClickManager) {
            cardClickManager = true
            val image = view as ImageView
            val tag = image.tag.toString().toInt()
            start(image, images[tag], tag)
        }


    }


}












/*
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_middle_game.*

class MiddleGame : AppCompatActivity() {

    val listImageOchiqYopiq = arrayOf(false, false, false, false, false, false,false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle_game)

        if (ochiqRasm==12){
            tv_won.text = "You are won"
        }

        val mediaPlayer = MediaPlayer.create(this,R.raw.back2)
        mediaPlayer.start()
        rasm_1.setOnClickListener {
            imageClick(rasm_1, R.drawable.camel, 0)
        }
        rasm_2.setOnClickListener {
            imageClick(rasm_2, R.drawable.fox, 1)
        }
        rasm_3.setOnClickListener {
            imageClick(rasm_3, R.drawable.wolf, 2)
        }
        rasm_4.setOnClickListener {
            imageClick(rasm_4, R.drawable.camel, 3)
        }
        rasm_5.setOnClickListener {
            imageClick(rasm_5, R.drawable.wolf, 4)
        }
        rasm_6.setOnClickListener {
            imageClick(rasm_6, R.drawable.fox, 5)
        }
        rasm_7.setOnClickListener {
            imageClick(rasm_7, R.drawable.lion, 6)
        }
        rasm_8.setOnClickListener {
            imageClick(rasm_8, R.drawable.monkey, 7)
        }
        rasm_9.setOnClickListener {
            imageClick(rasm_9, R.drawable.coala, 8)
        }
        rasm_10.setOnClickListener {
            imageClick(rasm_10, R.drawable.monkey, 9)
        }
        rasm_11.setOnClickListener {
            imageClick(rasm_11, R.drawable.lion, 10)
        }
        rasm_12.setOnClickListener {
            imageClick(rasm_12, R.drawable.coala, 11)
        }
    }

    fun imageClick(imageView: ImageView, rasm: Int, index: Int) {
        if (!animationDoing) {

            if (!listImageOchiqYopiq[index]) {
                animationOpen(imageView, rasm, index)
            } else {
                animationClose(imageView, rasm, index)
            }
        }
    }

    fun animationOpen(imageView: ImageView, rasm: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing =true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        listImageOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(imageIndex[0]!!)!!.visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]!!)!!.visibility = View.INVISIBLE
                                ochiqRasm--
                            } else {
                                animationClose(
                                    imageViewAniqla(imageIndex[0]!!),
                                    -1,
                                    imageIndex[0]!!
                                )
                                animationClose(
                                    imageViewAniqla(imageIndex[1]!!),
                                    -1,
                                    imageIndex[1]!!
                                )
                            }


                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

    }


    fun animationClose(imageView: ImageView?, rasm: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_1)
        imageView!!.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MiddleGame, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.code)
                animation2.setAnimationListener(object :Animation.AnimationListener{
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
        listImageOchiqYopiq[index!!] = false
        ochiqRasm--
    }

    fun imageViewAniqla(index: Int): ImageView? {
        var imageView: ImageView? = null
        when (index) {

            0 -> imageView = rasm_1
            1 -> imageView = rasm_2
            2 -> imageView = rasm_3
            3 -> imageView = rasm_4
            4 -> imageView = rasm_5
            5 -> imageView = rasm_6
            6 -> imageView = rasm_7
            7 -> imageView = rasm_8
            8 -> imageView = rasm_9
            9 -> imageView = rasm_10
            10 -> imageView = rasm_11
            11-> imageView = rasm_12


        }
        return imageView!!
    }
}

*/