# Buffup SDK for Android

This SDK allows you to integrate Buffs into your sports android apps.

![alt text][demo]

[demo]: https://github.com/emmanuelkehinde/BuffupSDK/blob/master/buffup-sdk-screencast.gif "Buffup SDK Demo"

## Installation

### Gradle

Add this in your root build.gradle
>
        allprojects {
          repositories {
            maven { url 'https://jitpack.io' }
          }
        }

Add this in your app build.gradle
>
        dependencies {
          implementation 'com.github.emmanuelkehinde:BuffupSDK:1.0.0'
        }

## Usage

**1.** The SDK exposes a custom view called `BuffView`, which is to be
integrated into the host application using `Relative Layout` as parent
layout.

>
        <com.emmanuelkehinde.buffup.view.BuffView
            android:id="@+id/buffView"    
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_alignParentBottom="true" />

**2.** In your activity/fragment, set up BuffView with the `ID` of the
present stream you are viewing.

>
        buffView.setupWithStream("STREAM_ID")

**3.** Then, call `start()` function on the BuffView instance to start
fetching and displaying Buffs.

>
        buffView.start(this)

**4.** Finally, initialize Buffup in your `Application` class by calling
the `initialize()` function, passing in the `AUTHENTICATION_KEY` you
obtained from Buffup.

>
        class App: Application() {

            override fun onCreate() {
                super.onCreate()
                
                Buffup.initialize("AUTH_KEY")
            }
        }

**5. (Optional)** Setup event listeners for your BuffView by calling
`addListener`.

>
        buffView.addListener(object : EventListener {
            override fun onBuffDisplayed(buff: Buff) {
                super.onBuffDisplayed(buff)
                Log.d(this.javaClass.simpleName, "Buff Displayed")
            }

            override fun onBuffAnswerSelected(answer: Buff.Answer) {
                super.onBuffAnswerSelected(answer)
                Log.d(this.javaClass.simpleName, "Answer selected: ${answer.title}")            
            }

            override fun onBuffError(t: Throwable) {
                super.onBuffError(t)
                Log.d(this.javaClass.simpleName, "Error: ${t.localizedMessage}")                        
            }
        })

## Customization

### XML

Feel free to customize your BuffView by adding attributes in your XML as
shown below.

>
        <com.emmanuelkehinde.buffup.view.BuffView
            android:id="@+id/buffView"    
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_alignParentBottom="true"
            app:buff_author_info_text_color="@color/white"                              //default to Black
            app:buff_answer_row_background="@drawable/buff_question_answer_layout_bg"   //default to light gray with alpha
            app:buff_answer_row_text_color="@color/white"                               //default to Black 
            app:buff_author_info_background="@drawable/buff_author_layout_bg"           //default to light gray with alpha
            app:buff_question_background="@drawable/buff_question_layout_bg"            //default to Black with alpha
            app:buff_selected_answer_row_text_color="@color/colorPrimary"               //default to White
            />

### Programmatically

You can also customize your BuffView programmatically as shown below.

>
        buffView.authorInfoBackground = ContextCompat.getDrawable(this, R.drawable.buff_author_layout_bg)
        buffView.authorInfoTextColor = ContextCompat.getColor(this, R.color.buff_text_dark) 
        buffView.questionTextColor = ContextCompat.getColor(this, R.color.white) 
        buffView.answerRowBackground = ContextCompat.getDrawable(this, R.drawable.buff_question_answer_layout_bg)
        buffView.selectedAnswerRowTextColor = ContextCompat.getColor(this, R.color.white) 

### List of Attributes

Here is a table showing all the available attributes and their format.

| Name                                         | format        |
| ---------------------------------------------|:-------------:|
| buff_author_info_background                  | integer       |
| buff_author_info_text_color                  | color         |
| buff_close_icon                              | integer       |
| buff_question_background                     | integer       |
| buff_answer_row_background                   | integer       |
| buff_selected_answer_row_background          | integer       |
| buff_answer_row_text_color                   | color         |
| buff_selected_answer_row_text_color          | color         |


## Architecture

Here is a list of tools, libraries, design patterns and architecture
uses in the project.

### SDK

* Kotlin - Programming Language
* HttpUrlConnection - For Network Request
* Couroutine - For asynchronous programming
* MotionLayout - For Swipe Button Animation
* Picasso - For Image loading
* Gson - For Serialization
* JUnit4, Mockito  - For Unit Testing
* Error Handling using throwable Exceptions

### Demo App

* Kotlin - Programming Language
* MVVM - As app architecture
* ViewModel - Provides data required by the fragment/activity
* LiveData - As observable data holder for the ViewModel
* JUnit4, Mockito  - For Unit Testing
* Expresso - For UI testing

## ToDo (Future Releases)

* Add support for more ViewGroups apart from RelativeLayout
* Reduce the number of library dependencies used further
* Add more customization features

## Written By:

####  [Emmanuel Kehinde](https://github.com/emmanuelkehinde)
