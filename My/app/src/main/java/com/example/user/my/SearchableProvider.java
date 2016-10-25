package com.example.user.my;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by User on 24-10-2016.
 */
public class SearchableProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.user.my.SearchableProvider";
    public  static final int MODE = DATABASE_MODE_QUERIES;

    public SearchableProvider() {    // ALT+insert for constructer
       setupSuggestions(AUTHORITY ,MODE);
    }


}
