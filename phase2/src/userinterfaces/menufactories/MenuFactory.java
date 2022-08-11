package userinterfaces.menufactories;

import userinterfaces.menuenums.MenuEnums;

/**
 * An abstract menu factory that generates menu.
 */
public interface MenuFactory {
    /**
     * Generate and return a menu base on the MenuEnums type.
     *
     * @param type the type of menu to generate
     * @return a menu with the given type
     */
    Menu getMenu(MenuEnums type);
}
