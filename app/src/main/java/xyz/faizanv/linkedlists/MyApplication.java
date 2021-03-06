package xyz.faizanv.linkedlists;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseInstallation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by faizanv on 11/7/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "HjR2ViTLQ9u8UAzd4Cw29SHVAEFlRmDOZ5sRdlDd", "WBVeDnGpl6joe0jchzhF58VGw3jQHMCtGBWoQBTC");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        FacebookSdk.sdkInitialize(getApplicationContext());

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "xyz.faizanv.linkedlists",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
