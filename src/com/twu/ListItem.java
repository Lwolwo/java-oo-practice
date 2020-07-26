package com.twu;

public class ListItem {
    private String name; // 热搜名
    private int rank; // 0为普通热搜，非0数为实际购买排名
    private int vote; // 投票票数
    private boolean superItem; // 超级热搜
    private int money; // 购买金额

    public ListItem(String name) {
        this.name = name;
        this.rank = 0;
        this.vote = 0;
        this.superItem = false;
        this.money = 0;
    }
    public ListItem(String name, boolean superItem) {
        this.name = name;
        this.rank = 0;
        this.vote = 0;
        this.superItem = superItem;
        this.money = 0;
    }

    public void vote(int num) {
        if (this.superItem) {
            this.vote += num * 2;
        } else {
            this.vote += num;
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }
    public int getRank() {
        return this.rank;
    }
    public int getVote() {
        return this.vote;
    }
    public int getMoney() {
        return this.money;
    }
}
