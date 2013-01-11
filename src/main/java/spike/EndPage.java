package spike;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 * @author Eirik Meland <eirik.meland@conduct.no>
 */
public class EndPage extends WebPage {
    public EndPage(State state) {
        super(new CompoundPropertyModel<Object>(state));

        getState().setLastPage(this.getClass());

        add(new Label("ajaxRadioSelect"));
        add(new Label("alternatives"));
        add(new Label("lastPage"));
    }

    private State getState() {
        return (State) getDefaultModelObject();
    }
}
