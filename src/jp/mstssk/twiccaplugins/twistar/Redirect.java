package jp.mstssk.twiccaplugins.twistar;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Redirect extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = "http://twistar.cc/";
        String screen_name = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        if (screen_name != null && Pattern.matches("^[a-zA-Z0-9_]+$", screen_name)) {
            String[] tabs = getResources().getStringArray(R.array.tabs);
            int position = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(Configurations.KEY_TABS, Configurations.DEFAULT_TAB);
            url += screen_name + (position > 0 ? ("/" + tabs[position]) : "");
        }
        intent.setData(Uri.parse(url));

        startActivity(intent);
        finish();
        
    }
}