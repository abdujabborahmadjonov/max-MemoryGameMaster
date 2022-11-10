package dev.abdujabbor.maxmemorygame

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hard_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_middle_game.*


class HardGame : AppCompatActivity() {

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
        R.drawable.lion,
        R.drawable.camel,
        R.drawable.camel,
        R.drawable.coala,
        R.drawable.coala,
        R.drawable.wolf,
        R.drawable.wolf,
        R.drawable.fox,
        R.drawable.fox,


    )
    private var countImage = 0
    private var photoId = arrayOfNulls<Int>(2)
    private var imageIndex = arrayOfNulls<Int>(2)
    private var imageUsed =
        arrayOf(
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_game)
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
                val animation2 = AnimationUtils.loadAnimation(this@HardGame, R.anim.anim_2)
                image.setImageResource(R.drawable.apple)
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
                val animation2 = AnimationUtils.loadAnimation(this@HardGame, R.anim.anim_2)
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
                                    apple_1.visibility = View.VISIBLE
                                    apple_2.visibility = View.VISIBLE
                                    apple_3.visibility = View.VISIBLE
                                    apple_4.visibility = View.VISIBLE
                                    apple_5.visibility = View.VISIBLE
                                    apple_6.visibility = View.VISIBLE
                                    apple_7.visibility = View.VISIBLE
                                    apple_8.visibility = View.VISIBLE
                                    apple_9.visibility = View.VISIBLE
                                    apple_10.visibility = View.VISIBLE
                                    apple_11.visibility = View.VISIBLE
                                    apple_12.visibility = View.VISIBLE
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
        var imageview = apple_1
        when (index) {
            0 -> imageview = apple_1
            1 -> imageview = apple_2
            2 -> imageview = apple_3
            3 -> imageview = apple_4
            4 -> imageview = apple_5
            5 -> imageview = apple_6
            6 -> imageview = apple_7
            7 -> imageview = apple_8
            8 -> imageview = apple_9
            9 -> imageview = apple_10
            10 -> imageview = apple_11
            11 -> imageview = apple_12
            12 -> imageview = apple_13
            13-> imageview = apple_14
            14-> imageview = apple_15
            15-> imageview = apple_16
            16-> imageview = apple_17
            17-> imageview = apple_18
            18-> imageview = apple_19
            19-> imageview = apple_20



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
                val animation2 = AnimationUtils.loadAnimation(this@HardGame, R.anim.anim_2)
                image.setImageResource(R.drawable.apple)
                image.startAnimation(animation2)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }

    private fun onTimerTick() {

        apple_1.setImageResource(images[0])
        apple_2.setImageResource(images[1])
        apple_3.setImageResource(images[2])
        apple_4.setImageResource(images[3])
        apple_5.setImageResource(images[4])
        apple_6.setImageResource(images[5])
        apple_7.setImageResource(images[6])
        apple_8.setImageResource(images[7])
        apple_9.setImageResource(images[8])
        apple_10.setImageResource(images[9])
        apple_11.setImageResource(images[10])
        apple_12.setImageResource(images[11])
        apple_13.setImageResource(images[12])
        apple_14.setImageResource(images[13])
        apple_15.setImageResource(images[14])
        apple_16.setImageResource(images[15])
        apple_17.setImageResource(images[16])
        apple_18.setImageResource(images[17])
        apple_19.setImageResource(images[18])
        apple_20.setImageResource(images[19])


        apple_1.isClickable = false
        apple_2.isClickable = false
        apple_3.isClickable = false
        apple_4.isClickable = false
        apple_5.isClickable = false
        apple_6.isClickable = false
        apple_7.isClickable = false
        apple_8.isClickable = false
        apple_9.isClickable = false
        apple_10.isClickable = false
        apple_11.isClickable = false
        apple_12.isClickable = false
        apple_13.isClickable = false
        apple_14.isClickable = false
        apple_15.isClickable = false
        apple_16.isClickable = false
        apple_17.isClickable = false
        apple_18.isClickable = false
        apple_19.isClickable = false
        apple_20.isClickable = false

    }

    private fun onTimerFinish() {
        flipCards(apple_1)
        flipCards(apple_2)
        flipCards(apple_3)
        flipCards(apple_4)
        flipCards(apple_5)
        flipCards(apple_6)
        flipCards(apple_7)
        flipCards(apple_8)
        flipCards(apple_9)
        flipCards(apple_10)
        flipCards(apple_11)
        flipCards(apple_12)
        flipCards(apple_13)
        flipCards(apple_14)
        flipCards(apple_15)
        flipCards(apple_16)
        flipCards(apple_17)
        flipCards(apple_18)
        flipCards(apple_19)
        flipCards(apple_20)


        apple_1.isClickable = true
        apple_2.isClickable = true
        apple_3.isClickable = true
        apple_4.isClickable = true
        apple_5.isClickable = true
        apple_6.isClickable = true
        apple_7.isClickable = true
        apple_8.isClickable = true
        apple_9.isClickable = true
        apple_10.isClickable = true
        apple_11.isClickable = true
        apple_12.isClickable = true
        apple_13.isClickable = true
        apple_14.isClickable = true
        apple_15.isClickable = true
        apple_16.isClickable = true
        apple_17.isClickable = true
        apple_18.isClickable = true
        apple_19.isClickable = true
        apple_20.isClickable = true



    }

    fun cardClicked(view: View) {

        if (!cardClickManager) {
            cardClickManager = true
            val image = view as ImageView
            val tag = image.tag.toString().toInt()
            start(image, images[tag], tag)
        }


    }

    override fun onPause() {
        super.onPause()
        super.onStop()
        val media  = MediaPlayer.create(this,R.raw.fluttingduck)
        media.pause()
    }


}








