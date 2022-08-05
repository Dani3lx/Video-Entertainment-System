package entities;


public class Comments{
    private String commenter;
    private String comment;
    private String comment_date;
//    private Ratings r;
    //add ratings to the comment later

    public Comments(String commenter, String comment, String comment_date){
        this.commenter = commenter;
        this.comment=comment;
        this.comment_date=comment_date;

    }

    public String getCommenter(){
        return commenter;
    }

    public String getComment(){
        return comment;
    }

    public String getComment_date(){
        return comment_date;
    }

    public void setCommenter(String commenter){
        this.commenter = commenter;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setComment_date(String comment_date){
        this.comment_date = comment_date;
    }
}