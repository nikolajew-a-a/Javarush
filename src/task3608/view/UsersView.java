package task3608.view;

import task3608.bean.User;
import task3608.controller.Controller;
import task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        if (!modelData.isDisplayDeletedUserList())
            System.out.println("All users:");
        else
            System.out.println("All deleted users:");

        for (User user: modelData.getUsers()){
            System.out.println("\t" + user.toString());
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }


}
