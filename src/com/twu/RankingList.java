package com.twu;

import java.util.*;

public class RankingList {
    private List<ListItem> list;
    private List<ListItem> buyList;

    public RankingList() {
        list = new LinkedList<>();
        buyList = new LinkedList<>();
    }

    public void display() {
        for (int i = 0; i < list.size(); i++) {
            ListItem item = list.get(i);
            System.out.println((i + 1) + " " + item .getName() + " " + item.getVote());
        }
    }

    public void addItem(String name) {
        list.add(new ListItem(name));
    }

    public void addItem(String name, boolean superItem) {
        list.add(new ListItem(name, superItem));
    }


    public boolean voteItem(String name, int vote) {
        ListItem current = this.getThisItem(name);
        if (current == null) {
            return false;
        } else {
            current.vote(vote);
            this.sortList();
        }
        return true;
    }

    public void buyItem(String name, int rankNum, int money) {
        ListItem current = this.getThisItem(name);
        if (current == null) {
            System.out.println("该条热搜不存在");
            return;
        } else {
            // 搜索购买列表中是否存在该条热搜
            ListItem buyItem = this.searchFromBuyList(rankNum);
            // 不存在该条热搜
            if (buyItem == null) {
                buyItem = current;
                buyItem.setMoney(money);
                buyItem.setRank(rankNum);
                buyList.add(buyItem);
            } else {
                // 该排名存在热搜，并且购买金额大于原有金额
                if (money > buyItem.getMoney()) {
                    list.remove(buyItem);
                    buyList.remove(buyItem);
                    current.setRank(rankNum);
                    current.setMoney(money);
                    buyList.add(current);
                } else if (money <= buyItem.getMoney()) {
                    System.out.println("购买热搜的金额不足");
                }
            }
            this.sortBuyList();
            this.sortList();

        }
    }

    public void sortList() {
        Collections.sort(list, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o2.getVote() - o1.getVote();
            }
        });

        for (ListItem item : buyList) {
            int rank = item.getRank();
            list.remove(item);
            list.add(rank - 1, item);
        }

    }

    // 从小到大进行排序
    public void sortBuyList() {
        Collections.sort(buyList, new Comparator<ListItem>() {
            @Override
            public int compare(ListItem o1, ListItem o2) {
                return o1.getRank() - o2.getRank();
            }
        });
    }


    public int getSize() {
        return this.list.size();
    }

    public ListItem getThisItem(String name) {
        for (ListItem item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ListItem searchFromBuyList(int rank) {
        for (ListItem item : buyList) {
            if (item.getRank() == rank) {
                return item;
            }
        }
        return null;
    }

    public void displayInfo() {
        System.out.println("排行榜具体信息");
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + " " + list.get(i).getName() + " " + list.get(i).getVote() + "购买排名：" +list.get(i).getRank() + " 购买金额：" + list.get(i).getMoney());
        }
    }
}
