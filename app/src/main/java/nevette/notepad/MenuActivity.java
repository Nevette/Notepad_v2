package nevette.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button buttonNewNote, buttonSavedNotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonNewNote= findViewById(R.id.button_add_note);
        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewNoteActivity();
            }
        });

        buttonSavedNotesList = findViewById(R.id.button_saved_notes);
        buttonSavedNotesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSavedNotesActivity();
            }
        });
    }

    public void openNewNoteActivity(){
        Intent i = new Intent(this, NewNote.class);
        startActivity(i);
    }

    public void openSavedNotesActivity(){
        Intent j = new Intent(this, SavedNotesActivity.class);
        startActivity(j);
    }
}
