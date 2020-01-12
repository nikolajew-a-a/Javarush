package task3608.model;

import task3608.bean.User;

import java.util.ArrayList;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User("A", 1, 11));
        userList.add(new User("B", 2, 12));
        modelData.setUsers(userList);

    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void deleteUserById(long id){
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }

}
