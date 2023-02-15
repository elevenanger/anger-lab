package model;

/**
 * author : anger
 * date : 2022/7/16 20:18
 * description : 评论 POJO
 * POJO 是一个简单的没有依赖的对象
 * 仅仅被它的属性和方法所描述
 */
public class Comment {
    /*
    评论的作者
     */
    private String author;
    /*
    评论的内容
     */
    private String text;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "author='" + author + '\'' +
            ", text='" + text + '\'' +
            '}';
    }
}
