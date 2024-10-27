package project.project.Model;

public class Session extends Request{
    private static boolean isMember = false;
    private static boolean isAdmin = false;
    private static int uIdSession;
    private static String uName;
    private static String uTeam = "";

    public Session(String function) {
        super(function);
    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        Session.isAdmin = isAdmin;
    }

    public static boolean getIsMember() {
        return isMember;
    }

    public static void setIsMember(boolean isMember) {
        Session.isMember = isMember;
    }

    public static int getuIdSession() {
        return uIdSession;
    }

    public static void setuIdSession(int uIdSession) {
        Session.uIdSession = uIdSession;
    }

    public static String getuName() {
        return uName;
    }

    public static void setuName(String uName) {
        Session.uName = uName;
    }

    public static String getuTeam() {
        return uTeam;
    }

    public static void setuTeam(String uTeam) {
        Session.uTeam = uTeam;
    }
}
