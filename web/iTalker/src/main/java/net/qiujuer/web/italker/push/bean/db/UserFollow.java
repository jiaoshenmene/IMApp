package net.qiujuer.web.italker.push.bean.db;

/*
 * 用户关系Model,对应数据库
 *
 * @Author: 杜甲
 * @Date: 2019-04-14 18:03
 */

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    // 定义发起人，你关注某人，这里就是你
    // 多对1-> 你可以关注很多人，你的每一次关注都是一条记录
    // 你可以创建很多个关注的信息，所有是多对1；
    // 这里的多对一是：User 对应 多个UserFollow
    // optional 不可选，必须存储，一条关注记录一定要有一个"你"
    @ManyToOne(optional = false)
    private User origin;


    // 定义关注的目标，你关注的人
    // 也是多对1，你可以被很多人关注，每一次关注都是一条记录
    // 所有就是 多个UserFollow 对应 一个 User 的情况
    @ManyToOne(optional = false)
    private User target;

    // 别名，也就是对target的备注名，可以为null
    @Column
    private String alias;

    // 定义为创建时间戳
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    // 定义为更新时间戳，在创建事就已经写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();





}
