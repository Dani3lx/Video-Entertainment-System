package entities;

/**
 * This represents a video comment.
 */
public class Comments {
    private final String commenter;
    private String comment;
    private String commentDate;

    /**
     * Constructs a comment with commenter, comment and comment_date
     *
     * @param commenter   the username who make the comment
     * @param comment     the content of the comment
     * @param commentDate the date and time of the comment made.
     */
    public Comments(String commenter, String comment, String commentDate) {
        this.commenter = commenter;
        this.comment = comment;
        this.commentDate = commentDate;

    }

    /**
     * Returns the commenter of the comment.
     *
     * @return the commenter of the comment.
     */
    public String getCommenter() {
        return commenter;
    }

    /**
     * Returns the content of the comment.
     *
     * @return the content of the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Edit the content of the comment
     *
     * @param comment the content of the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the date and time of the comment made.
     *
     * @return the date and time of the comment made.
     */
    public String getCommentDate() {
        return commentDate;
    }

    /**
     * Edit the date and time of the comment made.
     *
     * @param commentDate the date and time of the comment made.
     */
    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * Returns the string representation of the comment.
     *
     * @return the string representation of the comment
     */
    @Override
    public String toString() {
        return commenter + ";" + comment + ";" + commentDate;
    }
}