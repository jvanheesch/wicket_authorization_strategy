package com.github.jvanheesch;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.action.ActionAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.action.IActionAuthorizer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWebApplication extends WebApplication {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HelloWorldPage.class;
    }

    @Override
    protected void init() {
        super.init();

        ActionAuthorizationStrategy authorizationStrategy = new ActionAuthorizationStrategy();
        IActionAuthorizer renderAuthorizer = new IActionAuthorizer() {
            private static final long serialVersionUID = -4336699418433528360L;

            @Override
            public Action getAction() {
                return new Action(Action.RENDER);
            }

            @Override
            public boolean authorizeAction(Component component) {
                return !component.getId().contains("invisible");
            }
        };
        IActionAuthorizer enableAuthorizer = new IActionAuthorizer() {
            private static final long serialVersionUID = 4002925959601851285L;

            @Override
            public Action getAction() {
                return new Action(Action.ENABLE);
            }

            @Override
            public boolean authorizeAction(Component component) {
                return !component.getId().contains("disabled");
            }
        };
        authorizationStrategy.addActionAuthorizer(renderAuthorizer);
        authorizationStrategy.addActionAuthorizer(enableAuthorizer);
        this.getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyWebApplication.class, args);
    }
}
