package nevette.notepad;

import java.io.Serializable;

public class Note implements Serializable {

    private Long id;
    private String title;
    private String content;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getTitile(){
        return title;
    }

    public String setTitle(String title){
        return title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
