package cn.lemene.BookTrace.module;

/**
 * Created by dimon on 16/12/26.
 */

public class Comment {
    String name;
    String content;

    public void Comment(String name, String content)
    {
        this.name = name;
        this.content = content;

    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

}
