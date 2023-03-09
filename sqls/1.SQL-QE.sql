# 创建数据库和数据表
drop database if exists ff_community;
create database ff_community character set utf8mb4;
USE ff_community;

-- ----------------------------
-- 数据库设计
-- ----------------------------
-- 1、用户信息表
-- ----------------------------
drop table if exists qe_user;
create table qe_user
(
    user_id      bigint(255)  not null auto_increment comment '用户ID',
    user_name    varchar(255) not null unique comment '用户名',
    email        varchar(255) not null unique comment '邮箱',
    phone_number varchar(255) unique comment '手机号',
    sex          varchar(255) default '未知' comment '性别',
    avatar       varchar(255) default '' comment '头像地址',
    password     varchar(255) not null comment '密码',
    status       bit(1)       default 1 comment '状态（1为正常，0为停用）',
    login_ip     varchar(255) default '127.0.0.1' comment '最后登陆的IP',
    login_date   datetime comment '最后登陆的时间',
    signature    varchar(255) default '无' comment '个性签名',
    grade        bigint(255)  default 1 comment '用户等级',
    experience   bigint(255)  default 0 comment '用户经验值',
    email_status bit(1)       default 0 comment '邮箱校验的状态(1-通过，0-未通过)',
    school_id    bigint(255)  default 0 comment '用户所在学校ID',
    delete_flag  bit(1)       default 0 comment '删除标志(0为未删除，1为删除)',
    create_time  datetime     default now() comment '创建时间',
    update_time  datetime     default now() comment '更新时间',
    primary key (user_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '用户表';

-- ----------------------------
-- 2、用户关注表
-- ----------------------------
drop table if exists qe_user_follow;
create table qe_user_follow
(
    follow_id             bigint(255) not null auto_increment comment '主键ID',
    give_follow_qeuser_id bigint(255) not null default 1 comment '发起关注的用户的ID',
    get_follow_qeuser_id  bigint(255) not null default 1 comment '被关注的用户的ID',
    create_time           datetime             default now() comment '创建时间',
    update_time           datetime             default now() comment '更新时间',
    primary key (follow_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '用户关注表';
# 联合唯一键，使得确保指定的多个列的组合是唯一的，即这两列的取值不能同时相同。保证了在该表中，不会有两行记录的这两个列的组合值完全相同。
alter table qe_user_follow
    add unique key (give_follow_qeuser_id, get_follow_qeuser_id);

-- ----------------------------
-- 3、用户-角色表(多对多)
-- ----------------------------
drop table if exists qe_user_role;
create table qe_user_role
(
    user_role_id    bigint(255)  not null auto_increment comment '主键',
    role_name       varchar(255) not null default '普通用户' comment '角色名称',
    user_id         bigint(255)  not null default 1 comment '所拥有该角色的用户ID',
    role_permission varchar(255) not null default 'user.*' comment '角色拥有的权限码',
    create_time     datetime              default now() comment '创建时间',
    update_time     datetime              default now() comment '更新时间',
    primary key (user_role_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '用户-角色表(多对多)';

-- ----------------------------
-- 5、版块表
-- ----------------------------
drop table if exists qe_section;
create table qe_section
(
    section_id            bigint(255)  not null auto_increment comment '版块主键',
    section_name          varchar(255) not null comment '版块名称',
    section_introduce     varchar(255) not null comment '版块介绍',
    section_logo          varchar(255) not null comment '版块logo',
    section_background    varchar(255) not null comment '版块背景图',
    section_admin_user_id bigint(255)  not null default 1 comment '版主用户ID',
    section_weight        bigint(255)  not null default 0 comment '版块权重',
    section_top           bit(1)       not null default 0 comment '板块是否置顶(1为置顶，0为不置顶)',
    delete_flag           bit(1)                default 0 comment '删除标志(不为0则删除,否则等于id)',
    create_time           datetime              default now() comment '创建时间',
    update_time           datetime              default now() comment '更新时间',
    primary key (section_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '版块表';

-- ----------------------------
-- 6、版块分类表
-- ----------------------------
drop table if exists qe_section_type;
create table qe_section_type
(
    section_type_id      bigint(255)  not null auto_increment comment '版块分类主键',
    section_type_name    varchar(255) not null comment '版块分类名称',
    section_id           bigint(255)  not null comment '分类所属的版块ID',
    section_type_weight  bigint(255)  not null default 0 comment '分类权重',
    section_type_mold    bit(1)       not null default 0 comment '分类类型（0-普通分类，1-特殊分类）',
    section_type_network varchar(255) comment '分类网址',
    delete_flag          bit(1)                default 0 comment '删除标志(不为0则删除,否则等于id)',
    create_time          datetime              default now() comment '创建时间',
    update_time          datetime              default now() comment '更新时间',
    primary key (section_type_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '版块分类表';

-- ----------------------------
-- 2、版块关注表
-- ----------------------------
drop table if exists qe_section_follow;
create table qe_section_follow
(
    follow_id               bigint(255) not null auto_increment comment '主键ID',
    give_follow_qeuser_id   bigint(255) not null comment '发起关注的用户的ID',
    get_follow_qesection_id bigint(255) not null comment '被关注的版块的ID',
    create_time             datetime default now() comment '创建时间',
    update_time             datetime default now() comment '更新时间',
    primary key (follow_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '用户关注版块的表';
alter table qe_section_follow
    add unique key (give_follow_qeuser_id, get_follow_qesection_id);
-- ----------------------------
-- 2、学校表
-- ----------------------------
drop table if exists qe_school;
create table qe_school
(
    school_id         bigint(255)  not null auto_increment comment '学校主键ID',
    school_name       varchar(255) not null unique comment '学校名称',
    school_introduce  longtext     not null comment '学校介绍',
    school_badge      varchar(255) not null comment '学校校徽',
    school_motto      varchar(255) not null comment '学校校训',
    school_background varchar(255) not null comment '学校背景图',
    school_build_date datetime comment '建校时间',
    school_location   varchar(255) comment '学校位置',
    delete_flag       bit(1)   default 0 comment '删除标志(不为0则删除,否则等于id)',
    create_time       datetime default now() comment '创建时间',
    update_time       datetime default now() comment '更新时间',
    primary key (school_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '学校表';

-- ----------------------------
-- 2、文章表
-- ----------------------------
drop table if exists qe_article;
create table qe_article
(
    article_id      bigint(255)  not null auto_increment comment '文章ID',
    article_title   varchar(255) not null comment '文章标题',
    article_content longtext     not null comment '文章内容',
    article_image   varchar(255) comment '文章首页图片',
    section_id      bigint(255)  not null comment '文章所属版块ID',
    section_type_id bigint(255)  not null comment '文章所在版块中的分类的ID',
    author_id       bigint(255)  not null comment '作者ID',
    status          bit(1)      default 1 comment '状态（1为正常，0为封禁）',
    article_weight  bigint(255) default 0 comment '文章权重',
    article_top     bit(1)      default 0 comment '文章是否置顶(1为置顶)',
    delete_flag     bit(1)      default 0 comment '删除标志(不为0则删除,否则等于id)',
    create_time     datetime    default now() comment '创建时间',
    update_time     datetime    default now() comment '更新时间',
    primary key (article_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '文章表';

-- ----------------------------
-- 3、用户点赞文章表
-- ----------------------------
drop table if exists qe_article_like;
create table qe_article_like
(
    like_id               bigint(255) not null auto_increment comment '主键ID',
    give_like_qeuser_id   bigint(255) not null comment '发起点赞的用户的ID',
    get_like_qearticle_id bigint(255) not null comment '被点赞的文章的ID',
    create_time           datetime default now() comment '创建时间',
    update_time           datetime default now() comment '更新时间',
    primary key (like_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '用户点赞文章的表';

-- ----------------------------
-- 4、评论表
-- ----------------------------
drop table if exists qe_comment;
create table qe_comment
(
    comment_id      bigint(255)  not null auto_increment comment '评论ID',
    article_id      bigint(255)  not null comment '评论所在的文章ID',
    content         varchar(255) not null comment '评论内容',
    comment_user_id bigint(20)            default 1 comment '评论者ID',
    status          bit(1)                default 1 comment '状态（1为正常，0为封禁）',
    to_user_id      bigint(255)  not null default 1 comment '被回复的人的ID',
    reply_id        bigint(255)           default 0 comment '父评论ID,回复的评论的ID(0则为是评论而不是回复)',
    root_id         bigint(255)           default 0 comment '根评论ID(为0则为根评论)',
    delete_flag     bit(1)                default 0 comment '删除标志(不为0则删除,否则等于id)',
    create_time     datetime              default now() comment '创建时间',
    primary key (comment_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '评论表';

-- ----------------------------
-- 4、收藏表
-- ----------------------------
drop table if exists qe_collection;
create table qe_collection
(
    collection_id bigint(255) not null auto_increment comment '收藏ID',
    user_id       bigint(255) not null comment '发起收藏的用户的ID',
    article_id    bigint(255) not null comment '被收藏的文章的ID',
    create_time   datetime default now() comment '创建时间',
    update_time   datetime default now() comment '更新时间',
    primary key (collection_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '收藏表';
alter table qe_collection
    add unique key (user_id, article_id);
-- ----------------------------
-- 5、操作记录表
-- ----------------------------
drop table if exists qe_operlog;
create table qe_operlog
(
    oper_id        bigint(255) not null auto_increment comment '操作的id',
    business_type  bigint(10)  not null default 1 comment '业务类型(0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据)',
    method         varchar(255)         default 'add' comment '方法名称',
    request_method varchar(255)         default 'GET' comment '请求方式',
    oper_username  varchar(255)         default '操作用户名' comment '操作用户名',
    oper_url       varchar(255)         default 'localhost' comment '请求的URL',
    oper_ip        varchar(255)         default '127.0.0.1' comment '操作所在的ip',
    oper_location  varchar(255)         default '地球' comment '操作所在的地方',
    oper_param     varchar(255)         default null comment '请求参数',
    json_result    varchar(255)         default null comment '返回的参数',
    status         bit(1)               default 0 comment '操作的状态(0正常 1异常)',
    error_msg      varchar(255)         default null comment '错误的消息',
    oper_time      datetime             default now() comment '操作的时间',

    primary key (oper_id)
) engine = innodb
  auto_increment = 1
  CHARACTER SET = utf8mb4 comment = '操作记录表';

