package com.twu;

public class User {
    private String name;
    private int type; // 0为用户，1为管理员
    private String password; // 管理员需要密码，第一次登陆时设置
    private int vote; // 可用投票票数

    public User(String name) {
        this.name = name;
        this.type = 0;
        this.vote = 10;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.type = 1;
        this.vote = 10;
    }

    public boolean verify(String password) {
        return this.password.equals(password);
    }

    public void vote(int number) {
        this.vote -= number;
    }

    public int getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getVote() {
        return this.vote;
    }
}
