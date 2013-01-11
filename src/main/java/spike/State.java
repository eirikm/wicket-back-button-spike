package spike;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eirik Meland <eirik.meland@conduct.no>
 */
public class State implements Serializable {

    private String ajaxRadioSelect;
    private List<String> alternatives = new ArrayList<String>();
    private Class lastPage;

    public State(String ajaxRadioSelect, List<String> alternatives) {
        this.ajaxRadioSelect = ajaxRadioSelect;
        this.alternatives.addAll(alternatives);
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

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public String toString() {
        return "State{" +
                "ajaxRadioSelect='" + ajaxRadioSelect + '\'' +
                ", alternatives=" + alternatives +
                ", lastPage=" + lastPage +
                '}';
    }
}
