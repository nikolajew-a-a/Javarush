package task3608.model;

import task3608.bean.User;

import java.util.List;

public class ModelData {
    private List<User> users;
    private List<User> deletedUsers;
    private User activeUser;
    private boolean displayDeletedUserList;

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setDeletedUsers(List<User> deletedUsers) {
        this.deletedUsers = deletedUsers;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }


}
