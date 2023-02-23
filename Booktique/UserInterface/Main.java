import serviceHost.PopulateDAta;

public class Main {
    public static void main(String [] args)
    {
        PopulateDAta data = new PopulateDAta();
        data.InitData();

        LoginPage.login();
    }
}