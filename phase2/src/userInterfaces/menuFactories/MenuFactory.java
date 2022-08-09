package userInterfaces.menuFactories;

import userInterfaces.menuEnums.MenuEnums;

public interface MenuFactory {
    Menu getMenu(MenuEnums type);
}
