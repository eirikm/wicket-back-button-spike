package spike;

import java.io.Serializable;

/**
 * @author Eirik Meland <eirik.meland@conduct.no>
 */
public class State implements Serializable {

    private String ajaxRadioSelect;
    private Class lastPage;

    public State(String ajaxRadioSelect) {
        this.ajaxRadioSelect = ajaxRadioSelect;
    }

    public String getAjaxRadioSelect() {
        return ajaxRadioSelect;
    }

    public void setAjaxRadioSelect(String ajaxRadioSelect) {
        this.ajaxRadioSelect = ajaxRadioSelect;
    }

    public Class getLastPage() {
        return lastPage;
    }

    public void setLastPage(Class lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        return "State{" +
                "ajaxRadioSelect='" + ajaxRadioSelect + '\'' +
                ", lastPage=" + lastPage +
                '}';
    }
}
