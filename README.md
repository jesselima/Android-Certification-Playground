# Android-Certification-Playground
Feet on the ground. Hands on the code.

Labels:
ongoing ⏩ ----- review 🔁 ----- done ✅ ----- stopped ❌ ----- warning❗----- questions❓

## Android core
======

### Application fundamentals

⏩   [Application fundamentals](https://developer.android.com/guide/components/fundamentals)

> Content from the [official documentation](https://developer.android.com/guide/components/fundamentals). It's just a copy and paste with some highlights. :stuck_out_tongue_winking_eye: It's also make my life easier we I need to review some technical stuff :blush:

Android apps can be written using **Kotlin, Java, and C++** languages. The Android SDK tools compile your code along with any data and resource files into an APK, an Android package, which is an archive file with an .apk suffix. One APK file contains all the contents of an Android app and is the file that Android-powered devices use to install the app.


> Each Android app lives in its own security **sandbox**, protected by the following Android security features: ...


###  App components

App components are the essential **building blocks** of an Android app. **Each component is an entry point through which the system or a user can enter your app**. Some components depend on others.

- Activities
- Services
- Broadcast receivers
- Content providers

>Each type serves a distinct purpose and has a distinct lifecycle that defines how the component is created and destroyed. The following sections describe the four types of app components.


##### Activities

An activity is the entry point for interacting with the user. It represents a single screen with a user interface.

You implement an activity as a subclass of the Activity class. For more information about the Activity class, see the [Activities developer guide](https://developer.android.com/guide/components/activities).


##### Services

A service **is a general-purpose entry point for keeping an app running in the background for all kinds of reasons**. It is a component that ***runs in the background to perform long-running operations or to perform work for remote processes***.

Another component, such as an activity, can start the service and let it run or bind to it in order to interact with it.

- **Music playback** is something the user is directly aware of, so the app tells the system this by saying it wants to be foreground with a notification to tell the user about it; in this case the system knows that it should try really hard to keep that service's process running, because the user will be unhappy if it goes away.
- **A regular background service** is not something the user is directly aware as running, so the system has more freedom in managing its process. ***It may allow it to be killed*** (and then restarting the service sometime later) if it needs RAM for things that are of more immediate concern to the user.

**Bound services** run because some other app (or the system) has said that it wants to make use of the service. This is basically the service providing an API to another process. The system thus knows there is a dependency between these processes, so if process A is bound to a service in process B, it knows that it needs to keep process B (and its service) running for A. Further, if process A is something the user cares about, then it also knows to treat process B as something the user also cares about.

A service is implemented as a subclass of Service. For more information about the Service class, see the [Services developer guide](https://developer.android.com/guide/components/services).

:star: **Note:** If your app targets Android 5.0 (API level 21) or later, use the JobScheduler class to schedule actions. [JobScheduler](https://developer.android.com/reference/android/app/job/JobScheduler) has the advantage of conserving battery by optimally scheduling jobs to reduce power consumption, and by working with the [Doze](https://developer.android.com/training/monitoring-device-state/doze-standby) API. For more information about using this class, see the [JobScheduler reference documentation](https://developer.android.com/reference/android/app/job/JobScheduler).


##### Broadcast receivers

A broadcast receiver is a component that enables the system to deliver events to the app outside of a regular user flow, allowing the app to respond to system-wide broadcast announcements.

:small_blue_diamond: Because broadcast receivers are another well-defined entry into the app, **the system can deliver broadcasts even to apps that aren't currently running**.

Many broadcasts originate from the system. Apps can also initiate broadcasts.

Although broadcast receivers don't display a user interface, they may create a status bar notification to alert the user when a broadcast event occurs. More commonly, though, a broadcast receiver is just a gateway to other components and is intended to do a very minimal amount of work. For instance, it might schedule a [JobService](https://developer.android.com/reference/android/app/job/JobService) to perform some work based on the event with [JobScheduler](https://developer.android.com/reference/android/app/job/JobScheduler)

A broadcast receiver is implemented as a subclass of [BroadcastReceiver](https://developer.android.com/reference/android/content/BroadcastReceiver) and each broadcast is delivered as an Intent object. For more information, see the BroadcastReceiver class.


##### Content providers

**A content provider manages a shared set of app data** that you can store in the file system, in a SQLite database, on the web, or on any other persistent storage location that your app can access. Through the content provider, *other apps can query or modify the data if the content provider allows it*. For example, **the Android system provides a content provider that manages the user's contact information**. *As such, any app with the proper permissions can query the content provider, such as ContactsContract.Data, to read and write information about a particular person*.

:eight_spoked_asterisk:__ It is tempting to think of a content provider as an abstraction on a database, because there is a lot of API and support built in to them for that common case. However, they have a different core purpose from a system-design perspective. **To the system, a content provider is an entry point into an app for publishing named data items, identified by a URI scheme**. Thus an app can decide how it wants to map the data it contains to a URI namespace, handing out those URIs to other entities which can in turn use them to access the data. There are a few particular things this allows the system to do in managing an app:


- **Assigning a URI doesn't require that the app remain running**, so URIs can persist after their owning apps have exited. The system only needs to make sure that an owning app is still running when it has to retrieve the app's data from the corresponding URI.

- **These URIs also provide an important fine-grained security model**. For example, an app can place the URI for an image it has on the clipboard, but leave its content provider locked up so that other apps cannot freely access it. When a second app attempts to access that URI on the clipboard, the system can allow that app to access the data via a temporary URI permission grant so that it is allowed to access the data only behind that URI, but nothing else in the second app.

:bulb:
 >Content providers are also useful for reading and writing data that is private to your app and not shared.

A content provider is implemented as a subclass of ContentProvider and must implement a standard set of APIs that enable other apps to perform transactions. For more information, see the [Content Providers developer guide](https://developer.android.com/guide/topics/providers/content-providers).


**A unique aspect of the Android system design is that any app can start another app’s component**. For example, if you want the user to capture a photo with the device camera, there's probably another app that does that and your app can use it instead of developing an activity to capture a photo yourself. You don't need to incorporate or even link to the code from the camera app. Instead, you can simply start the activity in the camera app that captures a photo. When complete, the photo is even returned to your app so you can use it. To the user, it seems as if the camera is actually a part of your app.

**When the system starts a component, it starts the process for that app if it's not already running and instantiates the classes needed for the component**. For example, if your app starts the activity in the camera app that captures a photo, that activity runs in the process that belongs to the camera app, not in your app's process. Therefore, unlike apps on most other systems, Android apps don't have a single entry point (there's no main() function).

:pencil: Because the system runs each app in a separate process with file permissions that restrict access to other apps, **your app cannot directly activate a component from another app. However, the Android system can**. To activate a component in another app, deliver a message to the system that specifies your intent to start a particular component. The system then activates the component for you.


### Activating components

:pencil:Three of the four component types — **activities, services, and broadcast receivers** — **are activated by an asynchronous message called an intent**. Intents bind individual components to each other at runtime. You can think of them as the messengers that request an action from other components, whether the component belongs to your app or another.

>Intents bind individual components to each other at runtime. You can think of them as the messengers that request an action from other components, whether the component belongs to your app or another.

>An [Intent](https://developer.android.com/reference/android/content/Intent) is created with an Intent object, which defines a message to activate either a **specific component** (explicit intent) or a **specific type** of component (implicit intent).

**For activities and services**, an intent defines the action to perform (for example, to view or send something) and may specify the URI of the data to act on, among other things that the component being started might need to know. For example, an intent might convey a request for an activity to show an image or to open a web page. In some cases, you can start an activity to receive a result, in which case the activity also returns the result in an [Intent](https://developer.android.com/reference/android/content/Intent). For example, *you can issue an intent to let the user pick a personal contact and have it returned to you. The return intent includes a URI pointing to the chosen contact*.

For **broadcast receivers**, *the intent simply defines the announcement being broadcast*. For example, a broadcast to indicate the device battery is low includes only a known action string that indicates battery is low.


Unlike activities, services and broadcast receivers, **content providers are not activated by intents**. Rather, **they are activated when targeted by a request from a [ContentResolver](https://developer.android.com/reference/android/content/ContentResolver)**. *The content resolver handles all direct transactions with the content provider so that the component that's performing transactions with the provider doesn't need to and instead calls methods on the ContentResolver object*. **This leaves a layer of abstraction between the content provider and the component requesting information (for security)**.


>There are separate methods for activating each type of component:

- You can start an activity or give it something new to do by passing an Intent to **startActivity()** or **startActivityForResult()** (when you want the activity to return a result).

- With Android 5.0 (API level 21) and later, you can use the [JobScheduler](https://developer.android.com/reference/android/app/job/JobScheduler) class to schedule actions. For earlier Android versions, you can start a service (or give new instructions to an ongoing service) by passing an Intent to startService(). You can bind to the service by passing an Intent to bindService().

- You can initiate a broadcast by passing an Intent to methods such as [sendBroadcast()](https://developer.android.com/reference/android/content/Context#sendBroadcast(android.content.Intent)), [sendOrderedBroadcast()](https://developer.android.com/reference/android/content/Context#sendOrderedBroadcast(android.content.Intent,%20java.lang.String)), or [sendStickyBroadcast()](https://developer.android.com/reference/android/content/Context#sendStickyBroadcast(android.content.Intent)).

- You can perform a [query](https://developer.android.com/reference/android/content/ContentProvider#query(android.net.Uri,%20java.lang.String[],%20android.os.Bundle,%20android.os.CancellationSignal)) to a content provider by calling query() on a [ContentResolver](https://developer.android.com/reference/android/content/ContentResolver).


##### The manifest file

> The primary task of the manifest is to inform the system about the app's components.

You must declare all app components using the following elements:

- < activity > elements for activities.
- < service  > elements for services.
- < receiver > elements for broadcast receivers.
- < provider > elements for content providers.

> :pencil: Activities, services, and content providers that you include in your source but do not declare in the manifest are not visible to the system and, consequently, can never run. **However, broadcast receivers can be either declared in the manifest or created dynamically in code** as BroadcastReceiver objects and registered with the system by calling registerReceiver().

>Before the Android system can start an app component, the system must know that the component exists by reading the app's manifest file, AndroidManifest.xml. Your app must declare all its components in this file, which must be at the root of the app project directory.

The manifest does a number of things in addition to declaring the app's components, such as the following:

- Identifies any user permissions the app requires, such as Internet access or read-access to the user's contacts.

- Declares the minimum API Level required by the app, based on which APIs the app uses.

- Declares hardware and software features used or required by the app, such as a camera, bluetooth services, or a multitouch screen.

- Declares API libraries the app needs to be linked against (other than the Android framework APIs), such as the Google Maps library.

For more about how to structure the manifest file for your app, see The [AndroidManifest.xml File documentation](https://developer.android.com/guide/topics/manifest/manifest-intro).


###### Declaring component capabilities

> As discussed above, in Activating components, you can use an Intent to start activities, services, and broadcast receivers. You can use an Intent by explicitly naming the target component (using the component class name) in the intent. You can also use an implicit intent, which describes the type of action to perform and, optionally, the data upon which you’d like to perform the action. The implicit intent allows the system to find a component on the device that can perform the action and start it. If there are multiple components that can perform the action described by the intent, the user selects which one to use.

:pushpin: **CAUTION:** **If you use an intent to start a Service, ensure that your app is secure by using an explicit intent**. Using an implicit intent to start a service is a security hazard because you cannot be certain what service will respond to the intent, and the user cannot see which service starts. Beginning with Android 5.0 (API level 21), the system throws an exception if you call bindService() with an implicit intent. Do not declare intent filters for your services.


>The system identifies the components that can respond to an intent by comparing the intent received to the intent filters provided in the manifest file of other apps on the device.


:pencil: When you declare an activity in your app's manifest, y*ou can optionally include intent filters that declare the capabilities of the activity so it can respond to intents from other apps*. You can declare an intent filter for your component by adding an <intent-filter> element as a child of the component's declaration element.

For example, if you build an email app with an activity for composing a new email, you can declare an intent filter to respond to "send" intents (in order to send a new email), as shown in the following example:



    <manifest ... >
        ...
        <application ... >
            <activity android:name="com.example.project.ComposeEmailActivity">
                <intent-filter>
                    <action android:name="android.intent.action.SEND" />
                    <data android:type="*/*" />
                    <category android:name="android.intent.category.DEFAULT" />
                </intent-filter>
            </activity>
        </application>
    </manifest>


If another app creates an intent with the ACTION_SEND action and passes it to startActivity(), the system may start your activity so the user can draft and send an email.

For more about creating intent filters, see the [Intents and Intent Filters document](https://developer.android.com/guide/components/intents-filters).


###### Declaring app requirements

There are a variety of devices powered by Android and not all of them provide the same features and capabilities. To prevent your app from being installed on devices that lack features needed by your app, it's important that you clearly define a profile for the types of devices your app supports by declaring device and software requirements in your manifest file. Most of these declarations are informational only and the system does not read them, but external services such as Google Play do read them in order to provide filtering for users when they search for apps from their device.

For example, if your app requires a camera and uses APIs introduced in Android 2.1 (API Level 7), you must declare these as requirements in your manifest file as shown in the following example:

    <manifest ... >
        <uses-feature android:name="android.hardware.camera.any"
                      android:required="true" />
        <uses-sdk android:minSdkVersion="7" android:targetSdkVersion="19" />
        ...
    </manifest>

:pencil:With the declarations shown in the example, devices that do not have a camera or have an Android version lower than 2.1 cannot install your app from Google Play. However, you can declare that your app uses the camera, but does not **require** it. In that case, your app must set the **required** attribute to false and check at runtime whether the device has a camera and disable any camera features as appropriate.

More information about how you can manage your app's compatibility with different devices is provided in the Device Compatibility document.




#### App resources
___


An Android app is composed of more than just code—it requires resources that are separate from the source code, such as **images, audio files, and anything relating to the visual presentation of the app**. For example, **you can define animations, menus, styles, colors, and the layout of activity user interfaces with XML files**. Using app resources makes it easy to update various characteristics of your app without modifying code. *Providing sets of alternative resources enables you to optimize your app for a variety of device configurations, such as different languages and screen sizes*.

For every resource that you include in your Android project, the SDK build tools define a unique integer ID, which you can use to reference the resource from your app code or from other resources defined in XML. For example, i*f your app contains an image file named logo.png (saved in the res/drawable/ directory), the SDK tools generate a resource ID named R.drawable.logo. This ID maps to an app-specific integer*, which you can use to reference the image and insert it in your user interface.

**One of the most important aspects of providing resources separate from your source code is the ability to provide alternative resources for different device configurations**. For example, by defining UI strings in XML, you can translate the strings into other languages and save those strings in separate files. Then Android applies the appropriate language strings to your UI based on a language qualifier that you append to the resource directory's name (such as res/values-fr/ for French string values) and the user's language setting.

Android supports many different qualifiers for your alternative resources. The qualifier is a short string that you include in the name of your resource directories in order to define the device configuration for which those resources should be used. For example, you should create different layouts for your activities, depending on the device's screen orientation and size. When the device screen is in portrait orientation (tall), you might want a layout with buttons to be vertical, but when the screen is in landscape orientation (wide), the buttons could be aligned horizontally. **To change the layout depending on the orientation, you can define two different layouts and apply the appropriate qualifier to each layout's directory name**. *Then, the system automatically applies the appropriate layout depending on the current device orientation*.

For more about the different kinds of resources you can include in your application and how to create alternative resources for different device configurations, read [Providing Resources](https://developer.android.com/guide/topics/resources/providing-resources). To learn more about best practices and designing robust, production-quality apps, see [Guide to App Architecture](https://developer.android.com/topic/libraries/architecture/guide).



##### GitHub Icons

:eight_spoked_asterisk:

:pencil:

:pushpin:

:calling:

:tada:

:bulb: