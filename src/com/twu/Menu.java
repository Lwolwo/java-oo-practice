package com.twu;

import java.util.*;

public class Menu {
    private List<User> users;
    private RankingList rankingList;
    private User activeUser;

    Menu() {
        users = new ArrayList<>();
        rankingList = new RankingList();
        activeUser = null;
    }

    public void init() {
        System.out.println("欢迎来到热搜排行榜，您的身份是？");
        System.out.println("1. 用户");
        System.out.println("2. 管理员");
        System.out.println("3. 退出");
    }

    public boolean createUser(int code, String name, String pwd) {
        boolean isExist = false;
        for (User user : users) {
            if (user.getName().equals(name)) {
                isExist = true;
                activeUser = user;
                break;
            }
        }


        if (code == 1 && !isExist) {
            User newUser = new User(name);
            users.add(newUser);
            activeUser = newUser;
        } else if (code == 2) {
            if (!isExist) {
                User newUser = new User(name, pwd);
                users.add(newUser);
                activeUser = newUser;
            } else {
                // 密码错误
                if (!activeUser.verify(pwd)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        // 普通用户
        System.out.println("你好，" + this.activeUser.getName() + "，你可以");
        System.out.println("1. 查看热搜排行榜");
        if (this.activeUser.getType() == 0) {
            System.out.println("2. 给热搜事件投票");
            System.out.println("3. 购买热搜");
            System.out.println("4. 添加热搜");
            System.out.println("5. 退出");
        } else if (this.activeUser.getType() == 1) {
            System.out.println("2. 添加热搜");
            System.out.println("3. 添加超级热搜");
            System.out.println("4. 退出");
        }
    }

    // 查看热搜
    public void displayRankingList() {
        this.rankingList.display();
    }

    // 给热搜投票
    public void voteItem(String name, String vote) {
        if (!vote.matches("[0-9]+")) {
            System.out.println("投票数非法，投票失败");
            return;
        }
        int voteNumber = Integer.parseInt(vote);
        if (voteNumber > this.activeUser.getVote() || voteNumber <= 0) {
            System.out.println("投票数非法，投票失败");
            return;
        }
        if (!rankingList.voteItem(name, voteNumber)) {
            System.out.println("该条热搜不存在");
        } else {
            this.activeUser.vote(voteNumber);
        }
    }

    // 添加热搜
    public void addListItem(String name, String type) {
        boolean superItem = false;
        if (type.equals("3")) {
            superItem = true;
        }
        this.rankingList.addItem(name, superItem);
    }

    // 购买热搜
    public void buyRanking(String name, String rankNum, String money) {
        if (!rankNum.matches("[0-9]+") || !money.matches("[0-9]+")) {
            System.out.println("输入数字非法，购买失败");
            return;
        }
        int rankNumber = Integer.parseInt(rankNum);
        int buyMoney = Integer.parseInt(money);

        if (rankNumber <= 0 || rankNumber > this.rankingList.getSize()) {
            System.out.println("购买排名数非法，购买失败");
            return;
        } else if (buyMoney <= 0) {
            System.out.println("购买金额非法，购买失败");
        }

        rankingList.buyItem(name, rankNumber, buyMoney);

    }

    public int getActiveUserVote() {
        return activeUser.getVote();
    }

    public int getUserType() {
        return this.activeUser.getType();
    }
}
