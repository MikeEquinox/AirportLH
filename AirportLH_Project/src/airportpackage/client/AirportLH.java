package airportpackage.client;

//Import log4j classes.
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import java.util.logging.Level;
import java.util.logging.Logger;

import airportpackage.shared.Aircraft;
import airportpackage.shared.Crew;
import airportpackage.shared.ExtendedPager;
import airportpackage.shared.Fleet;
import airportpackage.shared.NewUser;
import airportpackage.shared.Staff;
import airportpackage.shared.Timetable;

import java.util.List;

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
import com.google.gwt.user.client.Command;
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
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;

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
	// private static final org.apache.logging.log4j.Logger clientLogger2 =
	// LogManager
	// .getLogger("eqConsoleFile");

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
	private Column<Timetable, String> deleteButton6;
	private Column<Timetable, String> columnCrewId1;
	private Button buttonCreateCrew;
	private Button buttonDeleteCrew;
	private Button buttonRefreshCrew;
	private TextBox messageTextBox5;
	private TextBox messageTextBox6;

	public HandlerRegistration regHandler4;

	/**
	 * @param max
	 *            number of crews
	 */
	private static final int CREW_LIMIT = 15;

	/**
	 * Update OR create staff in selected crew FieldUpdaterImplementation
	 */
	private final class FieldUpdaterImplementationUpdateCrewStaff implements FieldUpdater<Staff, String> {
		@Override
		public void update(int index, Staff object, String value) {

//			 Window.alert("forwardCrewId: " + forwardCrewId);
			forwardStaffIdNew = object.getStaffId();
			clientLogger.log(Level.INFO, "Changing staff forwardStaffIdNew " + forwardStaffIdNew);

			if (forwardCrewId > 0 & forwardCrewId <= 7) {

				clientLogger.log(Level.INFO, "Редактирование списка летных бригад on monitor screen");
				clientLogger.log(Level.INFO, "Changing staff LastNameNew " + object.getLastName());

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

						// Filling existing crew
						crewFill(crew);
						
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

								clientLogger.log(Level.INFO,
										"greetingService.updateCrew(), update crew "
												+ forwardCrewId);
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
										"greetingService.updateFlag(forwardStaffIdNew), AsyncCallback failure. "
												+ caught.getMessage());

							}

							@Override
							public void onSuccess(String string) {

								clientLogger.log(Level.INFO,
										"greetingService.updateFlag(forwardStaffIdNew), staffId blocked: "
												+ forwardStaffIdNew);

								/**
								 * staffId released (employee is out of crew)
								 * greetingService.updateFlag()
								 * 
								 * @param forwardStaffIdOld
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
				});

				tabLayoutPanel.selectTab(6);
				
				// clientLogger.log(Level.INFO, "Changing crew " + forwardCrewId
				// + " on monitor screen");
				// clientLogger.log(Level.INFO, "Changing crew: " +
				// forwardCrewId);
				// regHandler2 = tabLayoutPanel
				// .addBeforeSelectionHandler(new
				// BeforeSelectionHandlerImplementationBlock6Tab());
				// regHandler3 = tabLayoutPanel
				// .addBeforeSelectionHandler(new
				// BeforeSelectionHandlerImplementationBlock7Tab());

			} else {
				if (forwardCrewId == 0) { // если ID новой бригады еще нет

//					buttonCreateCrew.setEnabled(false);
					/**
					 * Get template crew greetingService.getCrew()
					 */
					greetingService.getCrewById(0, new AsyncCallback<Crew>() {
						@Override
						public void onFailure(Throwable caught) {
							clientLogger.log(Level.SEVERE,
									"greetingService.getCrew(), AsyncCallback failure. " + caught.getMessage());

						}

						@Override
						public void onSuccess(Crew crew) {

							String s = "10";
							tabLayoutPanel.selectTab(6);
							clientLogger.log(Level.INFO, "greetingService.getCrew(), get template crew");
							while (true) {
								s = Window.prompt("Введите номер летной бригады: больше 9, меньше 16", "10");
								forwardCrewId = Integer.valueOf(s);
								if (forwardCrewId >= 10 | forwardCrewId <= 15)
									break;
							}
							crew.setCrewId(forwardCrewId);

							/**
							 * Insert crew greetingService.insertCrew()
							 */
							greetingService.insertCrew(crew, new AsyncCallback<String>() {
								@Override
								public void onFailure(Throwable caught) {
									clientLogger.log(Level.SEVERE,
											"greetingService.insertCrew(), AsyncCallback failure. "
													+ caught.getMessage());

								}

								@Override
								public void onSuccess(String string) {

									tabLayoutPanel.selectTab(6);
									clientLogger.log(Level.INFO, "greetingService.insertCrew(), new crew"
											+ forwardCrewId + "inserted in table CREWS");
								}
							});

							/**
							 * Update crew greetingService.updateCrew()
							 */
							greetingService.updateCrew(crew, new AsyncCallback<String>() {
								@Override
								public void onFailure(Throwable caught) {
									clientLogger.log(Level.SEVERE,
											"greetingService.updateCrew(), AsyncCallback failure. "
													+ caught.getMessage());

								}

								@Override
								public void onSuccess(String string) {

									clientLogger.log(Level.INFO,
											"greetingService.updateCrew(), update employee in the crew "
													+ forwardCrewId);
								}
							});

						}
					});

				} else if (forwardCrewId > 7) { // если ID новой бригады создан

					tabLayoutPanel.selectTab(6);
//					 regHandler4 = tabLayoutPanel.addBeforeSelectionHandler(new
//								 BeforeSelectionHandlerImplementationBlockAllExcl67Tab());

					Crew crew = new Crew();
					crew.setCrewId(forwardCrewId);
					/**
					 * Get new crew greetingService.getCrewById()
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
									"greetingService.updateCrew(), update employee in the crew " + forwardCrewId);

							crewFill(crew);
							Window.alert("Update crew. forwardStaffIdNew " + forwardStaffIdNew);

							/**
							 * Update crew greetingService.updateCrew()
							 */
							greetingService.updateCrew(crew, new AsyncCallback<String>() {
								@Override
								public void onFailure(Throwable caught) {
									clientLogger.log(Level.SEVERE,
											"greetingService.updateCrew(), AsyncCallback failure. "
													+ caught.getMessage());

								}

								@Override
								public void onSuccess(String string) {

									clientLogger.log(Level.INFO,
											"greetingService.updateCrew(), update employee in the crew "
													+ forwardCrewId);
								}
							});

						}
					});

				}

			}
		}

		// Methods
		/**
		 * Fill new crew or update existing crew
		 * 
		 * @param crew
		 *            - object Crew
		 */
		private Crew crewFill(Crew crew) {
			Window.alert("" + indexPosition);
			switch (indexPosition) {
			case 1:
				crew.setCpId(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting Co-Pilot: " + crew.getCpId());
				break;
			case 5:
				crew.setFlightAt1Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant1: " + crew.getFlightAt1Id());
				break;
			case 6:
				crew.setFlightAt2Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant2: " + crew.getFlightAt2Id());
				break;
			case 7:
				crew.setFlightAt3Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant3: " + crew.getFlightAt3Id());
				break;
			case 8:
				crew.setFlightAt4Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant4: " + crew.getFlightAt4Id());
				break;
			case 9:
				crew.setFlightAt5Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant5: " + crew.getFlightAt5Id());
				break;
			case 10:
				crew.setFlightAt6Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant6: " + crew.getFlightAt6Id());
				break;
			case 11:
				crew.setFlightAt7Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant7: " + crew.getFlightAt7Id());
				break;
			case 12:
				crew.setFlightAt8Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant8: " + crew.getFlightAt8Id());
				break;
			case 13:
				crew.setFlightAt9Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant9: " + crew.getFlightAt9Id());
				break;
			case 14:
				crew.setFlightAt10Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting FlightAttendant10: " + crew.getFlightAt10Id());
				break;
			case 0:
				crew.setPicId(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting Pilot-In-Command: " + crew.getPicId());
				break;
			case 3:
				crew.setPurser1Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting Purser1: " + crew.getPurser1Id());
				break;
			case 4:
				crew.setPurser2Id(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting Purser2: " + crew.getPurser2Id());
				break;
			case 2:
				crew.setSoId(forwardStaffIdNew);
				clientLogger.log(Level.INFO, "Inserting Second Officer: " + crew.getSoId());
				break;
			default:
				break;

			}
			return crew;
		}
	}

	/**
	 * Button Создать бригаду ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationCreateCrew implements ClickHandler {

		public ClickHandlerImplementationCreateCrew() {
		}

		public void onClick(ClickEvent event) {
			if (Window.confirm("Уверены, что надо создать летную бригаду?") == true) {
				clientLogger.log(Level.INFO,
						"ClickHandlerImplementationCreateCrew, creation of new crew");
				forwardCrewId = 0;
				buttonDeleteCrew.setEnabled(false);
				messageTextBox6.setText("Для обновления таблицы нажмите кнопку [Обновить летную бригаду]");

				provider6.removeDataDisplay(cellTable6);
				provider6.addDataDisplay(cellTable6);
			} else
				Window.alert("Создание летной бригады отменено");
			clientLogger.log(Level.INFO,
					"ClickHandlerImplementationCreateCrew, creation of new crew cancelled");
		}
	}

	/**
	 * Button Удалить бригаду ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationDeleteCrew implements ClickHandler {

		public ClickHandlerImplementationDeleteCrew() {
		}

		public void onClick(ClickEvent event) {

			if (Window.confirm("Уверены, что надо удалить летную бригаду " + forwardCrewId + " ?") == true) {

				greetingService.deleteCrewById(forwardCrewId, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						clientLogger.log(Level.SEVERE,
								"greetingService.deleteCrewById(), AsyncCallback failure. " + caught.getMessage());
					}

					@Override
					public void onSuccess(String result) {

						Window.alert("Летная бригада" + forwardCrewId + " удалена");
						clientLogger.log(Level.INFO,
								"greetingService.deleteCrewById(), crewId " + forwardCrewId + " has been deleted");

						regHandler2 = tabLayoutPanel
								.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementationBlock6Tab());
						regHandler3 = tabLayoutPanel
								.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementationBlock7Tab());
						tabLayoutPanel.selectTab(5);
					}
				});

			} else {
				Window.alert("Удаление летной бригады " + forwardCrewId+" отменено");
				clientLogger.log(Level.INFO,
						"greetingService.deleteCrewById(), deleting of crew " + forwardCrewId + " cancelled");
			}

		}
	}

	/**
	 * Field Delete flight FieldUpdaterImplementation
	 */
	private final class FieldUpdaterImplementationDeleteFlight implements FieldUpdater<Timetable, String> {
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
	 * Update staff in crew in the next tab FieldUpdaterImplementation
	 */
	private final class FieldUpdaterImplementationUpdateCrew implements FieldUpdater<Staff, String> {
		@Override
		public void update(int index, Staff object, String value) {

			tabLayoutPanel.selectTab(7);
			regHandler3.removeHandler();
			forwardStaffPosition = object.getPosition();
			forwardStaffFirstName = object.getFirstName();
			forwardStaffLastName = object.getLastName();
			forwardStaffIdOld = object.getStaffId();
			indexPosition = index;

			clientLogger.log(Level.INFO, "Changing staff position: " + forwardStaffPosition + " on monitor screen");
			clientLogger.log(Level.INFO, "Changing staff old Id " + forwardStaffIdOld);
			clientLogger.log(Level.INFO, "Changing staff FirstName " + forwardStaffFirstName);
			clientLogger.log(Level.INFO, "Changing staff LastName " + forwardStaffLastName);

			provider7 = ListDataProviderExtension7.getInstance(forwardStaffPosition);
			provider7.addDataDisplay(cellTable7);

		}
	}

	/**
	 * Button Все рейсы ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationAllSuspendedFlights implements ClickHandler {
		private final CellTable<Timetable> cellTable5;

		private ClickHandlerImplementationAllSuspendedFlights(CellTable<Timetable> cellTable5) {
			this.cellTable5 = cellTable5;
		}

		public void onClick(ClickEvent event) {

			clientLogger.log(Level.INFO, "Button Все рейсы has been clicked");

			provider5.removeDataDisplay(cellTable5);
			provider5A = ListDataProviderExtension5A.getInstance();
			provider5A.addDataDisplay(cellTable5);
		}
	}

	/**
	 * Button Все назначенные рейсы ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationAllActiveFlights implements ClickHandler {
		private final CellTable<Timetable> cellTable5;

		private ClickHandlerImplementationAllActiveFlights(CellTable<Timetable> cellTable5) {
			this.cellTable5 = cellTable5;
		}

		public void onClick(ClickEvent event) {

			clientLogger.log(Level.INFO, "Button Все назначенные рейсы has been clicked");

			provider5A.removeDataDisplay(cellTable5);
			provider5 = ListDataProviderExtension5.getInstance();
			provider5.addDataDisplay(cellTable5);
		}
	}

	/**
	 * Changing CREW NUMBER of selected flight FieldUpdaterImplementation
	 */
	private final class FieldUpdaterImplementationChangeFlightCrew implements FieldUpdater<Timetable, String> {
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
	 * Refresh crew after adding or inserting satff in the selected crew
	 * ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationRefreshCrew implements ClickHandler {

		private ClickHandlerImplementationRefreshCrew() {

		}

		public void onClick(ClickEvent event) {

			tabLayoutPanel.selectTab(6);

			clientLogger.log(Level.INFO, "Refreshing crew " + forwardCrewId + " on monitor screen");

			provider6.removeDataDisplay(cellTable6);
			provider6 = ListDataProviderExtension6.getInstance(forwardCrewId);
			provider6.addDataDisplay(cellTable6);

		}
	}

	/**
	 * Changing CREW (STAFF) of selected flight Shows next tab to change crew
	 * FieldUpdater
	 */
	private final class FieldUpdaterImplementationChangeCrew implements FieldUpdater<Timetable, String> {
		@Override
		public void update(int index, Timetable object, String value) {

			tabLayoutPanel.selectTab(6);

			regHandler2.removeHandler();
			forwardCrewId = object.getCrewId();

			clientLogger.log(Level.INFO, "Changing crew " + forwardCrewId + " on monitor screen");
			clientLogger.log(Level.INFO, "Changing crew: " + forwardCrewId);

			provider6 = ListDataProviderExtension6.getInstance(forwardCrewId);
			provider6.addDataDisplay(cellTable6);

		}

	}

	/**
	 * blocks tabs excluding first tab. BeforeSelectionHandler.
	 */
	private final class BeforeSelectionHandlerImplementationBlockAllTab implements BeforeSelectionHandler<Integer> {
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
	 * blocks 6 tab. BeforeSelectionHandler.
	 */
	private final class BeforeSelectionHandlerImplementationBlock6Tab implements BeforeSelectionHandler<Integer> {
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
	 * blocks 7 tab. BeforeSelectionHandler.
	 */
	private final class BeforeSelectionHandlerImplementationBlock7Tab implements BeforeSelectionHandler<Integer> {
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
	 * blocks 7 tab. BeforeSelectionHandler.
	 */
	private final class BeforeSelectionHandlerImplementationBlockAllExcl67Tab
			implements BeforeSelectionHandler<Integer> {
		@Override
		public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {

			// only first tab is active
			if (event.getItem() > 7 | event.getItem() < 6) {
				// Canceling the event prevents the tab from being selected.
				event.cancel();
			}
		}
	}

	/**
	 * registration in app ClickHandlerImplementation
	 * 
	 * @param passwordTextBox
	 * @param userTextBox
	 * @param buttonExit
	 * @param buttonNewUser
	 * @param activeUserTextBox
	 */
	private final class ClickHandlerImplementationRegistration implements ClickHandler {
		private final PasswordTextBox passwordTextBoxD;
		private final TextBox userTextBoxD;
		private final Button buttonExitD;
		private final Button buttonNewUserD;
		private final TextBox activeUserTextBoxD;

		private ClickHandlerImplementationRegistration(PasswordTextBox passwordTextBox, TextBox userTextBox,
				Button buttonExit, Button buttonNewUser, TextBox activeUserTextBox) {
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
						// Button Delete flight
						deleteButton6.setFieldUpdater(new FieldUpdaterImplementationDeleteFlight());
						// Field Update crew
						columnCrewId1.setFieldUpdater(new FieldUpdaterImplementationChangeFlightCrew());

					} else if (result.equals("DIS")) {
						clientLogger.log(Level.INFO, "ClickHandlerImplementation1: dispatcher mode activated.");
						// Window.alert("Активирован режим диспетчера");
						regHandler1.removeHandler();
						buttonExitD.setEnabled(true);
						activeUserTextBoxD.setText(userTextBoxD.getText());
						// Button Delete flight
						deleteButton6.setFieldUpdater(null);
						// Field Update crew
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
	 * exiting from app ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationSignOut implements ClickHandler {

		private final PasswordTextBox passwordTextBoxE;
		private final TextBox userTextBoxE;
		private final Button buttonExitE;
		private final Button buttonNewUserE;
		private final TextBox activeUserTextBoxE;

		private ClickHandlerImplementationSignOut(PasswordTextBox passwordTextBox, TextBox userTextBox,
				Button buttonExit, Button buttonNewUser, TextBox activeUserTextBox) {
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
	 * new user creation ClickHandlerImplementation
	 */
	private final class ClickHandlerImplementationSignOn implements ClickHandler {

		private final AbsolutePanel absolutePanelD;

		private ClickHandlerImplementationSignOn(AbsolutePanel absolutePanel) {

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
		 * Client logger configuration
		 */
		clientLogger.setLevel(Level.INFO);
		// PopupPanel loggingPopup = new LoggingPopup();
		// loggingPopup.setPopupPosition(400, 700);
		// loggingPopup.setWidth("500px");
		// loggingPopup.getWidget().setWidth("500px");
		// clientLogger.addHandler(new HasWidgetsLogHandler(loggingPopup));
		/**
		 * Register a Default Uncaught Exception Handler //
		 */
		GWT.UncaughtExceptionHandler uncaughtExceptionHandler = new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				clientLogger.log(Level.SEVERE, "My Global Exception handler: " + e.getMessage());
				Window.alert("My Global Exception handler caught an exception!" + e.getMessage());
			}
		};
		// handle the unexpected after onModuleLoad()
		GWT.setUncaughtExceptionHandler(uncaughtExceptionHandler);
		// use try/catch to handle the reset
		try {
			onModuleLoad2();
		} catch (RuntimeException ex) {
			// use our handler rather than duplicate code
			uncaughtExceptionHandler.onUncaughtException(ex);
		}
	}

	public void onModuleLoad2() {

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
		buttonNewUser.addClickHandler(new ClickHandlerImplementationSignOn(absolutePanel1));
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
		buttonEnter.setText("Войти в систему");
		absolutePanel1.add(buttonEnter, 200, 160);

		newUser = new NewUser(absolutePanel1);

		buttonExit.addClickHandler(new ClickHandlerImplementationSignOut(passwordTextBox, userTextBox, buttonExit,
				buttonNewUser, activeUserTextBox));
		buttonEnter.addClickHandler(new ClickHandlerImplementationRegistration(passwordTextBox, userTextBox, buttonExit,
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

		// provider1 = new ListDataProviderExtension1();
		provider1 = ListDataProviderExtension1.getInstance();

		// add provider to cellTable
		provider1.addDataDisplay(cellTable1);

		// SimplePager pagerCellTable1 = new SimplePager(TextLocation.CENTER,
		// true, true);
		ExtendedPager pagerCellTable1 = new ExtendedPager();
		pagerCellTable1.setPageSize(20);
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

		// provider2 = new ListDataProviderExtension2();
		provider2 = ListDataProviderExtension2.getInstance();

		// add provider to cellTable
		provider2.addDataDisplay(cellTable2);

		// SimplePager pagerCellTable2 = new SimplePager(TextLocation.CENTER,
		// false, false);
		ExtendedPager pagerCellTable2 = new ExtendedPager();
		pagerCellTable2.setPageSize(15);
		pagerCellTable2.setDisplay(cellTable2);

		verticalPanel2.add(cellTable2);
		verticalPanel2.add(pagerCellTable2);

		tabLayoutPanel.add(verticalPanel2, "Все летные бригады");

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

		// provider3 = new ListDataProviderExtension3();
		provider3 = ListDataProviderExtension3.getInstance();

		// add provider to cellTable
		provider3.addDataDisplay(cellTable3);

		// SimplePager pagerCellTable3 = new SimplePager(TextLocation.CENTER,
		// true, true);
		ExtendedPager pagerCellTable3 = new ExtendedPager();
		pagerCellTable3.setPageSize(8);
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

		provider4 = ListDataProviderExtension4.getInstance();

		// add provider to cellTable4
		provider4.addDataDisplay(cellTable4);

		// SimplePager pagerCellTable4 = new SimplePager(TextLocation.CENTER,
		// true, true);
		ExtendedPager pagerCellTable4 = new ExtendedPager();
		pagerCellTable4.setPageSize(8);
		pagerCellTable4.setDisplay(cellTable4);

		verticalPanel4.add(cellTable4);
		verticalPanel4.add(pagerCellTable4);

		tabLayoutPanel.add(verticalPanel4, "Список самолетов");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel5 = new VerticalPanel();

		verticalPanel5.setSpacing(5);

		Label labelInfo = new Label("Удалять рейс и  редактировать номер летной бригады может только администратор.");
		verticalPanel5.add(labelInfo);

		messageTextBox5 = new TextBox();
		verticalPanel5.add(messageTextBox5);
		messageTextBox5.setText(
				"Изменение состава летных бригад заблокировано. Нажмите кнопку с номером бригады для разблокирования.");
		messageTextBox5.setWidth("700px");
		messageTextBox5.setEnabled(false);

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

		// Changing CREW (STAFF) of selected flight Shows next tab to change
		// crew
		// FieldUpdaterImplementation
		updateButton.setFieldUpdater(new FieldUpdaterImplementationChangeCrew());

		// Changing CREW (NUMBER) of selected flight
		// FieldUpdaterImplementation
		columnCrewId1.setFieldUpdater(new FieldUpdaterImplementationChangeFlightCrew());

		// Button Delete flight
		// FieldUpdaterImplementation
		deleteButton6.setFieldUpdater(new FieldUpdaterImplementationDeleteFlight());

		// provider5 = new ListDataProviderExtension5();
		provider5 = ListDataProviderExtension5.getInstance();

		// add provider to cellTable
		provider5.addDataDisplay(cellTable5);

		// SimplePager pagerCellTable5 = new SimplePager(TextLocation.CENTER,
		// true, true);
		ExtendedPager pagerCellTable5 = new ExtendedPager();
		pagerCellTable5.setDisplay(cellTable5);

		// Button Все назначенные рейсы handler
		// ClickHandlerImplementation
		Button buttonAllFlights = new Button("Все назначенные рейсы");
		buttonAllFlights.addClickHandler(new ClickHandlerImplementationAllActiveFlights(cellTable5));

		// Button Все рейсы handler
		// ClickHandlerImplementation
		Button buttonAllSuspendedFlights = new Button("Все рейсы");
		buttonAllSuspendedFlights.addClickHandler(new ClickHandlerImplementationAllSuspendedFlights(cellTable5));

		buttonAllFlights.setText("Все назначенные рейсы");
		verticalPanel5.add(buttonAllFlights);

		buttonAllSuspendedFlights.setText("Все рейсы");
		verticalPanel5.add(buttonAllSuspendedFlights);

		verticalPanel5.add(cellTable5);
		verticalPanel5.add(pagerCellTable5);

		tabLayoutPanel.add(verticalPanel5, "Табло рейсов");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel6 = new VerticalPanel();

		verticalPanel6.setSpacing(5);

		messageTextBox6 = new TextBox();
		verticalPanel6.add(messageTextBox5);
		messageTextBox6.setText(
				"Изменение состава летных бригад заблокировано. Нажмите кнопку с должностью для разблокирования.");
		messageTextBox6.setWidth("700px");
		messageTextBox6.setEnabled(false);

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

		verticalPanel6.add(cellTable6);

		// Add a ButtonCell as column to the CellTable
		Column<Staff, String> updateButton6 = new Column<Staff, String>(new ButtonCell()) {
			@Override
			public String getValue(Staff object) {
				return object.getPosition();
			}
		};
		cellTable6.addColumn(updateButton6, "Изменить состав летной бригады");

		buttonDeleteCrew = new Button("Delete crew");
		buttonDeleteCrew.setText("Удалить летную бригаду");
		buttonDeleteCrew.addClickHandler(new ClickHandlerImplementationDeleteCrew());
		verticalPanel6.add(buttonDeleteCrew);

		buttonCreateCrew = new Button("Create crew");
		buttonCreateCrew.setText("Создать летную бригаду");
		verticalPanel6.add(buttonCreateCrew);
		buttonCreateCrew.addClickHandler(new ClickHandlerImplementationCreateCrew());

		buttonRefreshCrew = new Button("Refresh crew");
		buttonRefreshCrew.setText("Обновить летную бригаду");
		verticalPanel6.add(buttonRefreshCrew);
		buttonRefreshCrew.addClickHandler(new ClickHandlerImplementationRefreshCrew());

		// Field Update staff in crew in the next tab
		// FieldUpdaterImplementation
		updateButton6.setFieldUpdater(new FieldUpdaterImplementationUpdateCrew());

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

		ExtendedPager pagerCellTable7 = new ExtendedPager();
		pagerCellTable7.setDisplay(cellTable7);
		
		// Update staff in selected crew
		// FieldUpdaterImplementation
		updateButton7.setFieldUpdater(new FieldUpdaterImplementationUpdateCrewStaff());

		verticalPanel7.add(cellTable7);
		verticalPanel7.add(pagerCellTable7);

		tabLayoutPanel.add(verticalPanel7, "Летная бригада");

		// ----------------------------------------------------------------------------

		VerticalPanel verticalPanel8 = new VerticalPanel();

		// Create a CellTable.
		final CellTable<Fleet> cellTable8 = new CellTable<Fleet>();
		// Display 10 rows in one page
		cellTable8.setPageSize(10);

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

		provider8 = ListDataProviderExtension8.getInstance();

		// add provider to cellTable
		provider8.addDataDisplay(cellTable8);

		// SimplePager pagerCellTable8 = new SimplePager(TextLocation.CENTER,
		// false, false);
		ExtendedPager pagerCellTable8 = new ExtendedPager();
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

		regHandler1 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementationBlockAllTab());
		regHandler2 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementationBlock6Tab());
		regHandler3 = tabLayoutPanel.addBeforeSelectionHandler(new BeforeSelectionHandlerImplementationBlock7Tab());
	}

}
