package root.model;

public interface ObserverMethodsInterface {
    void registerObserver(ObserverInterface observer);
    void removeObserver(ObserverInterface observer);
    void notifyObservers();
    int getState();
}
