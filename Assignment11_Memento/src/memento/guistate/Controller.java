package memento.guistate;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private Gui gui;
    private List<IMemento> history;
    private List<IMemento> redoList;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.history = new ArrayList<>();
        this.redoList = new ArrayList<>();
        saveToHistory();
    }

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        history.add(currentState);
        redoList.clear();
        System.out.println("Saved to history, history size: " + history.size());
    }

    public void setOption(int optionNumber, int choice) {
        saveToHistory();         // 先保存修改前的状态
        model.setOption(optionNumber, choice);
        gui.updateGui();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
        gui.updateGui();
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    // 撤销
    public void undo() {
        if (history.size() > 1) {
            IMemento current = model.createMemento();
            redoList.add(current);

            history.remove(history.size() - 1);
            IMemento previous = history.get(history.size() - 1);
            model.restoreState(previous);
            gui.updateGui();
            System.out.println("Undo performed, history size: " + history.size() + ", redo size: " + redoList.size());
        } else {
            System.out.println("Cannot undo: no more history");
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            // 将当前状态存入历史（以便之后可以再次撤销到这个状态）
            IMemento current = model.createMemento();
            history.add(current);

            // 从重做列表取出最后一个状态
            IMemento next = redoList.remove(redoList.size() - 1);
            model.restoreState(next);
            gui.updateGui();
            System.out.println("Redo performed, history size: " + history.size() + ", redo size: " + redoList.size());
        } else {
            System.out.println("Cannot redo: no redo history");
        }
    }

    public List<IMemento> getHistory() {
        return new ArrayList<>(history);
    }

    public void restoreFromHistory(IMemento memento) {
        model.restoreState(memento);
        int index = history.indexOf(memento);
        if (index != -1) {
            history = new ArrayList<>(history.subList(0, index + 1));
        }
        redoList.clear();
        gui.updateGui();
        System.out.println("Restored from history, new history size: " + history.size());
    }
}