package haja.pta.common.communication.infrastructure;


public interface IClientInfrastructure {

    public void displayNotification(String title, String message);

    public void playMedia(String url);
}
