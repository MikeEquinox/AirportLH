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
 * ListDataProviderExtension5A shows Timetable on monitor screen
 * greetingService.getTimetableDetailsAll(new AsyncCallback<List
 * <Timetable>>()
 */
final class ListDataProviderExtension5A extends ListDataProvider<Timetable> {
	
	private static ListDataProviderExtension5A instance;
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	final Logger clientLogger = Logger.getLogger("clientLogger");
	private int end;

	private ListDataProviderExtension5A() {
	}

	public static ListDataProviderExtension5A getInstance() {
	
		instance = new ListDataProviderExtension5A();
		return instance;
	}
	
	@Override
	protected void onRangeChanged(HasData<Timetable> display) {

		final int start;
		start = display.getVisibleRange().getStart();
		end = start + display.getVisibleRange().getLength();
		
		greetingService.getTimetableDetailsAll(new AsyncCallback<List<Timetable>>() {
			@Override
			public void onFailure(Throwable caught) {
				clientLogger.log(Level.SEVERE,
						"greetingService.getTimetableDetailsAll(), AsyncCallback failure. " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Timetable> timetableList) {

				instance.setList(timetableList);
				end = end >= timetableList.size() ? timetableList.size() : end;
				List<Timetable> sub = timetableList.subList(start, end);
				updateRowData(start, sub);
				clientLogger.log(Level.INFO, "ListDataProviderExtension5A shows Timetable on monitor screen");
				clientLogger.log(Level.INFO, "greetingService.getTimetableDetailsAll(), AsyncCallback succeded.");

			}
		});
	}
}
