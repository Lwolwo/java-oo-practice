package com.twu;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.init();
            String command = scanner.nextLine().trim();

            int code = Integer.parseInt(command);
            if (code == 1 || code == 2) {
                System.out.println("请输入您的昵称：");
                String name = scanner.nextLine();
                String pwd = "";
                if (code == 2) {
                    System.out.println("请输入您的密码：");
                    pwd = scanner.nextLine();
                }
                // 创建用户
                boolean result = menu.createUser(code, name, pwd);
                if (!result) {
                    System.out.println("密码错误");
                }
            } else if (code == 3) {
                break;
            } else {
                System.out.println("指令不合法，请重新输入");
            }

            while (true) {
                menu.display();
                command = scanner.nextLine().trim();

                if (menu.getUserType() == 0) {
                    if (command.equals("1")) {
                        menu.displayRankingList();
                    } else if (command.equals("2")) {
                        System.out.println("请输入你要投票的热搜名称：");
                        String name = scanner.nextLine();
                        System.out.println("请输入你要投票的热搜票数：（你目前还有：" + menu.getActiveUserVote() + "票）");
                        String vote = scanner.nextLine();
                        menu.voteItem(name, vote);

                    } else if (command.equals("3")) {
                        System.out.println("请输入你要购买的热搜名称：");
                        String name = scanner.nextLine();
                        System.out.println("请输入你要购买的热搜排名：");
                        String rankNum = scanner.nextLine();
                        System.out.println("请输入你要购买的热搜金额");
                        String money = scanner.nextLine();
                        menu.buyRanking(name, rankNum, money);

                    } else if (command.equals("4")) {
                        System.out.println("请输入你要添加的热搜名字：");
                        String name = scanner.nextLine();
                        // type = 2，添加普通热搜
                        menu.addListItem(name, "2");
                    } else if (command.equals("5")) {
                        break;
                    } else {
                        System.out.println("指令不合法，请重新输入");
                    }
                } else {
                    if (command.equals("1")) {
                        menu.displayRankingList();
                    } else if (command.equals("2") || command.equals("3")) {
                        System.out.println("请输入你要添加的热搜名字：");
                        String name = scanner.nextLine();
                        menu.addListItem(name, command);
                    } else if (command.equals("4")) {
                        break;
                    } else {
                        System.out.println("指令不合法，请重新输入");
                    }
                }

            }

        }
        scanner.close();
    }
}
