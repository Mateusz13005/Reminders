package com.example.reminders;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class RemindersActivity extends AppCompatActivity {
    private ListView mListView;
    private RemindersDbAdapter mDbAdapter;
    private RemindersSimpleCursorAdapter mCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        mListView = (ListView) findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);
        mDbAdapter = new RemindersDbAdapter(this);
        mDbAdapter.open();

        Cursor cursor = mDbAdapter.fetchAllReminders();
        // z kolumn zdefiniowanych w bazie danych
        String[] from = new String[]{
                RemindersDbAdapter.COL_CONTENT
        };
// do identyfikatorów widoków w układzie graficznym
        int[] to = new int[]{
                R.id.row_text
        };
        mCursorAdapter = new RemindersSimpleCursorAdapter(
// kontekst
                RemindersActivity.this,
// układ graficzny wiersza
                R.layout.reminders_row,
// kursor
                cursor,
// z kolumn zdefiniowanych w bazie danych
                from,
// do identyfikatorów widoków w układzie graficznym
                to,
// znacznik — nieużywany
                0);
// cursorAdapter (kontroler) aktualizuje ListView (widok)
// danymi z bazy danych (model)
        mListView.setAdapter(mCursorAdapter);
    }
// pozostała część klasy nie uległa zmianie, więc została pominięta


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                Log.d(getLocalClassName(),"utworzenie nowego przypomnienia");
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;
        }
    }


}
