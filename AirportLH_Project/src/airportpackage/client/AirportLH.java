package airportpackage.client;

import airportpackage.shared.Aircraft;
import airportpackage.shared.Crew;
import airportpackage.shared.Fleet;
import airportpackage.shared.NewUser;
import airportpackage.shared.Staff;
import airportpackage.shared.Timetable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.google.gwt.logging.client.LoggingPopup;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AirportLH implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	/**
	 * Client logger creation
	 */
	final Logger clientLogger = Logger.getLogger("clientLogger");


	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	// DOM elements
	RootLayoutPanel rootLayoutPanel;
	private TabLayoutPanel tabLayoutPanel;
	protected List<Timetable> timetableList;
	private CellTable<Staff> cellTable6;
	private CellTable<Staff> cellTable7;
	private NewUser newUser;

	// Data providers
	private ListDataProvider<Staff> provider1;
	private ListDataProvider<Staff> provider2;
	private ListDataProvider<Aircraft> provider3;
	private ListDataProvider<Aircraft> provider4;
	private ListDataProvider<Timetable> provider5;
	private ListDataProvider<Timetable> provider5A;
	private ListDataProvider<Staff> provider6;
	private ListDataProvider<Staff> provider7;
	private ListDataProvider<Fleet> provider8;

	// block all tabs excluding 1 tab
	private HandlerRegistration regHandler1;
	// block 6 tab
	private HandlerRegistration regHandler2;
	// block 7 tab
	private HandlerRegistration regHandler3;

	// var - crewId to change
	private int forwardCrewId = 1;
	// var - staff to change in crew
	private int forwardStaffIdOld;
	private String forwardStaffPosition = "";
	private String forwardStaffFirstName = "";
	private String forwardStaffLastName = "";
	// var new staff in crew
	private int forwardStaffIdNew;
	// index of changed item
	private int indexPosition;

	/**
	 * @param max number of crews
	 */
	private static final int CREW_LIMIT = 7;


	private Column<Timetable, String> deleteButton6;


	private Column<Timetable, String> columnCrewId1;

	

	/**
	 * Handler
	 * Update staff in selected crew
	 */
	private final class FieldUpdaterImplementation implements FieldUpdater<Staff, String> {
		@Override
		public void update(int index, Staff object, String value) {

			clientLogger.log(Level.INFO, "Редактирование списка летных бригад on monitor screen");
			clientLogger.log(Level.INFO, "Changing staff LastNameNew " + object.getLastName());

			forwardStaffIdNew = object.getStaffId();
			clientLogger.log(Level.INFO, "Changing staff forwardStaffIdNew " + forwardStaffIdNew);

			/**
			 * greetingService.getCrewById()
			 */
			greetingService.getCrewById(forwardCrewId, new AsyncCallback<Crew>() {
				@Override
				public void onFailure(Throwable caught) {
					clientLogger.log(Level.SEVERE,
							"greetingService.getCrewById(), AsyncCallback failure. " + caught.getMessage());
				}

				@Override
				public void onSuccess(Crew crew) {

					clientLogger.log(Level.INFO,
							"greetingService.getCrewById, update employee in the crew, position: "
									+ forwardStaffPosition);
					clientLogger.log(Level.INFO,
							"greetingService.getCrewById, update employee in the crew, id: " + forwardStaffIdNew);
					clientLogger.log(Level.INFO,
							"greetingService.getCrewById, update employee in the crew, indexPosition: "
									+ indexPosition);

					switch (indexPosition) {

					case 0:
						crew.setCpId(forwardStaffIdNew);
						break;
					case 1:
						crew.setFlightAt1Id(forwardStaffIdNew);
						break;
					case 2:
						crew.setFlightAt2Id(forwardStaffIdNew);
						break;
					case 3:
						crew.setFlightAt3Id(forwardStaffIdNew);
						break;
					case 4:
						crew.setFlightAt4Id(forwardStaffIdNew);
						break;
					case 5:
						crew.setFlightAt5Id(forwardStaffIdNew);
						break;
					case 6:
						crew.setFlightAt6Id(forwardStaffIdNew);
						break;
					case 7:
						crew.setFlightAt7Id(forwardStaffIdNew);
						break;
					case 8:
						crew.setFlightAt8Id(forwardStaffIdNew);
						break;
					case 9:
						crew.setFlightAt9Id(forwardStaffIdNew);
						break;
					case 10:
						crew.setFlightAt10Id(forwardStaffIdNew);
						break;
					case 11:
						crew.setPicId(forwardStaffIdNew);
						break;
					case 12:
						crew.setPurser1Id(forwardStaffIdNew);
						break;
					case 13:
						crew.setPurser2Id(forwardStaffIdNew);
						break;
					case 14:
						crew.setSoId(forwardStaffIdNew);
						break;
					default:
						break;

					}

					/**
					 * Update crew greetingService.updateCrew()
					 */
					greetingService.updateCrew(crew, new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							clientLogger.log(Level.SEVERE,
									"greetingService.updateCrew(), AsyncCallback failure. " + caught.getMessage());

						}

						@Override
						public void onSuccess(String string) {

							// Window.alert("Произошла смена летного состава
							// № " + forwardCrewId);
							clientLogger.log(Level.INFO,
									"ListDataProviderExtension7, method updateCrew(), update employee in the crew "
											+ forwardCrewId);

							tabLayoutPanel.selectTab(5);
							
							//????????????????????? всегда ли отрабатывает
							 regHandler2 = tabLayoutPanel
							 .addBeforeSelectionHandler(new
							 BeforeSelectionHandlerImplementation2());
							 regHandler3 = tabLayoutPanel
							 .addBeforeSelectionHandler(new
							 BeforeSelectionHandlerImplementation3());

						}
					});

					/**
					 * staffId blocked (employee is in crew)
					 * greetingService.updateFlag()
					 * 
					 * @param forwardStaffIdNew
					 */
					greetingService.updateFlag(forwardStaffIdNew, true, new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							clientLogger.log(Level.SEVERE,
									"greetingService.updateFlag(forwardStaffIdOld), AsyncCallback failure. "
											+ caught.getMessage());

						}

						@Override
						public void onSuccess(String string) {

							clientLogger.log(Level.INFO,
									"greetingService.updateFlag(), staffId blocked: " + forwardStaffIdNew);

						}
					});

					/**
					 * staffId released (employee is out of crew)
					 * greetingService.updateFlag()
					 * 
					 * @param forwardStaffIdNew
					 */
					greetingService.updateFlag(forwardStaffIdOld, false, new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							clientLogger.log(Level.SEVERE,
									"greetingService.updateFlag(forwardStaffIdOld), AsyncCallback failure. "
											+ caught.getMessage());

						}

						@Override
						public void onSuccess(String string) {

							clientLogger.log(Level.INFO,
									"greetingService.updateFlag(), staffId released: " + forwardStaffIdOld);

						}
					});

				}
			});

		}
	}

	
	/**
	 * Handler
	 * Button Удалить бригаду 
	 */
	private final class ClickHandlerImplementation6 implements ClickHandler {

		public ClickHandlerImplementation6() {
		}

		public void onClick(ClickEvent event) {

			if (Window.confirm("Уверены, что надо удалить бригаду " + forwardCrewId + " ?") == true) {
			greetingService.deleteCrewById(forwardCrewId, new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
					clientLogger.log(Level.SEVERE,
							"greetingService.deleteCrewById(), AsyncCallback failure. "
									+ caught.getMessage());
				}

				@Override
				public void onSuccess(String result) {

					Window.alert("Бригада удалена");
					clientLogger.log(Level.INFO, "greetingService.deleteCrewById(), crewId "
							+ forwardCrewId + " deleted");		
					tabLayoutPanel.selectTab(5);
				}
			});
			
			} else {
				Window.alert("Удаление отменено");
				clientLogger.log(Level.INFO, "greetingService.deleteCrewById(), deleting of crew "
						+ forwardCrewId + " cancelled");		
			}

		}
	}
	

	/**
	 * Handler
	 * Delete flight
	 */
	private final class FieldUpdaterImplementation4 implements FieldUpdater<Timetable, String> {
		@Override
		public void update(int index, Timetable object, String value) {

			final String flightNumberI = object.getFlightNumber();
			clientLogger.log(Level.INFO, "Try to delete flight: " + flightNumberI);

			if (Window.confirm("Уверены, что надо удалить " + flightNumberI + " ?") == true) {
				greetingService.deleteTimetableByFlightNumber(flightNumberI, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						clientLogger.log(Level.SEVERE,
								"greetingService.deleteTimetableByFlightNumber(), AsyncCallback failure. "
										+ caught.getMessage());
						// Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(String result) {

						Window.alert("Рейс удален.");
						clientLogger.log(Level.INFO, "greetingService.deleteTimetableByFlightNumber(), flightNumber "
								+ flightNumberI + " deleted");
					}

				});

			} else
				Window.alert("Удаление отменено");
			clientLogger.log(Level.INFO, "greetingService.deleteTimetableByFlightNumber(), deleting of flightNumber "
					+ flightNumberI + " cancelled");

		}
	}

	/**
	 * Handler
	 * Update staff in crew in the next tab
	 */
	private final class FieldUpdaterImplementation3 implements FieldUpdater<Staff, String> {
		@Override
		public void update(int index, Staff object, String value) {

			tabLayoutPanel.selectTab(7);
			regHandler3.removeHandler();
			forwardStaffPosition = object.getPosition();
			forwardStaffFirstName = object.getFirstName();
			forwardStaffLastName = object.getLastName();
			forwardStaffIdOld = object.getStaffId();
			indexPosition = index;

			clientLogger.log(Level.INFO, "Changing staff position: " + forwardStaffPosition + "on monitor screen");
			clientLogger.log(Level.INFO, "Changing staff old Id " + forwardStaffIdOld);
			clientLogger.log(Level.INFO, "Changing staff FirstName " + forwardStaffFirstName);
			clientLogger.log(Level.INFO, "Changing staff LastName " + forwardStaffLastName);

//			provider7 = new ListDataProviderExtension7();
			provider7 = ListDataProviderExtension7.getInstance(forwardStaffPosition);
			provider7.addDataDisplay(cellTable7);

		}
	}

	/**
	 * Handler
	 * Button Все рейсы
	 */
	private final class ClickHandlerImplementation5 implements ClickHandler {
		private final CellTable<Timetable> cellTable5;

		private ClickHandlerImplementation5(CellTable<Timetable> cellTable5) {
			this.cellTable5 = cellTable5;
		}

		public void onClick(ClickEvent event) {

			clientLogger.log(Level.INFO, "Button Все рейсы has been clicked");
//			provider5A = new ListDataProviderExtension5A();
			provider5A = ListDataProviderExtension5A.getInstance();
			provider5A.addDataDisplay(cellTable5);
			// Window.alert("Все рейсы");
		}
	}

	/**
	 * Handler
	 * Button Все назначенные рейсы 
	 */
	private final class ClickHandlerImplementation4 implements ClickHandler {
		private final CellTable<Timetable> cellTable5;

		private ClickHandlerImplementation4(CellTable<Timetable> cellTable5) {
			this.cellTable5 = cellTable5;
		}

		public void onClick(ClickEvent event) {

			clientLogger.log(Level.INFO, "Button Все назначенные рейсы has been clicked");
//			provider5 = new ListDataProviderExtension5();
			provider5 = ListDataProviderExtension5.getInstance(); 
			provider5.addDataDisplay(cellTable5);
			// Window.alert("Все назначенные рейсы");
		}
	}

	/**
	 * Handler
	 * Changing CREW NUMBER of selected flight
	 */
	private final class FieldUpdaterImplementation2 implements FieldUpdater<Timetable, String> {
		@Override
		public void update(int index, Timetable object, String value) {

			final Integer valueI = Integer.valueOf(value);

			if (valueI > 0 & valueI < CREW_LIMIT) {
				clientLogger.log(Level.INFO, "Changing crew number of this flight: " + object.getFlightNumber());
				clientLogger.log(Level.INFO, "Current crew number of this flight: " + object.getCrewId());
				object.setCrewId(valueI);

				greetingService.updateTimetableByFlightNumber(object, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						clientLogger.log(Level.SEVERE,
								"greetingService.getTimetableByFlightNumber(), AsyncCallback failure. "
										+ caught.getMessage());
						// Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(String result) {

						// Window.alert("Новая летная бригада выбрана");
						clientLogger.log(Level.INFO, "greetingService.getTimetableByFlightNumber(), new crew number "
								+ valueI + " inserted");
					}

				});

			} // end if
			else
				Window.alert("Номер летной бригады: от 1 до " + CREW_LIMIT);
			clientLogger.log(Level.INFO, "columnCrewId1.setFieldUpdater(), wrong number of crew");

		}
	}

	/**
	 * Handler
	 * Changing CREW (STAFF) of selected flight Shows next tab to change crew
	 */
	private final class FieldUpdaterImplementation1 implements FieldUpdater<Timetable, String> {
		@Override
		public void update(int index, Timetable object, String value) {

			tabLayoutPanel.selectTab(6);

			regHandler2.removeHandler();
			forwardCrewId = object.getCrewId();
//			Window.alert("Изменяем состав " + forwardCrewId);
			
			clientLogger.log(Level.INFO, "Changing crew " + forwardCrewId + " on monitor screen");
			clientLogger.log(Level.INFO, "Changing crew: " + forwardCrewId);
//			provider6 = new ListDataProviderExtension6();
			provider6 = ListDataProviderExtension6.getInstance(forwardCrewId);
			provider6.addDataDisplay(cellTable6);

		}
	}

	/**
	 * BeforeSelectionHandlerImplementation1 blocks tabs excluding first tab
	 */
	private final class BeforeSelectionHandlerImplementation1 implements BeforeSelectionHandler<Integer> {
		@Override
		public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {

			// only first tab is active
			if (event.getItem() >= 1) {
				// Canceling the event prevents the tab from being selected.
				event.cancel();
			}
		}
	}

	/**
	 * BeforeSelectionHandlerImplementation2 blocks 6 tab
	 */
	private final class BeforeSelectionHandlerImplementation2 implements BeforeSelectionHandler<Integer> {
		@Override
		public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {

			// only first tab is active
			if (event.getItem() == 6) {
				// Canceling the event prevents the tab from being selected.
				event.cancel();
			}
		}
	}

	/**
	 * BeforeSelectionHandlerImplementation3 blocks 7 tab
	 */
	private final class BeforeSelectionHandlerImplementation3 implements BeforeSelectionHandler<Integer> {
		@Override
		public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {

			// only first tab is active
			if (event.getItem() == 7) {
				// Canceling the event prevents the tab from being selected.
				event.cancel();
			}
		}
	}

	/**
	 * Class ClickHandlerImplementation1 - registration in app
	 * 
	 * @param passwordTextBox
	 * @param userTextBox
	 * @param buttonExit
	 * @param buttonNewUser
	 * @param activeUserTextBox
	 */
	private final class ClickHandlerImplementation1 implements ClickHandler {
		private final PasswordTextBox passwordTextBoxD;
		private final TextBox userTextBoxD;
		private final Button buttonExitD;
		private final Button buttonNewUserD;
		private final TextBox activeUserTextBoxD;

		private ClickHandlerImplementation1(PasswordTextBox passwordTextBox, TextBox userTextBox, Button buttonExit,
				Button buttonNewUser, TextBox activeUserTextBox) {
			passwordTextBoxD = passwordTextBox;
			buttonExitD = buttonExit;
			userTextBoxD = userTextBox;
			buttonNewUserD = buttonNewUser;
			activeUserTextBoxD = activeUserTextBox;
		}

		public void onClick(ClickEvent event) {
			String s1 = userTextBoxD.getText();
			String s2 = passwordTextBoxD.getText();

			greetingService.registration(s1, s2, new AsyncCallback<String>() {
				@Override
				public void onSuccess(String result) {

					clientLogger.log(Level.INFO, "Set rights: " + result);
					if (result.equals("ADM")) {
						// remove tabPanel blocks
						regHandler1.removeHandler();
						clientLogger.log(Level.INFO, "ClickHandlerImplementation1: admin mode activated");
						// Window.alert("Активирован режим администратора");

						buttonExitD.setEnabled(true);
						activeUserTextBoxD.setText(userTextBoxD.getText());
						if (userTextBoxD.getText().equals("admin"))
							buttonNewUserD.setEnabled(true);
						//Button Delete flight
						deleteButton6.setFieldUpdater(new FieldUpdaterImplementation4());
						//Field Update crew
						columnCrewId1.setFieldUpdater(new FieldUpdaterImplementation2());

					} else if (result.equals("DIS")) {
						clientLogger.log(Level.INFO, "ClickHandlerImplementation1: dispatcher mode activated.");
						// Window.alert("Активирован режим диспетчера");
						regHandler1.removeHandler();
						buttonExitD.setEnabled(true);
						activeUserTextBoxD.setText(userTextBoxD.getText());
						//Button Delete flight
						deleteButton6.setFieldUpdater(null); 
						//Field Update crew
						columnCrewId1.setFieldUpdater(null);

					}
				}

				@Override
				public void onFailure(Throwable caught) {
					clientLogger.log(Level.SEVERE,
							"greetingService.registration(), AsyncCallback failure. " + caught.getMessage());
					Window.alert("Result not ok");
				}
			});

		}
	}

	/**
	 * ClickHandlerImplementation2 - exiting from app
	 */
	private final class ClickHandlerImplementation2 implements ClickHandler {

		private final PasswordTextBox passwordTextBoxE;
		private final TextBox userTextBoxE;
		private final Button buttonExitE;
		private final Button buttonNewUserE;
		private final TextBox activeUserTextBoxE;

		private ClickHandlerImplementation2(PasswordTextBox passwordTextBox, TextBox userTextBox, Button buttonExit,
				Button buttonNewUser, TextBox activeUserTextBox) {
			passwordTextBoxE = passwordTextBox;
			buttonExitE = buttonExit;
			userTextBoxE = userTextBox;
			buttonNewUserE = buttonNewUser;
			activeUserTextBoxE = activeUserTextBox;
		}

		public void onClick(ClickEvent event) {

			// Exiting app
			regHandlerActivation();
			buttonExitE.setEnabled(false);
			activeUserTextBoxE.setText("");
			userTextBoxE.setValue("");
			passwordTextBoxE.setValue("");
			buttonNewUserE.setEnabled(false);

			if (newUser.isVisible())
				newUser.setVisible(false);

			clientLogger.log(Level.INFO,
					"ClickHandlerImplementation2: " + activeUserTextBoxE.getText() + " mode deactivated.");
			Window.alert("До свидания!");

		}
	}

	/**
	 * ClickHandlerImplementation3 - new user creation
	 */
	private final class ClickHandlerImplementation3 implements ClickHandler {

		private final AbsolutePanel absolutePanelD;

		private ClickHandlerImplementation3(AbsolutePanel absolutePanel) {

			absolutePanelD = absolutePanel;
		}

		public void onClick(ClickEvent event) {

			// newUser = new NewUser(absolutePanelD);
			absolutePanelD.add(newUser);
			if (newUser.isAttached())
				newUser.setVisible(true);
			clientLogger.log(Level.INFO, "ClickHandlerImplementation3: new user creation in progress.");

		}
	}



	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------
	public void onModuleLoad() {

		/**
		 * Register a Default Uncaught Exception Handler //
		 */
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				clientLogger.log(Level.SEVERE, "My Global Exception handler" + e.getMessage());
			}
		});
		
		/**
		 * Client logger configuration
		 */
		clientLogger.setLevel(Level.INFO);
		// PopupPanel loggingPopup = new LoggingPopup();
		// loggingPopup.setPopupPosition(400, 700);
		// loggingPopup.setWidth("500px");
		// loggingPopup.getWidget().setWidth("500px");
		// clientLogger.addHandler(new HasWidgetsLogHandler(loggingPopup));

		rootLayoutPanel = RootLayoutPanel.get();
		tabLayoutPanel = new TabLayoutPanel(2.5, Unit.EM);
		tabLayoutPanel.setHeight("800px");

		AbsolutePanel absolutePanel1 = new AbsolutePanel();
		absolutePanel1.setSize("800px", "600px");

		Label labelRegisteredUser = new Label("Зарегистрированные пользователи");
		absolutePanel1.add(labelRegisteredUser, 110, 10);

		Label labelUsername = new Label("Пользователь");
		absolutePanel1.add(labelUsername, 75, 50);

		TextBox userTextBox = new TextBox();
		absolutePanel1.add(userTextBox, 200, 50);

		Label labelPassword = new Label("Пароль");
		absolutePanel1.add(labelPassword, 75, 100);

		PasswordTextBox passwordTextBox = new PasswordTextBox();
		absolutePanel1.add(passwordTextBox, 200, 100);

		Button buttonExit = new Button("New button");
		buttonExit.setText("Выйти из системы");
		absolutePanel1.add(buttonExit, 200, 210);
		buttonExit.setEnabled(false);

		Button buttonNewUser = new Button("New User");
		buttonNewUser.addClickHandler(new ClickHandlerImplementation3(absolutePanel1));
		buttonNewUser.setText("Новый пользователь");
		absolutePanel1.add(buttonNewUser, 200, 260);
		buttonNewUser.setEnabled(false);

		Label labelActiveUser = new Label("В системе работает: ");
		absolutePanel1.add(labelActiveUser, 75, 340);

		TextBox activeUserTextBox = new TextBox();
		absolutePanel1.add(activeUserTextBox, 250, 340);
		activeUserTextBox.setText(userTextBox.getText());

		activeUserTextBox.setReadOnly(true);

		Button buttonEnter = new Button("New button");
		buttonEnter.setText("Зарегистрироваться");
		absolutePanel1.add(buttonEnter, 200, 160);

		newUser = new NewUser(absolutePanel1);

		buttonExit.addClickHandler(new ClickHandlerImplementation2(passwordTextBox, userTextBox, buttonExit,
				buttonNewUser, activeUserTextBox));
		buttonEnter.addClickHandler(new ClickHandlerImplementation1(passwordTextBox, userTextBox, buttonExit,
				buttonNewUser, activeUserTextBox));

		tabLayoutPanel.add(absolutePanel1, "Регистрация");

		// ----------------------------------------------------------------------------
		VerticalPanel verticalPanel1 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Staff> cellTable1 = new CellTable<Staff>();
		// Display 20 rows in one page
		cellTable1.setPageSize(20);

		// Add a text columns to show the staff
		TextColumn<Staff> columnGender = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getGender();
			}
		};
		cellTable1.addColumn(columnGender, "Обращение");
		
		TextColumn<Staff> columnFirstName = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getFirstName();
			}
		};
		cellTable1.addColumn(columnFirstName, "Имя");

		TextColumn<Staff> columnLastName = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getLastName();
			}
		};
		cellTable1.addColumn(columnLastName, "Фамилия");

		TextColumn<Staff> columnCity = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getCity();
			}
		};
		cellTable1.addColumn(columnCity, "Город");

		TextColumn<Staff> columnCountry = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getCountry();
			}
		};
		cellTable1.addColumn(columnCountry, "Страна");

		TextColumn<Staff> columnPosition = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable1.addColumn(columnPosition, "Должность");

//		provider1 = new ListDataProviderExtension1();
		provider1 = ListDataProviderExtension1.getInstance();
				
		// add provider to cellTable
		provider1.addDataDisplay(cellTable1);

		SimplePager pagerCellTable1 = new SimplePager(TextLocation.CENTER, true, true);
		pagerCellTable1.setDisplay(cellTable1);

		verticalPanel1.add(cellTable1);
		verticalPanel1.add(pagerCellTable1);

		tabLayoutPanel.add(verticalPanel1, "Список летного состава");

		// ----------------------------------------------------------------------------
		VerticalPanel verticalPanel2 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Staff> cellTable2 = new CellTable<Staff>();
		// Display 15 rows in one page
		cellTable2.setPageSize(15);

		// Add a text columns to show the staff
		TextColumn<Staff> columnFirstName2 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getFirstName();
			}
		};
		cellTable2.addColumn(columnFirstName2, "Имя");

		TextColumn<Staff> columnLastName2 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getLastName();
			}
		};
		cellTable2.addColumn(columnLastName2, "Фамилия");

		TextColumn<Staff> columnPosition2 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable2.addColumn(columnPosition2, "Должность");

//		provider2 = new ListDataProviderExtension2();
		provider2 = ListDataProviderExtension2.getInstance();
		
		// add provider to cellTable
		provider2.addDataDisplay(cellTable2);

		SimplePager pagerCellTable2 = new SimplePager(TextLocation.CENTER, false, false);
		pagerCellTable2.setDisplay(cellTable2);

		verticalPanel2.add(cellTable2);
		verticalPanel2.add(pagerCellTable2);

		tabLayoutPanel.add(verticalPanel2, "Список летных бригад");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel3 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Aircraft> cellTable3 = new CellTable<Aircraft>();
		// Display 8 rows in one page
		cellTable3.setPageSize(8);

		// Add a text columns to show the staff
		TextColumn<Aircraft> columnAircraftName1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getAircraftName();
			}
		};
		cellTable3.addColumn(columnAircraftName1, "Название самолета");

		TextColumn<Aircraft> columnLength1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getLength() * 1e2) / 1e2;
			}
		};
		cellTable3.addColumn(columnLength1, "Длина, м");

		TextColumn<Aircraft> columnWingspan1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getWingspan() * 1e2) / 1e2;
			}
		};
		cellTable3.addColumn(columnWingspan1, "Размах крыльев, м");

		TextColumn<Aircraft> columnHeight1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getHeight() * 1e2) / 1e2;
			}
		};
		cellTable3.addColumn(columnHeight1, "Высота самолета, м");

		TextColumn<Aircraft> columnMaxTakeOffWeight1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxTakeoffWeight();
			}
		};
		cellTable3.addColumn(columnMaxTakeOffWeight1, "Макс. вес при взлете, тн.");

		TextColumn<Aircraft> columnMaxCruiseSpeed1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxCruiseSpeed();
			}
		};
		cellTable3.addColumn(columnMaxCruiseSpeed1, "Макс. курсовая скорость, км/ч");

		TextColumn<Aircraft> columnMaxCruiseAltitude1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxCruiseAltitude();
			}
		};
		cellTable3.addColumn(columnMaxCruiseAltitude1, "Макс. высота полета, м");

		TextColumn<Aircraft> columnFlightRange1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getFlightRange();
			}
		};
		cellTable3.addColumn(columnFlightRange1, "Дальность полета, км");

		TextColumn<Aircraft> columnEngines1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getEngines();
			}
		};
		cellTable3.addColumn(columnEngines1, "Двигатели");

		TextColumn<Aircraft> columnSeats1 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getSeats();
			}
		};
		cellTable3.addColumn(columnSeats1, "Места");

//		provider3 = new ListDataProviderExtension3();
		provider3 = ListDataProviderExtension3.getInstance();

		// add provider to cellTable
		provider3.addDataDisplay(cellTable3);

		SimplePager pagerCellTable3 = new SimplePager(TextLocation.CENTER, true, true);
		pagerCellTable3.setDisplay(cellTable3);

		verticalPanel3.add(cellTable3);
		verticalPanel3.add(pagerCellTable3);

		tabLayoutPanel.add(verticalPanel3, "Список типов самолетов");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel4 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Aircraft> cellTable4 = new CellTable<Aircraft>();
		// Display 8 rows in one page
		cellTable4.setPageSize(8);

		// Add a text columns to show the staff
		TextColumn<Aircraft> columnAircraftName2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getAircraftName();
			}
		};
		cellTable4.addColumn(columnAircraftName2, "Название самолета");

		TextColumn<Aircraft> columnLength2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getLength() * 1e2) / 1e2;
			}
		};
		cellTable4.addColumn(columnLength2, "Длина, м");

		TextColumn<Aircraft> columnWingspan2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getWingspan() * 1e2) / 1e2;
			}
		};
		cellTable4.addColumn(columnWingspan2, "Размах крыльев, м");

		TextColumn<Aircraft> columnHeight2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + Math.floor(object.getHeight() * 1e2) / 1e2;
			}
		};
		cellTable4.addColumn(columnHeight2, "Высота самолета, м");

		TextColumn<Aircraft> columnMaxTakeOffWeight2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxTakeoffWeight();
			}
		};
		cellTable4.addColumn(columnMaxTakeOffWeight1, "Макс. вес при взлете, тн.");

		TextColumn<Aircraft> columnMaxCruiseSpeed2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxCruiseSpeed();
			}
		};
		cellTable4.addColumn(columnMaxCruiseSpeed2, "Макс. курсовая скорость, км/ч");

		TextColumn<Aircraft> columnMaxCruiseAltitude2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getMaxCruiseAltitude();
			}
		};
		cellTable4.addColumn(columnMaxCruiseAltitude2, "Макс. высота полета, м");

		TextColumn<Aircraft> columnFlightRange2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return "" + object.getFlightRange();
			}
		};
		cellTable4.addColumn(columnFlightRange2, "Дальность полета, км");

		TextColumn<Aircraft> columnEngines2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getEngines();
			}
		};
		cellTable4.addColumn(columnEngines2, "Двигатели");

		TextColumn<Aircraft> columnSeats2 = new TextColumn<Aircraft>() {
			@Override
			public String getValue(Aircraft object) {
				return object.getSeats();
			}
		};
		cellTable4.addColumn(columnSeats2, "Места");

//		provider4 = new ListDataProviderExtension4();
		provider4 = ListDataProviderExtension4.getInstance();
		
		// add provider to cellTable4
		provider4.addDataDisplay(cellTable4);

		SimplePager pagerCellTable4 = new SimplePager(TextLocation.CENTER, true, true);
		pagerCellTable4.setDisplay(cellTable4);

		verticalPanel4.add(cellTable4);
		verticalPanel4.add(pagerCellTable4);

		tabLayoutPanel.add(verticalPanel4, "Список самолетов");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel5 = new VerticalPanel();

		Label labelInfo = new Label("Номер летной бригады можно редактировать");
		verticalPanel5.add(labelInfo);

		Label labelInfo5 = new Label(
				"Изменение списка летных бригад заблокировано. Нажмите соответствующую кнопку для разблокирования.");
		verticalPanel5.add(labelInfo5);

		// Create a CellTable.
		final CellTable<Timetable> cellTable5 = new CellTable<Timetable>();
		// Display 10 rows in one page
		cellTable5.setPageSize(10);

		// Add a text columns to show the staff
		TextColumn<Timetable> columnFlightNumber1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return object.getFlightNumber();
			}
		};
		cellTable5.addColumn(columnFlightNumber1, "Номер рейса");

		TextColumn<Timetable> columnAirline1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return object.getAirline();
			}
		};
		cellTable5.addColumn(columnAirline1, "Авиакомпания");

		TextColumn<Timetable> columnSourceAirport1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return object.getSourceAirport();
			}
		};
		cellTable5.addColumn(columnSourceAirport1, "Аэропорт отправления");

		TextColumn<Timetable> columnDepartureDate1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return "" + object.getDepartureDate();
			}
		};
		cellTable5.addColumn(columnDepartureDate1, "Дата отправления");

		TextColumn<Timetable> columnDepartureTime1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return "" + object.getDepartureTime();
			}
		};
		cellTable5.addColumn(columnDepartureTime1, "Время отправления");

		TextColumn<Timetable> columnDestinationAirport1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return object.getDestinationAirport();
			}
		};
		cellTable5.addColumn(columnDestinationAirport1, "Аэропорт прибытия");

		TextColumn<Timetable> columnArrivalDate1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return "" + object.getArrivalDate();
			}
		};
		cellTable5.addColumn(columnArrivalDate1, "Дата прибытия");

		TextColumn<Timetable> columnArrivalTime1 = new TextColumn<Timetable>() {
			@Override
			public String getValue(Timetable object) {
				return "" + object.getArrivalTime();
			}
		};
		cellTable5.addColumn(columnArrivalTime1, "Время прибытия");

		columnCrewId1 = new Column<Timetable, String>(new EditTextCell()) {
			@Override
			public String getValue(Timetable object) {
				return "" + object.getCrewId();
			}
		};
		cellTable5.addColumn(columnCrewId1, "Номер бригады");

		// Add a ButtonCell as column to the CellTable
		Column<Timetable, String> updateButton = new Column<Timetable, String>(new ButtonCell()) {
			@Override
			public String getValue(Timetable object) {
				return "Летная бригада № " + object.getCrewId();
			}
		};
		cellTable5.addColumn(updateButton, "Изменить состав летной бригады");

		deleteButton6 = new Column<Timetable, String>(new ButtonCell()) {
			@Override
			public String getValue(Timetable object) {
				return "Удалить";
			}
		};
		cellTable5.addColumn(deleteButton6, "Удаление рейса");

		//
		// Changing CREW (STAFF) of selected flight Shows next tab to change
		// crew
		//
		updateButton.setFieldUpdater(new FieldUpdaterImplementation1());

		//
		// Changing CREW NUMBER of selected flight
		//
		columnCrewId1.setFieldUpdater(new FieldUpdaterImplementation2());

		//
		// Delete flight
		//
		deleteButton6.setFieldUpdater(new FieldUpdaterImplementation4());

//		provider5 = new ListDataProviderExtension5();
		provider5 = ListDataProviderExtension5.getInstance();
		
		// add provider to cellTable
		provider5.addDataDisplay(cellTable5);

		SimplePager pagerCellTable5 = new SimplePager(TextLocation.CENTER, true, true);
		pagerCellTable5.setDisplay(cellTable5);

		//
		// Button Все назначенные рейсы handler
		//
		Button button1 = new Button("Все назначенные рейсы");
		button1.addClickHandler(new ClickHandlerImplementation4(cellTable5));
		
		//
		// Button Все рейсы handler
		//
		Button button2 = new Button("Все рейсы");
		button2.addClickHandler(new ClickHandlerImplementation5(cellTable5));

		button1.setText("Все назначенные рейсы");
		verticalPanel5.add(button1);

		button2.setText("Все рейсы");
		verticalPanel5.add(button2);

		verticalPanel5.add(cellTable5);
		verticalPanel5.add(pagerCellTable5);

		tabLayoutPanel.add(verticalPanel5, "Табло рейсов");

		//Initialize provider5
//		button1.click();
		
		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel6 = new VerticalPanel();

		Label labelInfo6 = new Label(
				"Изменение состава летной бригады заблокировано. Нажмите соответствующую кнопку для разблокирования.");
		verticalPanel6.add(labelInfo6);

		cellTable6 = new CellTable<Staff>();
		// Display 15 rows in one page
		cellTable6.setPageSize(15);

		TextColumn<Staff> columnCrewId6 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return "" + forwardCrewId;
			}
		};
		cellTable6.addColumn(columnCrewId6, "Номер летной бригады");

		TextColumn<Staff> columnFirstName6 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return "" + object.getFirstName();
			}
		};
		cellTable6.addColumn(columnFirstName6, "Имя");

		TextColumn<Staff> columnLastName6 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getLastName();
			}
		};
		cellTable6.addColumn(columnLastName6, "Фамилия");

		TextColumn<Staff> columnPosition6 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable6.addColumn(columnPosition6, "Должность");

//		provider6 = ListDataProviderExtension6.getInstance(forwardCrewId);
//		provider6.addDataDisplay(cellTable6);

		SimplePager pagerCellTable6 = new SimplePager(TextLocation.CENTER, false, false);
		pagerCellTable6.setDisplay(cellTable6);

		verticalPanel6.add(cellTable6);
		verticalPanel6.add(pagerCellTable6);

		// Add a ButtonCell as column to the CellTable
		Column<Staff, String> updateButton6 = new Column<Staff, String>(new ButtonCell()) {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable6.addColumn(updateButton6, "Изменить состав летной бригады");

		
		//
		// Button Удалить бригаду handler
		//
		Button buttonDeleteCrew = new Button("Delete crew");
		buttonDeleteCrew.addClickHandler(new ClickHandlerImplementation6());

		buttonDeleteCrew.setText("Удалить летную бригаду");
		verticalPanel6.add(buttonDeleteCrew);
		
		//
		// Update staff in crew in the next tab
		//
		updateButton6.setFieldUpdater(new FieldUpdaterImplementation3());

		tabLayoutPanel.add(verticalPanel6, "Cостав летных бригад");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel7 = new VerticalPanel();

		cellTable7 = new CellTable<Staff>();
		// Display 15 rows in one page
		cellTable7.setPageSize(15);

		TextColumn<Staff> columnFirstName7 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getFirstName();
			}
		};
		cellTable7.addColumn(columnFirstName7, "Имя");

		TextColumn<Staff> columnLastName7 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getLastName();
			}
		};
		cellTable7.addColumn(columnLastName7, "Фамилия");

		TextColumn<Staff> columnPosition7 = new TextColumn<Staff>() {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable7.addColumn(columnPosition7, "Должность");

		// Add a ButtonCell as column to the CellTable
		Column<Staff, String> updateButton7 = new Column<Staff, String>(new ButtonCell()) {
			@Override
			public String getValue(Staff object) {
				return "Выбрать";
			}
		};
		cellTable7.addColumn(updateButton7, "Изменить состав летной бригады");

		/**
		 * Update staff in selected crew
		 */
		updateButton7.setFieldUpdater(new FieldUpdaterImplementation());

//		provider7 = ListDataProviderExtension7.getInstance(forwardStaffPosition);
//		provider7.addDataDisplay(cellTable7);

		SimplePager pagerCellTable7 = new SimplePager(TextLocation.CENTER, false, false);
		pagerCellTable7.setDisplay(cellTable7);

		verticalPanel7.add(cellTable7);
		verticalPanel7.add(pagerCellTable7);

		tabLayoutPanel.add(verticalPanel7, "Летная бригада");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel8 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Fleet> cellTable8 = new CellTable<Fleet>();
		// Display 8 rows in one page
		cellTable8.setPageSize(8);

		// Add a text columns to show the staff
		TextColumn<Fleet> columnFleetId8 = new TextColumn<Fleet>() {
			@Override
			public String getValue(Fleet object) {
				return "" + object.getFleetId();
			}
		};
		cellTable8.addColumn(columnFleetId8, "Порядковый номер");

		TextColumn<Fleet> columnRegIdNumber8 = new TextColumn<Fleet>() {
			@Override
			public String getValue(Fleet object) {
				return object.getAircraftRegId();
			}
		};
		cellTable8.addColumn(columnRegIdNumber8, "Регистрационный номер");

		TextColumn<Fleet> columnAircraftId8 = new TextColumn<Fleet>() {
			@Override
			public String getValue(Fleet object) {
				return "" + object.getAircraftId();
			}
		};
		cellTable8.addColumn(columnAircraftId8, "Тип самолета");

		TextColumn<Fleet> columnIcaoAddr8 = new TextColumn<Fleet>() {
			@Override
			public String getValue(Fleet object) {
				return object.getIcaoAddr();
			}
		};
		cellTable8.addColumn(columnIcaoAddr8, "Адрес ICAO");

//		provider8 = new ListDataProviderExtension8();
		provider8 = ListDataProviderExtension8.getInstance();

		// add provider to cellTable
		provider8.addDataDisplay(cellTable8);

		SimplePager pagerCellTable8 = new SimplePager(TextLocation.CENTER, false, false);
		pagerCellTable8.setDisplay(cellTable8);

		verticalPanel8.add(cellTable8);
		verticalPanel8.add(pagerCellTable8);

		tabLayoutPanel.add(verticalPanel8, "Рег. имена самолетов");

		// ----------------------------------------------------------------------------
		
		
		// Block all tabs till registration
		regHandlerActivation();

		rootLayoutPanel.add(tabLayoutPanel);
		tabLayoutPanel.selectTab(0);

	}

	/**
	 * Block all tabs till registration
	 */
	private void regHandlerActivation() {

		regHandler1 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementation1());
		regHandler2 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementation2());
		regHandler3 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementation3());
	}

}
