package nevette.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNote extends AppCompatActivity {

    EditText inputTextField;
    Button buttonSave, buttonClear;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_new_note);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                buttonClear.setText("");
            }
        });

        inputTextField = findViewById(R.id.input_text);

        inputTextField.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userInput = inputTextField.getText().toString();
                if (userInput.equals("Input text")){
                    inputTextField.setText("");
                }
            }
        });
    }
}
