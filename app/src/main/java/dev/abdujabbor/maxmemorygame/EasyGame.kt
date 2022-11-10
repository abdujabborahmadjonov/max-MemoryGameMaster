package dev.abdujabbor.maxmemorygame

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_easy_game.*
class EasyGame : AppCompatActivity() {

    private var cardClickManager = false
    private val images = arrayListOf(
        R.drawable.moto_rasm,
        R.drawable.moto_rasm,
        R.drawable.tabiat_rasm_3,
        R.drawable.tabiat_rasm_3,
        R.drawable.tabiat_rasm_2,
        R.drawable.tabiat_rasm_2,
    )
    private var countImage = 0
    private var photoId = arrayOfNulls<Int>(2)
    private var imageIndex = arrayOfNulls<Int>(2)
    private var imageUsed =
        arrayOf(false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_game)
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
                val animation2 = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_2)
                image.setImageResource(R.drawable.yulduzcha)
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
                val animation2 = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_2)
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

                                    image_1.visibility = View.VISIBLE
                                    image_2.visibility = View.VISIBLE
                                    image_3.visibility = View.VISIBLE
                                    image_4.visibility = View.VISIBLE
                                    image_5.visibility = View.VISIBLE
                                    image_6.visibility = View.VISIBLE

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
        var imageview:ImageView = image_1
        when (index) {
            0 -> imageview = image_1
            1 -> imageview = image_2
            2 -> imageview = image_3
            3 -> imageview = image_4
            4 -> imageview = image_5
            5 -> imageview = image_6

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
                val animation2 = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_2)
                image.setImageResource(R.drawable.yulduzcha)
                image.startAnimation(animation2)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }

    private fun onTimerTick() {


        image_1.setImageResource(images[0])
        image_2.setImageResource(images[1])
        image_3.setImageResource(images[2])
        image_4.setImageResource(images[3])
        image_5.setImageResource(images[4])
        image_6.setImageResource(images[5])

        image_1.isClickable = false
        image_2.isClickable = false
        image_3.isClickable = false
        image_4.isClickable = false
        image_5.isClickable = false
        image_6.isClickable = false

    }

    private fun onTimerFinish() {

        flipCards(image_1)
        flipCards(image_2)
        flipCards(image_3)
        flipCards(image_4)
        flipCards(image_5)
        flipCards(image_6)

        image_1.isClickable = true
        image_2.isClickable = true
        image_3.isClickable = true
        image_4.isClickable = true
        image_5.isClickable = true
        image_6.isClickable = true



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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_easy_game.*
import kotlinx.android.synthetic.main.activity_main.*

class EasyGame : AppCompatActivity() {
    val listImageOchiqYopiq = arrayOf(false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    val images  = arrayOf(
        R.drawable.tabiat_rasm_2,
        R.drawable.tabiat_rasm_2,
        R.drawable.moto_rasm,
        R.drawable.moto_rasm,
        R.drawable.tabiat_rasm_3,
        R.drawable.tabiat_rasm_3,
    )
    var ochiqRasm = 0
    var animationDoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_game)

        restart()
images.shuffle()
        val mediaPlayer = MediaPlayer.create(this,R.raw.back2)
        mediaPlayer.start()

        image_1.setOnClickListener {
            imageClick(image_1, R.drawable.moto_rasm, 0)
        }
        image_2.setOnClickListener {
            imageClick(image_2, R.drawable.tabiat_rasm_2, 1)
        }
        image_3.setOnClickListener {
            imageClick(image_3, R.drawable.tabiat_rasm_3, 2)
        }
        image_4.setOnClickListener {
            imageClick(image_4, R.drawable.moto_rasm, 3)
        }
        image_5.setOnClickListener {
            imageClick(image_5, R.drawable.tabiat_rasm_3, 4)
        }
        image_6.setOnClickListener {
            imageClick(image_6, R.drawable.tabiat_rasm_2, 5)
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
        val animation = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing =true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_2)
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
                                imageViewAniqla(imageIndex[0]!!).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]!!).visibility = View.INVISIBLE
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


    fun animationClose(imageView: ImageView, rasm: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@EasyGame, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.yulduzcha)
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

    fun imageViewAniqla(index: Int): ImageView {
        var imageView: ImageView? = null
        when (index) {

            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6


        }
        return imageView!!
    }

    fun restart(){
        images.shuffle()

    }

}

*/