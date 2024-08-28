package FootballApp.entities.observerPatterns;

public interface Observable {
	void addObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
}