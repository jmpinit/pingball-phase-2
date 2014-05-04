package pingball.parse;

public class Fire {
    private String trigger;
    private String action;
    
    public Fire(String trigger, String action) {
        this.trigger = trigger;
        this.action = action;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getAction() {
        return action;
    }
}
