package fr.edmine.SkyWars.Games;

public class SoloGame {

    private GameState state;

    public void startGame() {
        state = GameState.IN_PROGRESS;

        System.out.println("Partie Solo démarée !");
    }

    public GameState getState() {
        return state;
    }

    public void endGame() {
        state = GameState.FINISHED;

        System.out.println("Partie Solo terminée !");
    }
}
