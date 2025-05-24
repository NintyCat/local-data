package com.sixty;

import com.sixty.entity.GroupInfo;
import com.sixty.util.DataUtil;

public class MainTest {
    public static void main(String[] args) {

        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroup(true);
        groupInfo.setGroupId("111111");
        System.out.println(DataUtil.save(GroupInfo.class).insert(groupInfo));
        for (GroupInfo info : DataUtil.find(GroupInfo.class).execute()) {
            DataUtil.del(GroupInfo.class).delete(info.getId()+11111);
        }

    }
}
