package rahul.example.facebookpost;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MyFbPost {
    
    Activity context;
    public MyFbPost(Activity ctx){
        
        context = ctx;
    }

    public void postOnFb(){

        final SharedPreferences mPrefs = context.getSharedPreferences(Global.FB_TOKEN, Context.MODE_PRIVATE);
        /*
         * Get existing access_token if any
         */

        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
        if (access_token != null) {
            Global.facebook.setAccessToken(access_token);

            Log.e("Access Token", Global.facebook.getAccessToken().toString());
        }
        if (expires != 0) {
            Global.facebook.setAccessExpires(expires);
        }

        /*
         * Only call authorise if the access_token has expired.
         */
        if (!Global.facebook.isSessionValid()) {

            try {
                Global.facebook.authorize(context, new String[] {
                    "user_about_me,user_birthday,email,publish_stream,offline_access,user_checkins,publish_checkins"
                }, new DialogListener() {
                    @Override
                    public void onComplete(Bundle values) {
                        SharedPreferences.Editor editor = mPrefs.edit();
                        editor.putString("access_token", Global.facebook.getAccessToken());
                        editor.putLong("access_expires", Global.facebook.getAccessExpires());
                        Log.e("Access Token", Global.facebook.getAccessToken().toString());
                        editor.commit();

                    }

                    @Override
                    public void onFacebookError(FacebookError error) {
                    }

                    @Override
                    public void onError(DialogError e) {
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
        try {
            String response = Global.facebook.request("me");
            Bundle parameters = new Bundle();
            parameters.putString("link", "http://www.google.com");
            parameters.putString("access_token", Global.facebook.getAccessToken());
            parameters.putString("picture", "http://newtech.aurum3.com/images/google-chrome-logo-2011.jpg");
            parameters.putString("name", "App name will go here");
            parameters.putString("caption", "Caption will go here");

            parameters.putString("description",
                    "Description will go here");
            parameters.putString("properties", Global.fb_prpoertis);

            response = Global.facebook.request("me/feed", parameters, "POST");
            Log.d("Tests", "got response: " + response);

            if (response == null || response.equals("") || response.equals("false")) {
                Log.v("Error", "Blank response");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("facebook post activity called");
      
    
    }
}
