package dam.JosantVarona.View;

public enum Scenes {
    ROOT("View/layout.fxml"),
    START("View/strar.fxml"),
    LOGIN("View/login.fxml"),
    MAIN("View/main.fxml"),
    HUELLASH("View/huellas.fxml"),
    INSERHUELLA("View/dejarHuella.fxml"),;

    private String url;
    Scenes (String url){
        this.url = url;
    }
    public String getURL(){
        return url;
    }
}
