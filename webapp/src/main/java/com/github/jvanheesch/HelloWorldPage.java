package com.github.jvanheesch;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class HelloWorldPage extends WebPage {
    private static final long serialVersionUID = 1662231773525391863L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new Label("visibleLabel", Model.of("visibleLabel")));
        this.add(new Label("invisibleLabel", Model.of("invisibleLabel")));
        this.add(new TextField<>("disabledTextField", Model.of("disabledTextField")));
        this.add(new TextField<>("enabledTextField", Model.of("enabledTextField")));
    }
}
