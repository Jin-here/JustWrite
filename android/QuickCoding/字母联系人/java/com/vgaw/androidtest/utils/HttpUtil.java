package com.vgaw.androidtest.utils;

import com.vgaw.androidtest.bean.ContactBase;
import com.vgaw.androidtest.bean.ContactIndex;
import com.vgaw.androidtest.bean.Friend;
import com.vgaw.androidtest.bean.Group;
import com.vgaw.androidtest.bean.Label;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by caojin on 2016/5/4.
 */
public class HttpUtil {
    private static HashMap<String, Integer> abcMap;

    public static HashMap<String, Integer> getAbcMap(){
        return abcMap;
    }

    public static ArrayList<ContactBase> getContact() {
        abcMap = new HashMap<>();

        ArrayList<ContactBase> dataList = new ArrayList<>();

        ArrayList<Label> labelList = new ArrayList<>();
        ArrayList<Group> groupList = new ArrayList<>();
        ArrayList<ContactIndex> indexList = new ArrayList<>();

        Group group1 = new Group();
        group1.setName("南京晓庄学院");
        Group group2 = new Group();
        group2.setName("橙子金融");
        groupList.add(group1);
        groupList.add(group2);


        Friend f1 = new Friend();
        f1.setName("曹进");
        Friend f2 = new Friend();
        f2.setName("陈凯");
        Friend f3 = new Friend();
        f3.setName("邓鹏");
        Friend f4 = new Friend();
        f4.setName("古寺寒");
        Friend f5 = new Friend();
        f5.setName("黄家良");
        Friend f6 = new Friend();
        f6.setName("周惠君");
        Friend f7 = new Friend();
        f7.setName("曹进");
        Friend f8 = new Friend();
        f8.setName("陈凯");
        Friend f9 = new Friend();
        f9.setName("邓鹏");
        Friend f10 = new Friend();
        f10.setName("古寺寒");
        Friend f11 = new Friend();
        f11.setName("黄家良");
        Friend f12 = new Friend();
        f12.setName("周惠君");
        indexList.add(f1);
        indexList.add(f2);
        indexList.add(f3);
        indexList.add(f4);
        indexList.add(f5);
        indexList.add(f6);
        indexList.add(f7);
        indexList.add(f8);
        indexList.add(f9);
        indexList.add(f10);
        indexList.add(f11);
        indexList.add(f12);

        for (ContactIndex contactIndex : indexList){
            // 插入字母
            Friend friend = (Friend) contactIndex;
            String index = HanziToPinyin.getInstance().get(friend.getName().substring(0, 1)).get(0).target.substring(0, 1).toUpperCase();
            friend.setIndex(index);

            // 插入abcMap
            if (abcMap.get(index) == null){
                Label label = new Label();
                label.setIndex(index);
                labelList.add(label);
                abcMap.put(index, -1);
            }
        }
        indexList.addAll(labelList);
        // 排序
        Collections.sort(indexList, new AlphabetComparator());

        // 统计abcMap
        for (int i = 0;i < indexList.size();i++){
            if (indexList.get(i) instanceof Label){
                abcMap.put(indexList.get(i).getIndex(), groupList.size() + i);
            }
        }

        dataList.addAll(groupList);
        dataList.addAll(indexList);
        return dataList;
    }

    public static class AlphabetComparator implements Comparator<ContactIndex> {

        @Override
        public int compare(ContactIndex lhs, ContactIndex rhs) {
            int first = lhs.getIndex().compareTo(rhs.getIndex());
            if (first == 0){
                return lhs.getType() - rhs.getType();
            }
            return first;
        }
    }
}
