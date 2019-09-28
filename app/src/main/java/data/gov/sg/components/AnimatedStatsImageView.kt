package data.gov.sg.components

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.image
import org.jetbrains.anko.uiThread

class AnimatedStatsImageView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var mAnimationDrawable = AnimationDrawable()

    /**
     *  set the Animation to the Drawable
     *  @param animSrc animation drawable
     *  @param start start immediately after the animation is attached
     */
    fun setAnimation( animSrc:Int){
        doAsync {
            mAnimationDrawable = context.getDrawable(animSrc) as AnimationDrawable
            uiThread {
                it.background=mAnimationDrawable
                mAnimationDrawable.start()
            }
        }
    }

    fun stopAnimation(){
        mAnimationDrawable.stop()
    }
}