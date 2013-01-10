package spike;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.Arrays;
import java.util.List;

public class HomePage extends WebPage {

    private static final List<String> TYPES = Arrays.asList(new String[] {
            "ON",
            "OFF"
    });

    private String selected = "ON";
    private String optional = "hidden?";

    public HomePage() {
        add(createForm());
    }

    private Form createForm() {
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                System.out.println("HomePage.onSubmit");
                System.out.println("selected = " + selected);
                System.out.println("optional = " + optional);
            }
        };

        RadioGroup<String> radioGroup = new RadioGroup<String>("group", new PropertyModel<String>(this, "selected"));
        form.add(radioGroup);

        radioGroup.add(new ListView<String>("choice", TYPES) {
            protected void populateItem(ListItem<String> it) {
                it.add(new Radio("radio", it.getModel()));
                it.add(new Label("label", it.getModelObject()));
            }
        });

        form.add(new TextField<String>("optional", new PropertyModel<String>(this, "optional")) {
            @Override
            public boolean isVisible() {
                return "ON".equals(HomePage.this.selected);
            }
        });

        return form;
    }
}
