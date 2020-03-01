# Android-Certification-Playground
**Feet on the ground. Hands on the code.**

Labels:

ongoing ⏩ ----- review 🔁 ----- done ✅ ----- stopped ❌ ----- warning❗----- questions❓

### Android core

✅   Toasts

✅   Snackbar

====================

⏩   [Localize your app](https://developer.android.com/guide/topics/resources/localization)


**Directory and Resource Type Description**

[Checkout official documentation](https://developer.android.com/guide/topics/resources/providing-resources)

**animator/**  
XML files that define property animations.

**anim/**  
XML files that define tween animations. (Property animations can also be saved in this directory, but the animator/ directory is preferred for property animations to distinguish between the two types.)

**color/**  
XML files that define a state list of colors. See Color State List Resource

**drawable/**  
Bitmap files (.png, .9.png, .jpg, .gif) or XML files that are compiled into the following drawable resource subtypes:

                Bitmap files
                Nine-Patches (re-sizable bitmaps)
                State lists
                Shapes
                Animation drawables
                Other drawables

**mipmap/**  
Drawable files for different *launcher icon* densities. For more information on managing launcher icons with mipmap/ folders, see Managing Projects Overview.

**layout/**  
XML files that define a user interface layout. See Layout Resource.

**menu/**  
XML files that define app menus, such as an Options Menu, Context Menu, or Sub Menu. See Menu Resource.

**raw/**  
Arbitrary files to save in their raw form. *To open these resources with a raw InputStream, call* **Resources.openRawResource()** with the resource ID, which is R.raw.filename.

                However, if you need access to original file names and file hierarchy, you might consider saving some resources in the assets/ directory (instead of res/raw/). Files in assets/ aren't given a resource ID, **so you can read them only using AssetManager**.

**values/**  
XML files that contain simple values, such as strings, integers, and colors.

                Whereas XML resource files in other res/ subdirectories define a single resource based on the XML filename, files in the values/ directory describe multiple resources. For a file in this directory, each child of the <resources> element defines a single resource. For example, a <string> element creates an R.string resource and a <color> element creates an R.color resource.
                
                Because each resource is defined with its own XML element, you can name the file whatever you want and place different resource types in one file. However, for clarity, you might want to place unique resource types in different files. For example, here are some filename conventions for resources you can create in this directory:
                
                arrays.xml for resource arrays (typed arrays).
                colors.xml for color values
                dimens.xml for dimension values.
                strings.xml for string values.
                styles.xml for styles.

**xml/** 	
Arbitrary XML files that can be read at runtime by calling **Resources.getXML()**. Various XML configuration files must be saved here, such as a searchable configuration.

**font/** 	
Font files with extensions such as **.ttf, .otf, or .ttc, or XML files that include a <font-family> element**. For more information about fonts as resources, go to Fonts in XML.



[Localization Checklist](https://developer.android.com/distribute/best-practices/launch/localization-checklist)


- **Research target languages and locales** . Identify countries where there is a good market opportunity and where you can provide appropriate user support.
    
- **Use Google Play data to find localization opportunities**. If your app is live, use the user acquisition performance report in the Play Console to identify countries where you haven’t localized but are seeing many visits to the Play store. These visits could indicate interest in your app in that country and an opportunity to increase installs by localizing.
    
- **Design your app for localization** . Use a single set of flexible layouts with alternative layouts where needed. Support right-to-left text, use system-provided formats for dates, times, numbers, and currencies, and include a full set of default resources.
    
- **Optimize your app if you’re targeting emerging markets**. Review the Building for Billions best practices on how to optimize Android apps for low- and no-bandwidth connectivity and low-cost devices.
    
- **Manage your app’s UI strings** . To prepare for translation move all strings into strings.xml, follow the Material Design guidelines on writing style, provide sufficient context information for declared strings, and mark content that should not be translated.
    
- **Translate your app, store listing, and other resources** . Solicit translations from your users, find a translation agency yourself, or use the app translation service from the Google Play Console or Android Studio to request a reliable and efficient translation.
    
- **Test your localized app** . Make sure you can test on the makes and models common in the markets you’re targeting. Check the UI thoroughly for formatting and presentation issues and where possible get native speakers to review.

- **Run an open test** . Plan an open test in key countries before launch to get real-world feedback from users.
    
- **Plan for international marketing**. Prepare to run an app install campaign and other regional or country-specific marketing from launch. Use the Google Play badge generator to build localized badges that you can use on websites or marketing materials. Generate new device art for promotional material with screenshots from your new localization.
    
- **Check your Optimization Tips**. Check whether you’re missing parts of your localized store listing and get other helpful reminders for a successful localized launch. Launch the Play Console.
    
- **Final checks and publishing** . First, go back and double check you’ve done everything in this list. Now you’re ready to launch or promote your app in your target country.
    
- **Support international users after launch**. At a minimum watch your ratings, reviews, and download stats carefully to find issues that could affect users. Consider creating language specific user groups or forums, if manageable.
    
- **Browse and reply to user reviews** . Understand what users think of your app and reply to their reviews to address any issues you might not have spotted in testing.
    
- **Run store listing experiments** . When visits to your store listing from your new markets start growing, run a localized store listing experiment to see what text and graphics work best. You can run up to 5 at the same time.




====================

Application fundamentals

Create a notification

AndroidX overview

Getting started with Jetpack

Android KTX (Kotlin)

Codelabs -> Notifications

Codelabs -> JobScheduler

Codelabs -> WorkManager (Java)

Codelabs -> WorkManager (Kotlin)
