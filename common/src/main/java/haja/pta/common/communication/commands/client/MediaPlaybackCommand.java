package haja.pta.common.communication.commands.client;

import haja.pta.common.communication.infrastructure.IClientInfrastructure;


public class MediaPlaybackCommand implements IClientCommand {

    private static final long serialVersionUID = 7812855060633364428L;

    String _url;
    
    public MediaPlaybackCommand(String url) {
        _url = url;
    }
    
    @Override
    public void execute(IClientInfrastructure client) {
        client.playMedia(_url);
    }

}
