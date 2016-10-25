package com.example.user.my;

import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity  {
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    ArrayList<String> rMessagesList = new ArrayList<String>();
    String[]  items;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    ArrayAdapter arrayAdapter;
    ListView listView_main;
   String query = new String();

    private static SearchResultsActivity inst;
    public static SearchResultsActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.search_results_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent searchIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(searchIntent.getAction())) {
             query = searchIntent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SearchableProvider.AUTHORITY, SearchableProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            getSupportActionBar().setTitle(query);

            Toast.makeText(SearchResultsActivity.this, query, Toast.LENGTH_SHORT).show();
            SearchRecentSuggestions searchRecentSuggestions =new SearchRecentSuggestions(this,SearchableProvider.AUTHORITY ,SearchableProvider.MODE);
            searchRecentSuggestions.saveRecentQuery(query, null);





                    listView_main = (ListView) findViewById(R.id.search_results);

          arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,smsMessagesList);

    refreshSmsInbox();


        }}




    private void refreshSmsInbox() {

        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();

        do {
            String str =   smsInboxCursor.getString(indexAddress) +
                    "\n" + smsInboxCursor.getString(indexBody) + "\n";




if (str.contains(query))
          arrayAdapter.add(str);


        } while (smsInboxCursor.moveToNext());
    //   arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,smsMessagesList)

        listView_main.setAdapter(arrayAdapter);
        for(String item:smsMessagesList){

            if(!item.contains(query)) {

                smsMessagesList.remove(item);

            }

        }

        arrayAdapter
                .notifyDataSetChanged();



   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            default:
                return false;
        }
    }

    }



