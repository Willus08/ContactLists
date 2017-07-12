package posidenpalace.com.contactlists;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class Contact extends AppCompatActivity  {
    ImageView imageView;
    TextView name;
    TextView phone;
    String  number;
    EditText mesg;
    private int My_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        name = (TextView) findViewById(R.id.tvName);
        phone = (TextView) findViewById(R.id.tvPhone);
        imageView = (ImageView) findViewById(R.id.ivPics);
        mesg = (EditText) findViewById(R.id.etMessage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Person person =  bundle.getParcelable("send");

        name.setText("Name: "+person.getName());
        phone.setText("Phone: "+person.getPhone());
        number = person.getPhone();
        if(person.getImageUri() != null) {
            imageView.setImageURI(Uri.parse(person.getImageUri()));
        }else{
            imageView.setImageResource(R.drawable.default_image);
        }
    }

    public void SMS(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},My_REQUEST);
        }
        else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, mesg.getText().toString(), null, null);
        }
    }

    public void Call(View view) {
        Uri num = Uri.parse("tel" + number);
        Intent phpneInente = new Intent(Intent.ACTION_CALL);
        phpneInente.setData(num);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.CALL_PHONE) != getPackageManager().PERMISSION_GRANTED) {
            return;
        }
        startActivity(phpneInente);
    }
}
