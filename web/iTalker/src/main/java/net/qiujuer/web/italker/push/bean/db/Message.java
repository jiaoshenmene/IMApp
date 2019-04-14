package net.qiujuer.web.italker.push.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * @Author: 杜甲
 * @Date: 2019-04-14 20:50
 */
@Entity
@Table(name = "TB_MESSAGE")
public class Message {

    public static final int TYPE_STR = 1; //字符串类型

    public static final int TYPE_PIC = 2; //图片类型

    public static final int TYPE_FILE = 3; //文件类型

    public static final int TYPE_AUDIO = 4; //语音类型


    // 这是一个主键
    @Id
    @Column(updatable = false,nullable = false)
    // 这里不自动生成UUID，Id由代码写入，由客户端负责生成
    // 避免复杂的服务器和客户端的映射关系
    //把uuid的生成器定义为uuid2,uuid2是常规的UUID toString
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    //内容不允许为空，类型为text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    //附件
    @Column()
    private String attach;

    // 消息类型
    @Column(nullable = false)
    private int type;

    // 发送者
    // 多个消息对应一个发送者
    @JoinColumn(name = "senderId")
    @ManyToOne(optional = false)
    private User sender;
    // 这个字段仅仅只是为了对应sender的数据库字段senderId
    // 不允许手动的更新或者插入
    @Column(updatable = false, insertable = false)
    private String senderId;


    // 接收者，可为空
    // 多个消息对应一个接收者
    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(updatable = false, insertable = false)
    private String receiverId;


    // 定义为创建时间戳
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建事就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
