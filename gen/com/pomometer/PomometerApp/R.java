/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.pomometer.PomometerApp;

public final class R {
    public static final class attr {
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarButtonStyle=0x7f010001;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarStyle=0x7f010000;
    }
    public static final class color {
        public static final int black=0x7f040005;
        public static final int black_overlay=0x7f040000;
        public static final int green_background=0x7f040004;
        public static final int red_background=0x7f040001;
        public static final int red_foreground=0x7f040002;
        public static final int white_text=0x7f040003;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f050000;
        public static final int activity_vertical_margin=0x7f050001;
        /**  Font sizes 
         */
        public static final int font_large=0x7f050004;
        public static final int font_medium=0x7f050005;
        public static final int font_small=0x7f050006;
        public static final int page_title_text_size=0x7f050002;
        public static final int row_text_size=0x7f050003;
        public static final int timer_text_size=0x7f050007;
    }
    public static final class drawable {
        public static final int ic_launcher=0x7f020000;
    }
    public static final class id {
        public static final int break_length=0x7f09000d;
        public static final int btn_finish_dialog=0x7f09000e;
        public static final int durationAndBreakLength=0x7f09000f;
        public static final int duration_length=0x7f09000c;
        public static final int duration_picker=0x7f090009;
        public static final int duration_subtitle=0x7f090008;
        public static final int goal_entry=0x7f090007;
        public static final int goal_subtitle=0x7f090006;
        public static final int page_list_content=0x7f090005;
        public static final int page_list_options_frame=0x7f090004;
        public static final int page_list_scroll_view=0x7f090003;
        public static final int page_title=0x7f090001;
        public static final int page_title_separator=0x7f090002;
        public static final int pomo_timer=0x7f09000b;
        public static final int start_button=0x7f09000a;
        public static final int timer_layout=0x7f090000;
    }
    public static final class layout {
        public static final int activity_list_layout=0x7f030000;
        public static final int activity_pomometer_options=0x7f030001;
        public static final int activity_pomometer_timer=0x7f030002;
        public static final int duration_and_break_length=0x7f030003;
    }
    public static final class menu {
        public static final int project_list=0x7f080000;
    }
    public static final class string {
        public static final int action_settings=0x7f060001;
        public static final int add_a_duration=0x7f060006;
        public static final int add_a_goal=0x7f060005;
        public static final int app_name=0x7f060000;
        public static final int break_length_header=0x7f060009;
        public static final int duration_length_header=0x7f060008;
        public static final int hello_world=0x7f060002;
        public static final int length_measurement=0x7f06000a;
        public static final int options_button_name=0x7f06000b;
        public static final int project_list_button_new=0x7f060004;
        public static final int project_list_title=0x7f060003;
        public static final int start_pomodoro=0x7f060007;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.
    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.
        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.
    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f070000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f070001;
    }
    public static final class styleable {
        /** 
         Declare custom theme attributes that allow changing which styles are
         used for button bars depending on the API level.
         ?android:attr/buttonBarStyle is new as of API 11 so this is
         necessary to support previous API levels.
    
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarButtonStyle com.pomometer.PomometerApp:buttonBarButtonStyle}</code></td><td></td></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarStyle com.pomometer.PomometerApp:buttonBarStyle}</code></td><td></td></tr>
           </table>
           @see #ButtonBarContainerTheme_buttonBarButtonStyle
           @see #ButtonBarContainerTheme_buttonBarStyle
         */
        public static final int[] ButtonBarContainerTheme = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link com.pomometer.PomometerApp.R.attr#buttonBarButtonStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name android:buttonBarButtonStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarButtonStyle = 1;
        /**
          <p>This symbol is the offset where the {@link com.pomometer.PomometerApp.R.attr#buttonBarStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name android:buttonBarStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarStyle = 0;
    };
}
