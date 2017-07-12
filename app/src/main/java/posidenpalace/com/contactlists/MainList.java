package posidenpalace.com.contactlists;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainList extends AppCompatActivity {
    private static final String TAG = "Test";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator itemAnimator;
    List<Person> contacts = new ArrayList<>();
    private RecycleAdapter adapter;


    String Name;
    String Phone = "";
    String ImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.Menu);

        Uri contentURI = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String hasPictures = ContactsContract.Contacts.Photo.PHOTO_URI;
        String HasNumber = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

        int hasNumber = 0;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Phone = "";
                Name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                Log.d(TAG, "onCreate: " + Name);
                ImageUri = cursor.getString(cursor.getColumnIndex(hasPictures));
                Log.d(TAG, "onCreate: Image" + ImageUri);
                hasNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HasNumber)));
                if (hasNumber > 0) {
                    Uri PhoneURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String Number = ContactsContract.CommonDataKinds.Phone.NUMBER;

                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                    String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=?";
                    String[] selectionArgs = new String[]{Name};

                    Cursor phoneCursor = getContentResolver().query(PhoneURI
                            , projection
                            , selection
                            , selectionArgs
                            , null);

                    while (phoneCursor.moveToNext()) {
                        if (Phone == "") {
                            Phone = phoneCursor.getString(phoneCursor.getColumnIndex(Number));
                            Log.d(TAG, "getPhoneContacts: " + Phone);
                        }

                    }

                    contacts.add(new Person(Name, Phone, ImageUri));
                }
                layoutManager = new LinearLayoutManager(this);
                itemAnimator = new DefaultItemAnimator();
                adapter = new RecycleAdapter(contacts);

                recyclerView.setItemAnimator(itemAnimator);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }


    }


}
