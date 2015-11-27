package airportpackage.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import airportpackage.shared.Timetable;

/**
 * ListDataProviderExtension5 shows Timetable on monitor screen
 * greetingService.getTimetableDetails(new AsyncCallback<List<Timetable>>()
 */
final class ListDataProviderExtension5 extends ListDataProvider<Timetable> {
	
	private static ListDataProviderExtension5 instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;

	private ListDataProviderExtension5() {
	}

	public static ListDataProviderExtension5 getInstance() {
	
		instance = new ListDataProviderExtension5();
		return instance;
	}
	
	@Override
	protected void onRangeChanged(HasData<Timetable> display) {

		final int start;
		start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();	
		
		greetingService.getTimetableDetails(new AsyncCallback<List<Timetable>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getTimetableDetails(), AsyncCallback failure. " + caught.getMessage());
				// Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Timetable> timetableList) {

				instance.setList(timetableList);
				end = end >= timetableList.size() ? timetableList.size() : end;
				List<Timetable> sub = timetableList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension5 shows Timetable on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getTimetableDetails(), AsyncCallback succeded.");

			}
		});
	}
}