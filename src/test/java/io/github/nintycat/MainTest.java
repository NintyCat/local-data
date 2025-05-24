package io.github.nintycat;

import io.github.nintycat.entity.GroupInfo;
import io.github.nintycat.util.DataUtil;

public class MainTest {
    public static void main(String[] args) {
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setId(1L);
        groupInfo.setGroupId("111111");
        groupInfo.setGroup(true);
        System.out.println(DataUtil.save(GroupInfo.class).insert(groupInfo));
        for (GroupInfo info : DataUtil.find(GroupInfo.class).execute()) {
            System.out.println(info.getId());
            System.out.println(info.getGroupId());
            System.out.println(info.isGroup());
            DataUtil.del(GroupInfo.class).delete(info.getId());
        }

    }
}
