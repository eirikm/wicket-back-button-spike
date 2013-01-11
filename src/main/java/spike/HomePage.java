package spike;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class HomePage extends WebPage {

    private static final List<String> AJAX_RADIO_TYPES = asList("ON", "OFF");
    private static final List<String> CHECKBOXES = asList("Alt 1", "Alt 2", "Alt 3");
    private Component checkboxes;

    public HomePage() {
        super(Model.of(new State("OFF", asList("Alt 1"))));
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

        form.add(createAjaxRadioGroup());
        checkboxes = createCheckboxes();
        form.add(checkboxes);

        return form;
    }

    private Component createCheckboxes() {
        WebMarkupContainer container = new WebMarkupContainer("checkboxContainer") {
            @Override
            public boolean isVisible() {
                return "ON".equals(getState().getAjaxRadioSelect());
            }
        };
        ListView<String> listView = new ListView<String>("checkboxListView", CHECKBOXES) {
            @Override
            protected void populateItem(ListItem<String> item) {
                final String string = item.getModelObject();
                CheckBox checkBox = new CheckBox("check", new IModel<Boolean>() {
                    @Override
                    public Boolean getObject() {
                        List<String> alternatives = getState().getAlternatives();
                        return alternatives.contains(string);
                    }

                    @Override
                    public void setObject(Boolean object) {
                        if (object == true) {
                            getState().getAlternatives().add(string);
                        } else {
                            getState().getAlternatives().remove(string);
                        }

                    }

                    @Override
                    public void detach() {
                    }
                });
                checkBox.setOutputMarkupId(true);
                item.add(checkBox);
                item.add(new Label("name", string));
            }
        };
        container.add(listView);
        container.setOutputMarkupId(true);
        container.setOutputMarkupPlaceholderTag(true);
        return container;
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
                        target.add(checkboxes);
                    }
                });
            }
        };
        radioGroup.add(listView);
        ajaxRadioDiv.add(radioGroup);
        ajaxRadioDiv.setOutputMarkupId(true);
        return ajaxRadioDiv;
    }

    private State getState() {
        return (State) getDefaultModelObject();
    }
}
