package nevette.notepad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewNoteActivity extends AppCompatActivity {

    EditText inputTextField;
    Button buttonSave, buttonClear;
    Database db;
    Context context;

    private Note displayNote;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        displayNote = (Note) getIntent().getSerializableExtra("Note");
        context = getBaseContext();

        db = new Database(this.getBaseContext());

        buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inputTextField.setText("");
            }
        });

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String inputText = inputTextField.getText().toString();
                displayNote.setContent(inputText);

                if (inputText.length() > 20){
                    displayNote.setTitle(inputText.substring(0,19));
                }
                else {
                    displayNote.setTitle(inputText);
                }
                if (displayNote.getId() == null){
                    db.addNote(displayNote);
                }
                else {
                    db.saveNote(displayNote);
                }
                Toast toast = Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        inputTextField = findViewById(R.id.input_text);
        if (displayNote.getId() != null){
            inputTextField.setText(displayNote.getContent());
        }

        inputTextField.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String inputText = inputTextField.getText().toString();
                if (inputText.equals("Input text")){
                    inputTextField.setText("");
                }
            }
        });
    }
}
