package entities;


public class Comments{
    private String commenter;
    private String comment;
    private String comment_date;

    /**
     * Constructs a comment with commenter, comment and comment_date
     *
     * @param commenter the username who make the comment
     * @param comment the content of the comment
     * @param comment_date the date and time of the comment made.
     */
    public Comments(String commenter, String comment, String comment_date){
        this.commenter = commenter;
        this.comment=comment;
        this.comment_date=comment_date;

    }

    /**
     * Returns the commenter of the comment.
     *
     * @return the commenter of the comment.
     */
    public String getCommenter(){
        return commenter;
    }

    /**
     * Returns the content of the comment.
     *
     * @return the content of the comment.
     */
    public String getComment(){
        return comment;
    }

    /**
     * Returns the date and time of the comment made.
     *
     * @return the date and time of the comment made.
     */
    public String getComment_date(){
        return comment_date;
    }

    /**
     * Edit the content of the comment
     *
     * @param comment the content of the comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * Edit the date and time of the comment made.
     *
     * @param comment_date the date and time of the comment made.
     */
    public void setComment_date(String comment_date){
        this.comment_date = comment_date;
    }

    /**
     * Returns the string representation of the comment.
     *
     * @return the string representation of the comment
     */
    @Override
    public String toString(){
        return commenter + ";" + comment + ";" + comment_date;
    }
}