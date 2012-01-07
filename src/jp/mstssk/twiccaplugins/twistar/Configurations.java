package jp.mstssk.twiccaplugins.twistar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

public class Configurations extends Activity 
        implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    
    public static final String KEY_TABS = "tabs";
    public static final int DEFAULT_TAB = 3; // default is "faved"
    
    private SharedPreferences preference;
    private Spinner spinner_tabs;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparent);
        
        preference = PreferenceManager.getDefaultSharedPreferences(this);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        int position = preference.getInt(KEY_TABS, DEFAULT_TAB);
        View view = LayoutInflater.from(this).inflate(R.layout.setting_dialog, null);
        spinner_tabs = (Spinner)view.findViewById(R.id.spiner_tabs);
        spinner_tabs.setSelection(position);
        
        new AlertDialog.Builder(this)
            .setView(view)
            .setPositiveButton(android.R.string.ok, this)
            .setNegativeButton(android.R.string.cancel, this)
            .show().setOnDismissListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == AlertDialog.BUTTON_POSITIVE) {
            int selected = spinner_tabs.getSelectedItemPosition();
            SharedPreferences.Editor editor = preference.edit();
            editor.putInt(KEY_TABS, selected);
            editor.commit();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        finish();
    }
}
