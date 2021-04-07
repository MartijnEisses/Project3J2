package root.server;

import root.server.ObserverInterface;

public interface ObserverMethodsInterface {
    void registerObserver(ObserverInterface observer);
    void removeObserver(ObserverInterface observer);
    void notifyObservers();
    int getState();
}
