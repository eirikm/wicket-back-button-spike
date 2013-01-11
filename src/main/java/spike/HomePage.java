package spike;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.Arrays;
import java.util.List;

public class HomePage extends WebPage {

    private static final List<String> RADIO_TYPES = Arrays.asList(new String[] {
            "WAT!",
            "Capiche"
    });

    private static final List<String> AJAX_RADIO_TYPES = Arrays.asList(new String[] {
            "ON",
            "OFF"
    });
    private TextField<String> optionalTextField;

    public HomePage() {
        super(Model.of(new State("OFF", "hidden?")));
        getState().setLastPage(this.getClass());
        add(createForm());
    }

    @Override
    protected void onRender() {
        super.onRender();
        System.out.println("HomePage.onRender");
        System.out.println("getState() = " + getState());
    }

    private Form createForm() {
        final Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                System.out.println("HomePage.onSubmit");
                System.out.println("getState() = " + getState());
                modelChanging();
                EndPage nextPage = new EndPage(getState());
                modelChanged();
                setResponsePage(nextPage);
            }
        };

        form.add(createRadioGroup());
        form.add(createAjaxRadioGroup());
        optionalTextField = createOptionalTextField();
        form.add(optionalTextField);

        return form;
    }

    private TextField<String> createOptionalTextField() {
        TextField<String> optional = new TextField<String>("optional", new PropertyModel<String>(getState(), "optional")) {
            @Override
            public boolean isVisible() {
                return "ON".equals(HomePage.this.getSelected());
            }
        };
        optional.setOutputMarkupId(true);
        optional.setOutputMarkupPlaceholderTag(true);
        return optional;
    }

    private RadioGroup<String> createRadioGroup() {
        RadioGroup<String> radioGroup = new RadioGroup<String>("radioGroup", new PropertyModel<String>(getState(), "radioSelect"));
        ListView<String> listView = new ListView<String>("radioChoice", RADIO_TYPES) {
            protected void populateItem(final ListItem<String> it) {
                it.add(new Radio("radio", it.getModel()));
                it.add(new Label("radioLabel", it.getModelObject()));
            }
        };
        radioGroup.add(listView);
        return radioGroup;
    }

    private WebMarkupContainer createAjaxRadioGroup() {
        final WebMarkupContainer ajaxRadioDiv = new WebMarkupContainer("ajaxRadioDiv");
        final RadioGroup<String> radioGroup = new RadioGroup<String>("ajaxRadioGroup", new PropertyModel<String>(getState(), "ajaxRadioSelect"));
        ListView<String> listView = new ListView<String>("ajaxRadioChoice", AJAX_RADIO_TYPES) {
            protected void populateItem(final ListItem<String> it) {
                it.add(new Radio("ajaxRadio", it.getModel()));
                it.add(new Label("ajaxRadioLabel", it.getModelObject()));
                it.add(new AjaxEventBehavior("onclick") {
                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        System.out.println("HomePage.onEvent:" + it.getModelObject());
                        System.out.println("getState() = " + getState());

                        getState().setAjaxRadioSelect(it.getModelObject());

                        target.add(ajaxRadioDiv);
                        target.add(optionalTextField);
                    }
                });
            }
        };
        radioGroup.add(listView);
        ajaxRadioDiv.add(radioGroup);
        ajaxRadioDiv.setOutputMarkupId(true);
        return ajaxRadioDiv;
    }

    private String getSelected() {
        return getState().getAjaxRadioSelect();
    }

    private State getState() {
        return (State) getDefaultModelObject();
    }
}
