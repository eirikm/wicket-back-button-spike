package spike;

import java.io.Serializable;

/**
 * @author Eirik Meland <eirik.meland@conduct.no>
 */
public class State implements Serializable {

    private String radioSelect;
    private String ajaxRadioSelect;
    private String optional;
    private Class lastPage;

    public State(String ajaxRadioSelect, String optional) {
        this.ajaxRadioSelect = ajaxRadioSelect;
        this.optional = optional;
    }

    public String getAjaxRadioSelect() {
        return ajaxRadioSelect;
    }

    public void setAjaxRadioSelect(String ajaxRadioSelect) {
        this.ajaxRadioSelect = ajaxRadioSelect;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public Class getLastPage() {
        return lastPage;
    }

    public void setLastPage(Class lastPage) {
        this.lastPage = lastPage;
    }

    public String getRadioSelect() {
        return radioSelect;
    }

    public void setRadioSelect(String radioSelect) {
        this.radioSelect = radioSelect;
    }

    @Override
    public String toString() {
        return "State{" +
                "radioSelect='" + radioSelect + '\'' +
                ", ajaxRadioSelect='" + ajaxRadioSelect + '\'' +
                ", optional='" + optional + '\'' +
                ", lastPage=" + lastPage +
                '}';
    }
}
