package com.example.animationtryout

import android.animation.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),Animator.AnimatorListener {

    private var rotateAnimator: ObjectAnimator? = null
    private var translateAnimator: ObjectAnimator? = null
    private var scaleAnimator: ObjectAnimator? = null
    private var fadeAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun rotateAnimation(view: View) {

        rotateAnimator = ObjectAnimator.ofFloat(spydermanImage, "rotation", 0.0f, -180.0f)
        rotateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun scaleAnimator(view: View) {

        scaleAnimator = ObjectAnimator.ofFloat(spydermanImage, "scaleX", 1.0f, 3.0f)
        scaleAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun translateAnimation(view: View) {

        translateAnimator = ObjectAnimator.ofFloat(spydermanImage, "translationX", 0f, 200f)
        translateAnimator?.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }

        // translateAnimator.cancel()
    }

    fun fadeAnimation(view: View) {

        fadeAnimator = ObjectAnimator.ofFloat(spydermanImage, "alpha", 1.0f, 0.0f)
        fadeAnimator?.apply {
            duration = 1500
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            addListener(this@MainActivity)
            start()
        }
    }

    fun setFromCode(view: View) {

        // Root Animator Set
        val rootSet = AnimatorSet()

        // Flip Animation
        val flip = ObjectAnimator.ofFloat(spydermanImage, "rotationX", 0.0f, 360.0f)
        flip.duration = 500

        // Child Animator Set
        val childSet = AnimatorSet()

        // Scale Animations
        val scaleX = ObjectAnimator.ofFloat(spydermanImage, "scaleX", 1.0f, 1.5f)
        scaleX.duration = 500

        val scaleY = ObjectAnimator.ofFloat(spydermanImage, "scaleY", 1.0f, 1.5f)
        scaleY.duration = 500

//		rootSet.playSequentially(flip, childSet)
//		childSet.playTogether(scaleX, scaleY)

        rootSet.play(flip).before(childSet)
        childSet.play(scaleX).with(scaleY)

        rootSet.start()
    }



    override fun onAnimationStart(animation: Animator?) {

        if (animation == scaleAnimator)
            Toast.makeText(this, "Scale Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == rotateAnimator)
            Toast.makeText(this, "Rotate Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == translateAnimator)
            Toast.makeText(this, "Translate Animation Started", Toast.LENGTH_SHORT).show()

        if (animation == fadeAnimator)
            Toast.makeText(this, "Fade Animation Started", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationRepeat(animation: Animator?) {

        Toast.makeText(this, "Animation Repeated", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationEnd(animation: Animator?) {

        Toast.makeText(this, "Animation Ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAnimationCancel(animation: Animator?) {

        Toast.makeText(this, "Animation Cancelled", Toast.LENGTH_SHORT).show()
    }


    fun viewPropertyAnimator(view: View) {
        val vpa = spydermanImage.animate()
        vpa.apply {
            duration = 1000
            rotationX(360.0f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationX(200.0f)
            alpha(0.5f)
            interpolator = OvershootInterpolator()
            start()
        }
    }

    fun propertyValuesHolder(view: View) {

        val rotX = PropertyValuesHolder.ofFloat("rotationX", 360f)
        val scaX = PropertyValuesHolder.ofFloat("scaleX", 1.5f)
        val scaY = PropertyValuesHolder.ofFloat("scaleY", 1.5f)

        val objA = ObjectAnimator.ofPropertyValuesHolder(spydermanImage, rotX, scaX, scaY)
        objA.apply {
            duration = 1000
            interpolator = OvershootInterpolator()
            addListener(this@MainActivity)
            start()
        }
    }



    fun goToActivityAnimationDrawabale(view: View) {
        val intent=Intent(this,AnimationDrawableÂctivity::class.java)
        startActivity(intent)
    }

}




