package controllers.menuAction.menuActionFactories;

import controllers.menuAction.menuActions.MenuAction;
public interface MenuActionFactory {
    MenuAction getMenuAction(String type);
}
