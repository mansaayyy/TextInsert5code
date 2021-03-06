package com.example.manasi.textinsert5;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DB_Controller db;
    EditText editText,editText2;
    TextView textView;
    Button insert,display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DB_Controller(this);
        editText=(EditText) findViewById(R.id.edit);
        editText2=(EditText) findViewById(R.id.edit2);
        textView=(TextView) findViewById(R.id.text);
        insert=(Button) findViewById(R.id.insert);
        display=(Button) findViewById(R.id.display);
    }

    public void insert(View v){

        String text=editText.getText().toString();
        String text2=editText2.getText().toString();
        db.insert_album(text,text2);
        editText.setText("");
        editText2.setText("");
        Toast.makeText(getApplicationContext(),"Here",Toast.LENGTH_SHORT).show();
    }

    public void Display(View v){
        textView.setText("");
        Cursor smsInboxCursor = db.list_album();
        while (smsInboxCursor.moveToNext()) {
            String str = smsInboxCursor.getString(0) + "     " + smsInboxCursor.getString(1) +
                    "\n";
            textView.append(str);
        }
    }

    public void Delete(View v) {
        textView.setText("");
        String text=editText.getText().toString();
        db.deleteEntry(text);
        Toast.makeText(getApplicationContext(),"Deleted!",Toast.LENGTH_SHORT).show();
        Display(v);
    }

    public void Update(View v) {
        String oldText=editText.getText().toString();
        String newText=editText2.getText().toString();
        db.updateEntry(oldText,newText);
        Toast.makeText(getApplicationContext(),"Updated!",Toast.LENGTH_SHORT).show();
        Display(v);
    }
}
