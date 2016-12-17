package sample.bot.men;

public interface State {

    void execute(Men men);
    void wait(Men men);

}
