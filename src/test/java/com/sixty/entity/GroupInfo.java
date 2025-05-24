package com.sixty.entity;

import com.sixty.annotation.JsonEntity;
import com.sixty.annotation.JsonId;

@JsonEntity(file = "groupInfo.json")
public class GroupInfo {
    @JsonId
    private Long id;
    private String groupId;
    private boolean isGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "id=" + id +
                ", groupId='" + groupId + '\'' +
                ", isGroup=" + isGroup +
                '}';
    }
}
