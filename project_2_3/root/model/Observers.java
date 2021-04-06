package root.model;

import java.util.ArrayList;

public class Observers implements ObserverMethodsInterface{

    private ArrayList<ObserverInterface> observers;

    public Observers() {
        this.observers = new ArrayList<ObserverInterface>();
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(ObserverInterface observer : observers) {

            //geef parameter mee om te updaten
            observer.update();
        }
    }

    public void updateReversi() {
        //doe iets om reversi te synchroniseren etc.
        notifyObservers();
    }

    @Override
    public int getState() {
        return 0;
    }


}
