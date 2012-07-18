package rahul.example.facebookpost;

import com.facebook.android.Facebook;


public class Global {

    
    public static final String FACEBOOK_APP_ID = <<your fb app id>>;
    public static final String FB_TOKEN = "fb_token"; 
    public static final Facebook facebook = new Facebook(FACEBOOK_APP_ID);
    
    public static String fb_prpoertis =   "{\"Download it from\":{\"href\":\"https://play.google.com/\",\"text\":\"Google PLay Store\"},\"Download it from:\":{\"href\":\"http://itunes.apple.com/\",\"text\":\"iTunes AppStore\"}}";
}
