# Android-Certification-Playground
Feet on the ground. Hands on the code.

Labels:
ongoing ⏩ ----- review 🔁 ----- done ✅ ----- stopped ❌ ----- warning❗----- questions❓

### Android core

:fast_forward: ______         [AndroidX overview](https://developer.android.com/jetpack/androidx)


**Artifacts within the androidx namespace** comprise the Android Jetpack libraries. Like the Support Library, libraries in the androidx namespace ship separately from the Android platform and provide backward compatibility across Android releases.

AndroidX is a major improvement to the original Android Support Library, which is no longer maintained. androidx packages fully replace the Support Library by providing feature parity and new libraries.

In addition, AndroidX includes the following features:

- All packages in AndroidX live in a consistent namespace starting with the string androidx. **The Support Library packages have been mapped into corresponding androidx.* packages**. For a full mapping of all the old classes and build artifacts to the new ones, see the [Package Refactoring page](https://developer.android.com/jetpack/androidx/refactor).

- **Unlike the Support Library, androidx packages are separately maintained and updated**. The androidx packages use strict Semantic Versioning, starting with version 1.0.0. **You can update AndroidX libraries in your project independently**.

- **Version 28.0.0 is the last release of the Support Library**. There will be no more android.support library releases. ***All new feature development will be in the androidx namespace***.

##### Using androidx libraries in your project

See [Migrating to AndroidX](https://developer.android.com/jetpack/androidx/migrate) to learn how to migrate an existing project.

If you want to use androidx-namespaced libraries in a new project, you need to set the compile SDK to Android 9.0 (API level 28) or higher and set both of the following Android Gradle plugin flags to true in your gradle.properties file.

- **android.useAndroidX**: When this flag is set to true, the Android plugin uses the appropriate AndroidX library instead of a Support Library. The flag is false by default if it is not specified.

- **android.enableJetifier**: When this flag is set to true, the Android plugin automatically migrates existing third-party libraries to use AndroidX dependencies by rewriting their binaries. The flag is false by default if it is not specified.

All the packages and classes in the androidx namespace can be found in the [AndroidX reference section](https://developer.android.com/reference/androidx/packages).


######

#
##### GitHub Icons

:eight_spoked_asterisk:

:pencil:

:pushpin:

:calling:

:tada:

:bulb: