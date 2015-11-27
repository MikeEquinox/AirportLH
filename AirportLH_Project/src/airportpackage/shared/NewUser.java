package airportpackage.shared;

import airportpackage.client.GreetingService;
import airportpackage.client.GreetingServiceAsync;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class NewUser extends Composite {

	/**
	 * Create Client logger
	 */
	Logger clientLogger = Logger.getLogger("clientLogger");

	public NewUser(AbsolutePanel absolutePanel1) {

		final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

		AbsolutePanel absolutePanel = new AbsolutePanel();

		Label labelUser = new Label("Новый пользователь");
		absolutePanel.add(labelUser, 475, 50);

		final TextBox userTextBox = new TextBox();
		absolutePanel.add(userTextBox, 610, 50);

		Label labelPassword = new Label("Пароль");
		absolutePanel.add(labelPassword, 475, 100);

		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		absolutePanel.add(passwordTextBox, 610, 100);

		Label labelConfirmPassword = new Label("Подтвердите пароль");
		absolutePanel.add(labelConfirmPassword, 475, 150);

		final PasswordTextBox passwordTextBox_1 = new PasswordTextBox();
		absolutePanel.add(passwordTextBox_1, 610, 150);

		Button button = new Button("New button");
		button.setText("Зарегистрировать");
		absolutePanel.add(button, 540, 220);

		absolutePanel1.add(absolutePanel);

		initWidget(absolutePanel);

		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String s1 = userTextBox.getText();
				String s2 = passwordTextBox.getText();
				String s3 = passwordTextBox_1.getText();
				if (s2.equals(s3)) {

					clientLogger.log(Level.INFO, "Composite NewUser, method newUser()");
					greetingService.newUser(s1, s2, new AsyncCallback<String>() {

						@Override
						public void onSuccess(String result) {

							if (result.equals("inserted")) {
								clientLogger.log(Level.INFO, "Composite NewUser, method newUser(), new user inserted");
								Window.alert("Пользователь зарегистрирован");
							}
							
						}

						@Override
						public void onFailure(Throwable caught) {

							clientLogger.log(Level.SEVERE, "greetingService.newUser(), AsyncCallback failure. " + caught.getMessage());
							Window.alert("Result not ok");
						}
					});

				} else {
					Window.alert("Введите правильный пароль");
				}
			}
		});

	}

}