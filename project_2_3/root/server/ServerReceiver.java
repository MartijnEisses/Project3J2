package root.server;

public class ServerReceiver {
    private String servercommand;

    public ServerReceiver(String servercommand){
        this.servercommand = servercommand;


    }

    public String getServercommand() {
        return servercommand;
    }
}
