package task3608;

import task3608.controller.Controller;
import task3608.model.MainModel;
import task3608.model.Model;
import task3608.view.EditUserView;
import task3608.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        Controller controller = new Controller();
        EditUserView editUserView = new EditUserView();
        editUserView.setController(controller);
        controller.setEditUserView(new EditUserView());

        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("Вано", 126, 1);
        usersView.fireEventShowDeletedUsers();



    }
}