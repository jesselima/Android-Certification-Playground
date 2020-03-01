package com.example.playground

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.*
import android.text.Annotation
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // When need, use the Android Context object for manual locale lookup
        // To see more about Configuration class switch to 03-android-core-localize-your-app-configuration-changes
        val primaryLocale: Locale = resources.configuration.locales[0]
        val country: String         = primaryLocale.country
        val displayCountry: String  = primaryLocale.displayCountry
        val displayLanguage: String = primaryLocale.displayLanguage
        val displayScript: String   = primaryLocale.displayScript
        val displayVariant: String  = primaryLocale.displayVariant
        val isO3Country: String     = primaryLocale.isO3Country
        val isO3Language: String    = primaryLocale.isO3Language
        val language: String        = primaryLocale.language
        val script: String          = primaryLocale.script
        val variant: String         = primaryLocale.variant

        buttonLogValuesFromResource.setOnClickListener {
            logValuesFromResource()
        }

        buttonToastLocalJsonFile.setOnClickListener {
            toastLocalJsonFile()
        }

        // Button to load Property Animation
        buttonLoadPropertyAnimation.setOnClickListener {
            loadPropertyAnimation()
        }

        // Button to load View Animation
        buttonLoadPropertyAnimation.setOnClickListener {
            loadViewAnimation()
        }

        // Button to load Frame Animation
        buttonLoadFrameAnimation.setOnClickListener {
            loadFrameAnimation()
        }

        sendSpannableString.setOnClickListener {
            val spannableString = SpannableString(getString(R.string.annotation_string_sample))
            val annotation = Annotation(Constants.annotationResourceTypeFont, AppFonts.ibmPlexSansBold)
            spannableString.setSpan(annotation, 0, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("TEXT_EXTRA", spannableString)
            startActivity(intent)
        }

    }

    // Property animation
    private fun loadPropertyAnimation() {
        val imageView: ImageView = findViewById(R.id.imageLogo)
        val set: Animator = AnimatorInflater.loadAnimator(applicationContext, R.animator.property_animator)
            .apply {
                setTarget(imageView)
                start()
            }
    }

    // View animation
    private fun loadViewAnimation() {
        val imageView: ImageView = findViewById(R.id.imageLogo)
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.view_animation)
        imageView.startAnimation(animation)

//        AnimationUtils.loadAnimation(this, R.anim.view_animation).also { hyperspaceJumpAnimation ->
//            findViewById<ImageView>(R.id.imageLogo).startAnimation(hyperspaceJumpAnimation)
//        }

    }

    // Frame Animation - When called will show the battery filling animation
    private fun loadFrameAnimation() {
        val imageView: ImageView = findViewById(R.id.image_view_animation_battery_filling)
        (imageView.background as AnimationDrawable).start()
    }

    private fun toastLocalJsonFile() {
        val inputStream = resources.openRawResource(R.raw.data)
        val jsonAsString = inputStream.readJsonAndClose()
        Toast.makeText(this, jsonAsString, Toast.LENGTH_LONG).show()
    }

    /**
     * https://stackoverflow.com/questions/39500045/in-kotlin-how-do-i-read-the-entire-contents-of-an-inputstream-into-a-string
     * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/index.html
     */
    private fun InputStream.readJsonAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }

    /**
     * This method load and log many resource types.
     * Use as a code sample.
     */
    private fun logValuesFromResource() {
        Log.d("======= ", "VALUES FROM RESOURCE ======")
        /**
         * Accessing resources
         */
        val screenSmall: Boolean    = resources.getBoolean(R.bool.screen_small)
        Log.d("screenSmall --> ", screenSmall.toString())

        val fontSize: Float         = resources.getDimension(R.dimen.font_size)
        Log.d("fontSize --> ", fontSize.toString())

        val maxSpeed: Int           = resources.getInteger(R.integer.max_speed)
        Log.d("maxSpeed --> ", maxSpeed.toString())

        val minSpeed: Int           = resources.getInteger(R.integer.min_speed)
        Log.d("minSpeed --> ", minSpeed.toString())

        val bitsValues: IntArray     = resources.getIntArray(R.array.bits)
        for (value in bitsValues) {
            Log.d("bitsValues --> ", value.toString())
        }

        val numbers: IntArray       = resources.getIntArray(R.array.array_of_numbers)
        for (value in numbers) {
            Log.d("numbers --> ", value.toString())
        }

        val stringArray: Array<String> = resources.getStringArray(R.array.my_string_array)
        for (value in stringArray) {
            Log.d("stringArray --> ", value)
        }

        // TODO -> fix it! Investigate why plurals is not accessible
        //  String resource plurals - "R.plurals" is not accessible
        //val count = 10 // getNumberOfSongsAvailable() // get the number from somewhere
        //val songsFound = resources.getQuantityString(R.plurals.numberOfSongsAvailable, 10)

        val colorFromResource: Int  = resources.getColor(R.color.colorAccent)

        val colors: TypedArray      = resources.obtainTypedArray(R.array.colors)
        val color: Int = colors.getColor(0, 0)
        colors.recycle()

        val icons: TypedArray       = resources.obtainTypedArray(R.array.icons)
        val drawable: Drawable? = icons.getDrawable(0)
        icons.recycle()

        // Accessing and using formated string
        val numberOfMessages = 40
        val username = "Jesse Lima"
        // Because the fromHtml(String) method formats all HTML entities, be sure to escape any
        // possible HTML characters in the strings you use with the formatted text, using htmlEncode(String).
        val escapedUsername: String = TextUtils.htmlEncode(username)
        val formatedString: String = getString(R.string.welcome_messages, escapedUsername, numberOfMessages)
        val styledText: Spanned = Html.fromHtml(formatedString, FROM_HTML_MODE_LEGACY)
        val textView1: TextView = findViewById(R.id.stringWithHtmlTags1)
        val textView2: TextView = findViewById(R.id.stringWithHtmlTags2)
        textView1.text = styledText.toString()
        textView2.text = styledText.toString()

        // Using styling with spannables helper methods
        val textViewWithSpannables = findViewById<TextView>(R.id.textViewStyledWithSpannables)
        val text: CharSequence = bold(
            italic(getString(R.string.hello)),
            color(Color.RED, getString(R.string.world)),
            color(Color.BLUE, " $username")
        )
        textViewWithSpannables.text = text

        // Custom Typefaces
        addCustomTypeFace()

    }


    // ### Styling with spannables ### ==============================
    /**
     * Returns a CharSequence that concatenates the specified array of CharSequence
     * objects and then applies a list of zero or more tags to the entire range.
     *
     * @param content an array of character sequences to apply a style to
     * @param tags the styled span objects to apply to the content
     *        such as android.text.style.StyleSpan
     */
    private fun apply(content: Array<out CharSequence>, vararg tags: Any): CharSequence {
        return SpannableStringBuilder().apply {
            openTags(tags)
            content.forEach { charSequence ->
                append(charSequence)
            }
            closeTags(tags)
        }

    }
    /** FUNCTION EXTENSIONS - openTags()
     * Iterates over an array of tags and applies them to the beginning of the specified
     * Spannable object so that future text appended to the text will have the styling
     * applied to it. Do not call this method directly.
     */
    private fun Spannable.openTags(tags: Array<out Any>) {
        tags.forEach { tag ->
            setSpan(tag, 0, 0, Spanned.SPAN_MARK_MARK)
        }
    }
    /** FUNCTION EXTENSIONS - closeTags()
     * "Closes" the specified tags on a Spannable by updating the spans to be
     * endpoint-exclusive so that future text appended to the end will not take
     * on the same styling. Do not call this method directly.
     */
    private fun Spannable.closeTags(tags: Array<out Any>) {
        tags.forEach { tag ->
            if (length > 0) {
                setSpan(tag, 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                removeSpan(tag)
            }
        }
    }

    /**
     * The following bold, italic, and color methods wrap the helper methods above and demonstrate
     * specific examples of applying styles defined in the android.text.style package.
     * You can create similar methods to do other types of text styling.
     */

    /**
     * Returns a CharSequence that applies boldface, italicFace, and color to the concatenation
     * of the specified CharSequence objects.
     */
    private fun bold(vararg content: CharSequence): CharSequence = apply(content, StyleSpan(Typeface.BOLD))
    private fun italic(vararg content: CharSequence): CharSequence = apply(content, StyleSpan(Typeface.ITALIC))
    private fun boldItalic(vararg content: CharSequence): CharSequence = apply(content, StyleSpan(Typeface.BOLD_ITALIC))
    private fun color(color: Int, vararg content: CharSequence): CharSequence = apply(content, ForegroundColorSpan(color))


    // Example - adding a custom typeface
    private fun addCustomTypeFace() {
        /**
         * Load the string resource and find the annotations with the font key.
         * Then create a custom span and replace the existing span.
         */
        ///* get the text as SpannedString so we can get the spans attached to the text */
         val titleText = getText(R.string.title) as SpannedString

        ///* get all the annotation spans from the text */
        val annotations = titleText.getSpans(0, titleText.length, Annotation::class.java)

        ///* create a copy of the title text as a SpannableString. */
        ///* the constructor copies both the text and the spans. so we can add and remove spans */
        val spannableString = SpannableString(titleText)

        ///* iterate through all the annotation spans */ // TODO -> Not working. Might be import, dependency or SNK. Or.. I do not know yet. I will check todo's later. Move on!!
        for (annotation in annotations) {
            // look for the span with the key font
            if (annotation.key == Constants.annotationResourceTypeFont) {
                val fontName = annotation.value
                // check the value associated to the annotation key
                if (fontName == AppFonts.ibmPlexSansBold) {
                    // create the typeface
                    val typeface = getFontCompat(R.font.ibm_plex_sans_bold)
                    // set the span at the same indices as the annotation
                    spannableString.setSpan(CustomTypefaceSpan(typeface),
                    titleText.getSpanStart(annotation),
                    titleText.getSpanEnd(annotation),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
        // now, the spannableString contains both the annotation spans and the CustomTypefaceSpan
        val textView: TextView = findViewById(R.id.textViewCustomStyledWithSpannables)
        textView.text = spannableString
    }

    // Functions Extension ResourceCompat for Font and Color
    private fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)
    fun Context.getFontCompat(@FontRes fontRes: Int) = ResourcesCompat.getFont(this, fontRes)


    // ### Action menu sample ### ==============================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.example_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId) {
            R.id.menu_item1 -> {
                showToast("Menu Item 1")
                true
            }
            R.id.menu_group_item_1 -> {
                showToast("Group Item 1")
                true
            }
            R.id.menu_group_item_2 -> {
                showToast("Group Item 1")
                true
            }
            R.id.menu_submenu_item_1 -> {
                showToast("Sub Menu Item 1")
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }


    // Helper toast
    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}
