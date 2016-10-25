package com.example.user.my;
 import com.example.user.my.MainActivity2;
 import com.google.android.gms.common.api.GoogleApiClient;
 import com.google.android.gms.common.api.ResultCallback;
 import com.google.android.gms.drive.Drive;
 import com.google.android.gms.drive.DriveApi;
 import com.google.android.gms.drive.DriveContents;
 import com.google.android.gms.drive.DriveFile;
 import com.google.android.gms.drive.DriveId;
 import com.google.android.gms.drive.MetadataChangeSet;

 import android.app.Activity;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
 import android.content.IntentSender;
 import android.database.Cursor;
 import android.graphics.Bitmap;
 import android.net.Uri;
 import android.os.Parcel;
 import android.os.Parcelable;
 import android.provider.SearchRecentSuggestions;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ComponentName;

 import java.io.ByteArrayOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.OutputStreamWriter;
 import java.io.Writer;
 import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener  {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final long serialVersionUID = 1L;
    private static MainActivity inst;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    private static final String TAG = "Google Drive Activity";
    private GoogleApiClient mGoogleApiClient;
    private Bitmap mBitmapToSave;
    ListView smsListView ;
    ArrayAdapter arrayAdapter;
    private boolean fileOperation = false;
    private DriveId mFileId;
    public DriveFile file;
    private static final int REQUEST_CODE_CREATOR = 2;

    Button button;

    public static MainActivity instance()  {
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
        Log.i("LOG'", " - broadcast created");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.my_tb_title);
        getSupportActionBar().setIcon(R.drawable.ic_action_name2);


        smsListView = (ListView) findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessagesList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);
        refreshSmsInbox();



    }



    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) +
                    "\n" + smsInboxCursor.getString(indexBody) + "\n";


            arrayAdapter.add(str);
        } while (smsInboxCursor.moveToNext());
    }

    public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        try {
            String[] smsMessages = smsMessagesList.get(pos).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";
            for (int i = 1; i < smsMessages.length; ++i) {
                smsMessage += smsMessages[i];

            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;


            Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
            if(pos==0){
                Intent  a = new  Intent(view.getContext() , List.class);
                Bundle b = new Bundle();

                startActivity(a);}
            if(pos==1){
                Intent  b = new  Intent(view.getContext() , List.class);
                startActivity(b);
            } if(pos ==2){
                Intent  c = new  Intent(view.getContext() , List.class);
                startActivity(c);
            }
            if(pos==3){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==4){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==5){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==6){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==7){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==8){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==9){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==10){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==11){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==12){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==13){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==14){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==15){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==16){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==18){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==19){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==20){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==21){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==22){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==23){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==27){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==28){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==29){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==33){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==34){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==35){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==36){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==37){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==48){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==49){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==50){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==51){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==52){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==55){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==56){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==57){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==58){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==59){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==60){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==71){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==72){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==75){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==76){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==77){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==78){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==79){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==80){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==81){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==82){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==85){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==86){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==87){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==88){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==89){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==90){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==91){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==92){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==95){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==96){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==97){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==98){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==99){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==100){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==101){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==102){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==105){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==106){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==107){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==108){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==109){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==110){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==111){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==112){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==150){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==152){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==155){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==159){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==160){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==161){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==162){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==166){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==165){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==167){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==168){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==169){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==170){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==171){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==172){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==175){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==176){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==177){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==178){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==179){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==180){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==181){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==182){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==183){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==184){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==185){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==186){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==187){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==188){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==189){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}
            if(pos==190){
                Intent  m = new  Intent(view.getContext() , List.class);
                startActivity(m);}




        } catch (Exception e) {
            e.printStackTrace();
        }
    }







        @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);



       SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setQueryHint(Html.fromHtml("<font color = #fffffh>"
                + getResources().getString(R.string.hint) + "</font>"));

            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);



            return true ;


    }  @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu1:
                SearchRecentSuggestions searchRecentSuggestions= new SearchRecentSuggestions(this , SearchableProvider.AUTHORITY,SearchableProvider.MODE);
              searchRecentSuggestions.clearHistory();
                Toast.makeText(MainActivity.this , "Search History Clear clicked" , Toast.LENGTH_SHORT).show();

                break;
            case  R.id.menu2:
                Toast.makeText(MainActivity.this ,"option2 clicked" ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.compose:

                Toast.makeText(MainActivity.this ,"compose message" ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DisplayMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.drive:
                Toast.makeText(MainActivity.this ,"upload " ,Toast.LENGTH_SHORT).show();
                Intent in = new Intent(this, MainActivity2.class);
                startActivity(in);



        }
        return super.onOptionsItemSelected(item);

    }





}




