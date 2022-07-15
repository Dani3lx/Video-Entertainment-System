public class NonAdminHandler extends UserActionHandler{

    NonAdminManager nm;
    public NonAdminHandler(UserManager um, VideoManager vm) {
        super(um);
        nm = new NonAdminManager(um, vm);
    }

}
