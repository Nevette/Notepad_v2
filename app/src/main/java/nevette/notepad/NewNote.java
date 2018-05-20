package nevette.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewNote extends AppCompatActivity {

    EditText input_text;
    Button button_save, button_clear;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_note);


    }
}
