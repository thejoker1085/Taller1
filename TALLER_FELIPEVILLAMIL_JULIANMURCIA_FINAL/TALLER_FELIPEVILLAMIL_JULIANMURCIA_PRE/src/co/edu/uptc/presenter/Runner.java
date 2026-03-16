package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.model.ManagerFileImp;
import co.edu.uptc.view.ConsoleView;

public class Runner {
    private PresenterInterface presenter;
    private ModelInterface model;
    private ViewInterface view;

    public void start() {
        makeMVP();
        view.start();
    }

    private void makeMVP() {
        presenter = new MainPresenter();
        model = new ManagerFileImp();
        view = new ConsoleView();
        presenter.setModel(model);
        presenter.setView(view);
        view.setPresenter(presenter);
    }
}
