package nevette.notepad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SavedNotesActivity extends AppCompatActivity{

    Context context;
    private LinearLayout savedNotesList;
    Database db;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saved_notes);

        context = this.getBaseContext();
        db = new Database(getBaseContext());

        savedNotesList = findViewById(R.id.notes_list);

        refresh();
    }

    public void refresh(){
        savedNotesList.removeAllViews();

        List<Note> notesList = new ArrayList<>();
        notesList.addAll(db.returnAllNotes());

        for (Note note: notesList){
            LinearLayout row = new LinearLayout(context);
            row.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            row.setOrientation(LinearLayout.HORIZONTAL);

            Button label = new Button(this);
            label.setBackgroundResource(R.drawable.light_round_button);
            label.setTextSize(18);
//            label.setTextColor(Color.BLACK);
            label.setClickable(true);
            label.setText(note.getTitle());
            savedNotesList.addView(row);
            row.addView(label);

            Button delete = new Button(this);
            delete.setBackgroundResource(R.drawable.round_button);
            delete.setTextSize(18);
            delete.setText("Delete");
            delete.setClickable(true);
            row.addView(delete);

            label.setOnClickListener(openNote(note));
            delete.setOnClickListener(deleteNote(note));
        }
    }

    private View.OnClickListener deleteNote(final Note note){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                db.deleteNote(note);
                refresh();
            }
        };
    }

    private View.OnClickListener openNote(final Note note){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view){
                newNote(note);
            }
        };
    }

    public void newNote(Note note){
        Intent i = new Intent(this, NewNoteActivity.class);
        i.putExtra("Note", note);
        startActivity(i);
    }

}
