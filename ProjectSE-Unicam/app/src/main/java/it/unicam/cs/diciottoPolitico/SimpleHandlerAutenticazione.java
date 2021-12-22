package it.unicam.cs.diciottoPolitico;

public class SimpleHandlerAutenticazione implements HandlerAutenticazione{

    private final DatabaseManager databaseManager;

    public SimpleHandlerAutenticazione(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean autenticarsi(String email, String password) {
        return this.databaseManager.verificaPresenza(email, password);
    }
}
