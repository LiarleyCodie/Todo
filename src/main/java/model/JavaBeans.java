package model;

public class JavaBeans
{
    private int     id;
    private String  title;
    private boolean isDone;

    public JavaBeans()
    {

    }

    public JavaBeans(String title, boolean isDone)
    {
        this.title  = title;
        this.isDone = isDone;
    }

    public JavaBeans(int id, String title, boolean isDone)
    {
        this.id     = id;
        this.title  = title;
        this.isDone = isDone;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean getIsDone()
    {
        return isDone;
    }

    public void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }

}
