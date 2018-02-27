package com.dartmouth.kd.devents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;



// Display a campus event
public class DisplayEventActivity extends Activity {

    private static final int MENU_ID_DELETE = 0;
    @SuppressWarnings("unused")
    private static final int MENU_ID_UPDATE = 1;

    private long mEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        //mContext = this;

        Bundle extras = getIntent().getExtras();

        if ( extras != null){
            mEventId = extras.getLong(Globals.KEY_ROWID);
            ((EditText) findViewById(R.id.editDispTitle)).setText(extras.getString(Globals.KEY_TITLE));
            ((EditText) findViewById(R.id.editDispDate)).setText(extras.getString(Globals.KEY_DATE));
            ((EditText) findViewById(R.id.editDispStart)).setText(extras.getString(Globals.KEY_START));
            ((EditText) findViewById(R.id.editDispEnd)).setText(extras.getString(Globals.KEY_END));
            ((EditText) findViewById(R.id.editDispLocation)).setText(extras.getString(Globals.KEY_LOCATION));
            ((EditText) findViewById(R.id.editDispDescription)).setText(extras.getString(Globals.KEY_DESCRIPTION));
            ((EditText) findViewById(R.id.editDispUrl)).setText(extras.getString(Globals.KEY_URL));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuItem menuitem;
        menuitem = menu.add(Menu.NONE, MENU_ID_DELETE, MENU_ID_DELETE, "Delete");
        menuitem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_DELETE:
                CampusEventDbHelper campusEventDbHelper = new CampusEventDbHelper(this);
                campusEventDbHelper.removeEvent(mEventId);

                finish();
                return true;

            default:
                finish();
                return false;
        }
    }
}

