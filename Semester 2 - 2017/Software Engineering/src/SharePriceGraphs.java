import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.util.*;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.chart.axis.*;
import org.jfree.chart.panel.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;
import org.jfree.data.xy.*;
import org.jfree.ui.*;

import org.json.*;

@SuppressWarnings("serial")
public class SharePriceGraphs extends JFrame implements ChartMouseListener {
	ChartPanel chartPanel;

	Crosshair domainCrosshair;
	Crosshair rangeCrosshair;

	ArrayList<Double> Open = new ArrayList<Double>();
	ArrayList<Double> Close = new ArrayList<Double>();
	ArrayList<String> Time = new ArrayList<String>();

	JFreeChart chart;
	static JFrame frame;
	String StockSymbol;

	public void newGraph() {
		frame = new JFrame("Share Market Price Information");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new NewGraphWindow());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public final JFrame getFrame() {
		return frame;
	}

	public class NewGraphWindow extends JPanel {

		JPanel reportingPane;
		XYPlot plot;

		public NewGraphWindow() {
			setLayout(new BorderLayout());
			reportingPane = new JPanel(new BorderLayout());
			// Button 1
			JButton btnReporting = new JButton("Daily");
			btnReporting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							createDailyGraph();
						}
					}.start();
				}
			});

			JButton btnReporting3 = new JButton("Monthly");
			btnReporting3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {

							createMonthlyGraph();
						}
					}.start();
				}
			});

			JButton btnReporting2 = new JButton("Hourly");
			btnReporting2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							createHourlyGraph();
						}
					}.start();
				}
			});

			JButton btnReporting4 = new JButton("Yearly");
			btnReporting4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							createYearlyGraph();
						}
					}.start();
				}
			});

			// this button is to create space
			JButton btnNewButton0 = new JButton("1");
			btnNewButton0.setVisible(false);

			JButton btnNewButton1 = new JButton("AAPL");
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("AAPL");
							createHourlyGraph();
						}
					}.start();
				}
			});
			JButton btnNewButton2 = new JButton("TSLA");
			btnNewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("TSLA");
							createHourlyGraph();
						}
					}.start();
				}
			});
			JButton btnNewButton3 = new JButton("MSFT");
			btnNewButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("MSFT");
							createHourlyGraph();
						}
					}.start();
				}
			});
			JButton btnNewButton4 = new JButton("AMZN");
			btnNewButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("AMZN");
							createHourlyGraph();
						}
					}.start();
				}
			});
			JButton btnNewButton5 = new JButton("NFLX");
			btnNewButton5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("NFLX");
							createHourlyGraph();
						}
					}.start();
				}
			});
			JButton btnNewButton6 = new JButton("AMD");
			btnNewButton6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new Thread() {
						public void run() {
							setStocksymbol("AMD");
							createHourlyGraph();
						}
					}.start();
				}
			});

			JPanel ButtonContainer0 = new JPanel();
			JPanel ButtonContainer1 = new JPanel();
			JPanel ButtonContainer2 = new JPanel();
			JPanel ButtonContainer3 = new JPanel();
			JPanel ButtonContainer4 = new JPanel();
			JPanel ButtonContainer5 = new JPanel();
			JPanel ButtonContainer6 = new JPanel();

			JPanel containerPanel = new JPanel(new GridLayout(7, 1));
			ButtonContainer0.add(btnNewButton0);
			ButtonContainer1.add(btnNewButton1);
			ButtonContainer2.add(btnNewButton2);
			ButtonContainer3.add(btnNewButton3);
			ButtonContainer4.add(btnNewButton4);
			ButtonContainer5.add(btnNewButton5);
			ButtonContainer6.add(btnNewButton6);

			containerPanel.add(ButtonContainer0);
			containerPanel.add(ButtonContainer1);
			containerPanel.add(ButtonContainer2);
			containerPanel.add(ButtonContainer3);
			containerPanel.add(ButtonContainer4);
			containerPanel.add(ButtonContainer5);
			containerPanel.add(ButtonContainer6);

			add(containerPanel, BorderLayout.WEST);

			add(reportingPane);
			JPanel ButtonContainer = new JPanel();
			ButtonContainer.add(btnReporting2);
			ButtonContainer.add(btnReporting);
			ButtonContainer.add(btnReporting3);
			ButtonContainer.add(btnReporting4);
			add(ButtonContainer, BorderLayout.SOUTH);
		}

		protected void createHourlyGraph() {
			try {
				chart = dailyGraph(getStocksymbol(), "1min", "Time Series (1min)", "HOUR");
			} catch (IOException | ParseException | JSONException e) {
				e.printStackTrace();
			}

			reportingPane.removeAll();
			reportingPane.add(createContent());

			plot = (XYPlot) chart.getPlot();

			ValueAxis yAxis = plot.getRangeAxis();
			ValueAxis xAxis = plot.getDomainAxis();
			xAxis.setVerticalTickLabels(true);

			DateAxis axis = (DateAxis) plot.getDomainAxis();

			yAxis.setRange(Collections.min(Close) - 0.01, Collections.max(Close) + 0.01);

			axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));

			((DateAxis) plot.getDomainAxis()).setTimeline(tenamto4pmTimeline());

			plot.getRenderer().setSeriesStroke(0, new BasicStroke((float) 6.0));

			revalidate();
			repaint();
		}

		public void createMonthlyGraph() {
			try {
				chart = monthGraph(getStocksymbol(), "60min", "Time Series (60min)", "MONTH");
			} catch (IOException | ParseException | JSONException e) {
				e.printStackTrace();
			}

			reportingPane.removeAll();
			reportingPane.add(createContent());

			plot = (XYPlot) chart.getPlot();

			ValueAxis yAxis = plot.getRangeAxis();
			ValueAxis xAxis = plot.getDomainAxis();
			xAxis.setVerticalTickLabels(true);

			DateAxis xAxisDate = (DateAxis) plot.getDomainAxis();

			yAxis.setAutoRange(true);

			xAxisDate.setDateFormatOverride(new SimpleDateFormat("yyyy/MM/dd"));

			plot.getRenderer().setSeriesStroke(0, new BasicStroke((float) 2.0));
			revalidate();
			repaint();
		}

		public void createDailyGraph() {

			try {
				chart = hourGraph(getStocksymbol(), "5min", "Time Series (5min)", "DAY");
			} catch (IOException | ParseException | JSONException e) {

				e.printStackTrace();
			}

			reportingPane.removeAll();
			reportingPane.add(createContent());

			plot = (XYPlot) chart.getPlot();

			ValueAxis yAxis = plot.getRangeAxis();
			ValueAxis xAxis = plot.getDomainAxis();
			xAxis.setVerticalTickLabels(true);

			DateAxis xAxisDate = (DateAxis) plot.getDomainAxis();
			xAxisDate.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
			yAxis.setRange(Collections.min(Close) - 0.05, Collections.max(Close) + 0.05);

			((DateAxis) plot.getDomainAxis()).setTimeline(tenamto4pmTimeline());

			plot.getRenderer().setSeriesStroke(0, new BasicStroke((float) 2.0));
			revalidate();
			repaint();
		}

		public void createYearlyGraph() {

			try {
				chart = yearGraph(getStocksymbol(), "YEAR");
			} catch (IOException | ParseException | JSONException e) {

				e.printStackTrace();
			}

			reportingPane.removeAll();
			reportingPane.add(createContent());

			plot = (XYPlot) chart.getPlot();

			ValueAxis yAxis = plot.getRangeAxis();
			ValueAxis xAxis = plot.getDomainAxis();
			xAxis.setVerticalTickLabels(true);

			DateAxis xAxisDate = (DateAxis) plot.getDomainAxis();
			xAxisDate.setDateFormatOverride(new SimpleDateFormat("yyyy/MM"));
			yAxis.setAutoRange(true);

			((DateAxis) plot.getDomainAxis()).setTimeline(tenamto4pmTimeline());

			plot.getRenderer().setSeriesStroke(0, new BasicStroke((float) 2.0));
			revalidate();
			repaint();
		}

		public Dimension getPreferredSize() {
			return new Dimension(560, 370);
		}
	}

	public String getStocksymbol() {
		return StockSymbol;

	}

	public void setStocksymbol(String input) {
		this.StockSymbol = input;
	}

	// The next two methods were made using the following reference;
	// https://stackoverflow.com/questions/21172794/jfreechart-dispay-mouse-coordinates-near-to-mouse-as-hints-on-mouse-move/21180275#21180275
	private JPanel createContent() {

		chartPanel = new ChartPanel(chart);
		chartPanel.addChartMouseListener(this);

		rangeCrosshair = new Crosshair(Double.NaN, Color.BLUE, new BasicStroke(0.5f));
		domainCrosshair = new Crosshair(Double.NaN, Color.BLUE, new BasicStroke(0.5f));
		rangeCrosshair.setLabelVisible(true);
		domainCrosshair.setLabelVisible(false);

		CrosshairOverlay stockMarketCrosshair = new CrosshairOverlay();

		stockMarketCrosshair.addDomainCrosshair(domainCrosshair);
		stockMarketCrosshair.addRangeCrosshair(rangeCrosshair);
		chartPanel.addOverlay(stockMarketCrosshair);

		return chartPanel;
	}

	public void chartMouseMoved(ChartMouseEvent event) {

		XYPlot plot = (XYPlot) event.getChart().getPlot();
		Rectangle2D dataArea = chartPanel.getScreenDataArea();

		double x = plot.getDomainAxis().java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
		double y = DatasetUtilities.findYValue(plot.getDataset(), 0, x);

		domainCrosshair.setValue(Math.round(x * 100.0) / 100.0);
		rangeCrosshair.setValue(Math.round(y * 100.0) / 100.0);
	}

	// Sourced from
	// https://stackoverflow.com/questions/45919485/how-to-remove-gaps-between-a-time-period-of-jfreechart/45935829#45935829
	// i have edited as much as possible!
	public static SegmentedTimeline tenamto4pmTimeline() {
		// This means that 6 segments are included aka 10am to 4pm and the rest
		// arent shown.
		SegmentedTimeline timeline = new SegmentedTimeline(SegmentedTimeline.HOUR_SEGMENT_SIZE, 7, 17);
		// +10 means it starts at 10am each morning.
		timeline.setStartTime(SegmentedTimeline.firstMondayAfter1900() + 9 * timeline.getSegmentSize());
		timeline.setBaseTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
		return timeline;
	}

	public JFreeChart hourGraph(String inputStockSymbol, String interval, String timeInterval, String periodOfTime)
			throws JSONException, IOException, ParseException {

		if (inputStockSymbol == null) {
			inputStockSymbol = "AAPL";
		}

		TimeSeries series = new TimeSeries(inputStockSymbol + " Stock Market Information");

		Close.clear();
		Open.clear();
		Time.clear();

		URL website2 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
				+ inputStockSymbol + "&interval=" + interval + "&outputsize=compact&apikey=MGG7HPG0H2MKVNO1");

		JSONTokener StockJSONTokener = new JSONTokener(website2.openStream());
		JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

		JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject.get(timeInterval);

		ArrayList<String> timeOfStockUnordered = new ArrayList<String>();

		Iterator<String> stockTimeKeys = stockTimeIntervalObject.keys();
		while (stockTimeKeys.hasNext()) {
			String key = stockTimeKeys.next();
			timeOfStockUnordered.add(key);
		}
		// Sorts it in order because .keys() is random and then reverses it so
		// it creates
		// graph starting at the oldest date.
		Collections.sort(timeOfStockUnordered);
		Collections.reverse(timeOfStockUnordered);

		for (int i = 0; i < timeOfStockUnordered.size(); i++) {

			JSONObject name3 = (JSONObject) stockTimeIntervalObject.get(timeOfStockUnordered.get(i));

			String OpenDouble = (String) name3.get("1. open");
			String CloseDouble = (String) name3.get("4. close");

			Time.add(timeOfStockUnordered.get(i));
			Open.add(Double.parseDouble(OpenDouble));
			Close.add(Double.parseDouble(CloseDouble));
		}

		int timeCounter = 0;
		DateFormat StockDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 73 = number of intervals for 1 day on the graph.
		for (int i = 0; i < 73; i++) {
			// Makes sure first position is the open price rather than closing
			// price.
			if (i == 0) {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Open.get(timeCounter) * 100.0) / 100.0);
				timeCounter++;
			} else {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Close.get(timeCounter) * 100.0) / 100.0);

				timeCounter++;
			}
		}
		// Add the series to your data set
		XYDataset StockMarketDate = (XYDataset) new TimeSeriesCollection(series);

		// Generate the graph
		JFreeChart StockMarketChart = ChartFactory.createTimeSeriesChart(
				inputStockSymbol + " Stock Market Information" + " (" + periodOfTime + ")", "Time", "Share Price",
				StockMarketDate, false, true, false);

		return StockMarketChart;

	}

	public JFreeChart dailyGraph(String inputStockSymbol, String interval, String timeInterval, String periodOfTime)
			throws JSONException, IOException, ParseException {

		if (inputStockSymbol == null) {
			inputStockSymbol = "AAPL";
		}

		TimeSeries series = new TimeSeries(inputStockSymbol + " Stock Market Information");

		Close.clear();
		Open.clear();
		Time.clear();

		URL website2 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
				+ inputStockSymbol + "&interval=" + interval + "&outputsize=compact&apikey=MGG7HPG0H2MKVNO1");

		JSONTokener StockJSONTokener = new JSONTokener(website2.openStream());
		JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

		JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject.get(timeInterval);

		ArrayList<String> timeOfStockUnordered = new ArrayList<String>();

		Iterator<String> stockTimeKeys = stockTimeIntervalObject.keys();
		while (stockTimeKeys.hasNext()) {
			String key = stockTimeKeys.next();
			timeOfStockUnordered.add(key);
		}
		// Sorts it in order because .keys() is random and then reverses it so
		// it creates
		// graph starting at the oldest date.
		Collections.sort(timeOfStockUnordered);
		Collections.reverse(timeOfStockUnordered);

		for (int i = 0; i < timeOfStockUnordered.size(); i++) {

			JSONObject name3 = (JSONObject) stockTimeIntervalObject.get(timeOfStockUnordered.get(i));

			String OpenDouble = (String) name3.get("1. open");
			String CloseDouble = (String) name3.get("4. close");

			Time.add(timeOfStockUnordered.get(i));
			Open.add(Double.parseDouble(OpenDouble));
			Close.add(Double.parseDouble(CloseDouble));
		}

		int timeCounter = 0;
		DateFormat StockDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < 60; i++) {
			// Makes sure first position is the open price rather than closing
			// price.
			if (i == 0) {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Open.get(timeCounter) * 100.0) / 100.0);
				timeCounter++;
			} else {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Close.get(timeCounter) * 100.0) / 100.0);

				timeCounter++;
			}
		}
		// Add the series to your data set
		XYDataset StockMarketDate = (XYDataset) new TimeSeriesCollection(series);

		// Generate the graph
		JFreeChart StockMarketChart = ChartFactory.createTimeSeriesChart(
				inputStockSymbol + " Stock Market Information" + " (" + periodOfTime + ")", "Time", "Share Price",
				StockMarketDate, false, true, false);

		return StockMarketChart;

	}

	public JFreeChart yearGraph(String inputStockSymbol, String periodOfTime)
			throws IOException, ParseException, JSONException {
		if (inputStockSymbol == null) {
			inputStockSymbol = "AAPL";
		}

		TimeSeries series = new TimeSeries(inputStockSymbol + " Stock Market Information");

		Close.clear();
		Open.clear();
		Time.clear();

		URL website2 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="
				+ inputStockSymbol + "&apikey=MGG7HPG0H2MKVNO1");

		JSONTokener StockJSONTokener = new JSONTokener(website2.openStream());
		JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

		JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject.get("Weekly Time Series");

		ArrayList<String> timeOfStockUnordered = new ArrayList<String>();

		Iterator<String> stockTimeKeys = stockTimeIntervalObject.keys();
		while (stockTimeKeys.hasNext()) {
			String key = stockTimeKeys.next();
			timeOfStockUnordered.add(key);
		}
		// Sorts it in order because .keys() is random and then reverses it so
		// it creates
		// graph starting at the oldest date.
		Collections.sort(timeOfStockUnordered);
		Collections.reverse(timeOfStockUnordered);

		for (int i = 0; i < timeOfStockUnordered.size(); i++) {

			JSONObject name3 = (JSONObject) stockTimeIntervalObject.get(timeOfStockUnordered.get(i));

			String OpenDouble = (String) name3.get("1. open");
			String CloseDouble = (String) name3.get("4. close");

			String input = timeOfStockUnordered.get(i);
			String output = null;
			if (input.length() == 10) {
				// also changed this to 16 from 02
				output = input + " 16:00:00";
			} else {

				output = input;
			}

			Time.add(output);

			Open.add(Double.parseDouble(OpenDouble));
			Close.add(Double.parseDouble(CloseDouble));
		}

		int timeCounter = 0;
		DateFormat StockDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < 52; i++) {
			// Makes sure first position is the open price rather than closing
			// price.
			if (i == 0) {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Open.get(timeCounter) * 100.0) / 100.0);
				timeCounter++;
			} else {
				Date myDate = StockDateFormat.parse(Time.get(timeCounter));
				series.add(new Second(myDate), Math.round(Close.get(timeCounter) * 100.0) / 100.0);

				timeCounter++;
			}
		}
		// Add the series to your data set
		XYDataset StockMarketDate = (XYDataset) new TimeSeriesCollection(series);

		// Generate the graph
		JFreeChart StockMarketChart = ChartFactory.createTimeSeriesChart(
				inputStockSymbol + " Stock Market Information" + " (" + periodOfTime + ")", "Time", "Share Price",
				StockMarketDate, false, true, false);

		return StockMarketChart;

	}

	public JFreeChart monthGraph(String inputStockSymbol, String interval, String timeInterval, String periodOfTime)
			throws JSONException, IOException, ParseException {

		if (inputStockSymbol == null) {
			inputStockSymbol = "AAPL";
		}

		TimeSeries series = new TimeSeries(inputStockSymbol + " Stock Market Information");

		Close.clear();
		Open.clear();
		Time.clear();

		URL website2 = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="
				+ inputStockSymbol + "&interval=" + interval + "&outputsize=full&apikey=MGG7HPG0H2MKVNO1");

		JSONTokener StockJSONTokener = new JSONTokener(website2.openStream());
		JSONObject StockBaseJSONObject = new JSONObject(StockJSONTokener);

		JSONObject stockTimeIntervalObject = (JSONObject) StockBaseJSONObject.get(timeInterval);

		ArrayList<String> timeOfStockUnordered = new ArrayList<String>();

		Iterator<String> stockTimeKeys = stockTimeIntervalObject.keys();
		while (stockTimeKeys.hasNext()) {
			String key = stockTimeKeys.next();
			timeOfStockUnordered.add(key);
		}
		// Sorts it in order because .keys() is random and then reverses it so
		// it creates
		// graph starting at the oldest date.
		Collections.sort(timeOfStockUnordered);
		Collections.reverse(timeOfStockUnordered);

		for (int i = 0; i < timeOfStockUnordered.size(); i++) {

			JSONObject name3 = (JSONObject) stockTimeIntervalObject.get(timeOfStockUnordered.get(i));

			String OpenDouble = (String) name3.get("1. open");
			String CloseDouble = (String) name3.get("4. close");

			Time.add(timeOfStockUnordered.get(i));
			Open.add(Double.parseDouble(OpenDouble));
			Close.add(Double.parseDouble(CloseDouble));
		}
		int timeCounter = 0;
		DateFormat StockDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			for (int i = 0; i < 144; i++) {
				// Makes sure first position is the open price rather than
				// closing
				// price.
				if (i == 0) {
					Date myDate = StockDateFormat.parse(Time.get(timeCounter));
					series.add(new Second(myDate), Math.round(Open.get(timeCounter) * 100.0) / 100.0);
					timeCounter++;
				} else {
					Date myDate = StockDateFormat.parse(Time.get(timeCounter));
					series.add(new Second(myDate), Math.round(Close.get(timeCounter) * 100.0) / 100.0);

					timeCounter++;
				}
			}

		} catch (IndexOutOfBoundsException e) {
			// This is because the API sometimes returns a list which is smaller
			// than what it should be.
		}

		// Add the series to your data set
		XYDataset StockMarketDate = (XYDataset) new TimeSeriesCollection(series);

		// Generate the graph
		JFreeChart StockMarketChart = ChartFactory.createTimeSeriesChart(
				inputStockSymbol + " Stock Market Information" + " (" + periodOfTime + ")", "Time", "Share Price",
				StockMarketDate, false, true, false);

		return StockMarketChart;

	}

	@Override
	public void chartMouseClicked(ChartMouseEvent arg0) {
		// Ignore, auto generated method.
	}

}
