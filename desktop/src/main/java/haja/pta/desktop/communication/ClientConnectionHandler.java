package haja.pta.desktop.communication;

import haja.pta.common.communication.IGenericStream;
import haja.pta.common.communication.commands.client.IClientCommand;
import haja.pta.common.communication.commands.server.IServerCommand;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Harald Jagenteufel
 * 
 */
public class ClientConnectionHandler implements IClientConnectionHandler {

    private IGenericStream<IClientCommand, IServerCommand> _stream;
    @Autowired
    private IPhoneCommunicationManagement _phoneCommManagement;
    private ClientConnectionReaderRunnable _runnable;

    public ClientConnectionHandler(
            IGenericStream<IClientCommand, IServerCommand> stream) {
        _stream = stream;
        _runnable = new ClientConnectionReaderRunnable();
        new Thread(_runnable).start();
    }

    @Override
    public synchronized void write(IClientCommand request) throws IOException {
        _stream.write(request);
    }

    private class ClientConnectionReaderRunnable implements Runnable {

        private boolean _running = false;

        /**
         * read commands from clients and execute them
         */
        @Override
        public void run() {
            _running = true;
            try {
                while(_running) {
                    IServerCommand cmd = ClientConnectionHandler.this._stream
                            .read();
                    cmd.execute(
                            ClientConnectionHandler.this._phoneCommManagement,
                            ClientConnectionHandler.this);
                }

                _stream.close();
            } catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void stop() {
            _running = false;
        }
    }

    @Override
    public void login(String user) {
        _phoneCommManagement.addClient(user, this);
    }

    @Override
    public void close() {
        _runnable.stop();
        try {
            _stream.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
