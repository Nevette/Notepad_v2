package nevette.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE = "Database";
    private static final Integer DATABASE_VERSION = 1;

    private static final String NOTES_TABLE = "notesTable";
    private static final String NOTE_ID = "id";
    private static final String NOTE_TITLE = "title";
    private static final String NOTE_CONTENT = "content";

    private static final String[] NOTES_COLUMN = {NOTE_ID, NOTE_TITLE, NOTE_CONTENT};

    public Database(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    public void addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.getTitile());
        values.put(NOTE_CONTENT, note.getContent());
        db.insert(NOTES_TABLE, null, values);
        db.close();
    }

    public Note downloadNote (Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(NOTES_TABLE, NOTES_COLUMN, "id = ?", new String[] {id.toString()}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = new Note();
        note.setId(Long.parseLong(cursor.getString(0)));
        note.setTitle(cursor.getString(1));
        note.setContent(cursor.getString(2));
        return note;
    }

    public void saveNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.getTitile());
        values.put(NOTE_CONTENT, note.getContent());

        db.update(NOTES_TABLE, values, "id = ?", new String[] {note.getId().toString()});
        db.close();
    }

    public void deleteNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + NOTES_TABLE + " where id = " + note.getId().toString();
        db.execSQL(sql);
        db.close();
    }

    public List<Note> returnAllNotes(){
        List<Note> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + NOTES_TABLE;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                notesList.add(note);
            }
            while (cursor.moveToNext());
        }
        return notesList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table notes " +
                "( id integer primary key autoincrement, " +
                "title text," +
                "content text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j){
        String sql = "drop table if exists notes";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
