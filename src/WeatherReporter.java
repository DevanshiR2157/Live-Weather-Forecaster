import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;


public class WeatherReporter {
	private WeatherBureau bureau;
	protected JFrame WeatherObserver;
	/**
	 * @wbp.nonvisual location=95,8
	 */
	private final JComboBox comboBox = new JComboBox();
	private JList stationList;
	private DefaultListModel listModel;
	private JPanel panel;
	private JLabel lblWeatherText;
	private JLabel lblWeatherData;
	private JLabel lblTempText;
	private JLabel lblTempData;
	private JLabel lblStationName;
	private JLabel lblIcon;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	/**
	 * @wbp.nonvisual location=256,28
	 */
	private final JButton button = new JButton("New button");
	private JLabel lblWindConditionsText;
	private JLabel lblWindText;
	private JLabel lblBarometricPressure;
	private JLabel lblWindData;
	private JLabel lblWindConditionsData;
	private JLabel lblPressureData;
	private JLabel lblHumidity;
	private JLabel lblHumidityData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherReporter window = new WeatherReporter();
					window.WeatherObserver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WeatherReporter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Initialize our WeatherBureau object
		bureau = new WeatherBureau();
		WeatherObserver = new JFrame();
		WeatherObserver.setTitle("Data Lab - National Weather Service");
		WeatherObserver.setBounds(100, 100, 1280, 531);
		WeatherObserver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_1 = new JPanel();
		WeatherObserver.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Pick a State");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JComboBox statesCombo = new JComboBox();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(statesCombo, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(849))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(statesCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel_1.setLayout(gl_panel_1);
		
					// Add listener for the list of states
					statesCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//updateWeatherData((String)statesCombo.getSelectedItem());
							updateStations((String)statesCombo.getSelectedItem());
		
						}
					});
		
		// Initialize our List box witha  default list model
		listModel = new DefaultListModel();
		stationList = new JList(listModel);
		stationList.setToolTipText("Select the weather station for which you want to see the most recent observation.");
		stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Add a scroll pane, so that our list box can be scrolled.
		 JScrollPane scrollPane = new JScrollPane(stationList);
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 // Add a Panel to display the observation for a selected station.
			WeatherObserver.getContentPane().add(scrollPane, BorderLayout.WEST);
			
			panel = new JPanel();
			panel.setBackground(SystemColor.textHighlight);
			WeatherObserver.getContentPane().add(panel, BorderLayout.CENTER);
			
			lblTempText = new JLabel("Temperature");
			lblTempText.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblTempText.setVerticalAlignment(SwingConstants.TOP);
			lblTempText.setHorizontalAlignment(SwingConstants.LEFT);
			
			lblWeatherText = new JLabel("Weather");
			lblWeatherText.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblWeatherText.setVerticalAlignment(SwingConstants.TOP);
			lblWeatherText.setHorizontalAlignment(SwingConstants.LEFT);
			
			lblWeatherData = new JLabel("                      ");
			lblWeatherText.setLabelFor(lblWeatherData);
			lblWeatherData.setForeground(SystemColor.text);
			lblWeatherData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblWeatherData.setVerticalAlignment(SwingConstants.TOP);
			lblWeatherData.setHorizontalAlignment(SwingConstants.LEFT);
			
			lblTempData = new JLabel("               ");
			lblTempText.setLabelFor(lblTempData);
			lblTempData.setForeground(SystemColor.text);
			lblTempData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblTempData.setVerticalAlignment(SwingConstants.TOP);
			lblTempData.setHorizontalAlignment(SwingConstants.LEFT);
			lblTempData.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
				}
			});
			
			lblStationName = new JLabel("");
			lblStationName.setForeground(SystemColor.info);
			lblStationName.setHorizontalAlignment(SwingConstants.CENTER);
			lblStationName.setFont(new Font("Tahoma", Font.PLAIN, 28));
			
			lblIcon = new JLabel("");
			lblIcon.setVerticalAlignment(SwingConstants.TOP);
			lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblWindConditionsText = new JLabel("Wind Conditions");
			lblWindConditionsText.setVerticalAlignment(SwingConstants.TOP);
			lblWindConditionsText.setHorizontalAlignment(SwingConstants.LEFT);
			lblWindConditionsText.setFont(new Font("Tahoma", Font.BOLD, 21));
			
			lblWindText = new JLabel("Wind");
			lblWindText.setVerticalAlignment(SwingConstants.TOP);
			lblWindText.setHorizontalAlignment(SwingConstants.LEFT);
			lblWindText.setFont(new Font("Tahoma", Font.BOLD, 21));
			
			lblBarometricPressure = new JLabel("Barometric Pressure");
			lblBarometricPressure.setLabelFor(lblBarometricPressure);
			lblBarometricPressure.setVerticalAlignment(SwingConstants.TOP);
			lblBarometricPressure.setHorizontalAlignment(SwingConstants.LEFT);
			lblBarometricPressure.setFont(new Font("Tahoma", Font.BOLD, 21));
			
			lblWindData = new JLabel("                     ");
			lblWindText.setLabelFor(lblWindData);
			lblWindData.setForeground(SystemColor.text);
			lblWindData.setVerticalAlignment(SwingConstants.TOP);
			lblWindData.setHorizontalAlignment(SwingConstants.LEFT);
			lblWindData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			
			lblWindConditionsData = new JLabel("        ");
			lblWindConditionsText.setLabelFor(lblWindConditionsData);
			lblWindConditionsData.setForeground(SystemColor.text);
			lblWindConditionsData.setVerticalAlignment(SwingConstants.TOP);
			lblWindConditionsData.setHorizontalAlignment(SwingConstants.LEFT);
			lblWindConditionsData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			
			lblPressureData = new JLabel("                  ");
			lblPressureData.setForeground(SystemColor.text);
			lblPressureData.setHorizontalAlignment(SwingConstants.LEFT);
			lblPressureData.setVerticalAlignment(SwingConstants.TOP);
			lblPressureData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			
			lblHumidity = new JLabel("Humidity");
			lblHumidity.setVerticalAlignment(SwingConstants.TOP);
			lblHumidity.setHorizontalAlignment(SwingConstants.LEFT);
			lblHumidity.setFont(new Font("Tahoma", Font.BOLD, 21));
			
			lblHumidityData = new JLabel("                    ");
			lblHumidity.setLabelFor(lblHumidityData);
			lblHumidityData.setForeground(SystemColor.text);
			lblHumidityData.setHorizontalAlignment(SwingConstants.LEFT);
			lblHumidityData.setVerticalAlignment(SwingConstants.TOP);
			lblHumidityData.setFont(new Font("Tahoma", Font.PLAIN, 21));
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblStationName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblWindText)
									.addComponent(lblBarometricPressure)
									.addComponent(lblHumidity)
									.addComponent(lblTempText, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblWeatherText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblWindConditionsText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(2)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblHumidityData)
									.addComponent(lblWindConditionsData, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblWindData, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPressureData, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTempData, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblWeatherData, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
								.addGap(184))
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addGap(208)
								.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(10, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(lblStationName, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblIcon, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addGap(3)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblWeatherText, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
							.addComponent(lblWeatherData, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTempText, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTempData))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblWindConditionsText)
							.addComponent(lblWindConditionsData))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblWindText)
							.addComponent(lblWindData))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBarometricPressure)
							.addComponent(lblPressureData))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblHumidity)
							.addComponent(lblHumidityData))
						.addGap(197))
			);
			panel.setLayout(gl_panel);

			// populate the list of states
			ArrayList<String> states = bureau.getStatesWithStations();
			for (String state: states) {
				statesCombo.addItem(state);
				if (state.toUpperCase().equals("WA")){
					statesCombo.setSelectedItem(state);	
				}
			}

		 //Add listener for the list of stations
		stationList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try {
				// Get the station ID
				String selectedValue  = (String)stationList.getSelectedValue();
				// Get the index of the "--"
				int endOfStationPos = selectedValue.indexOf("--");
				String station = selectedValue.substring(0,endOfStationPos);
				updateWeather(station);
				}
				catch (Exception e) {
					// eat the exception.
				}
			}
		});
		
	}
	
	private void updateStations(String state) {
		WeatherStation[] stations = bureau.getStationsInStateSortedByName(state);
		//stationList.removeAll();
		listModel.removeAllElements();
		for (WeatherStation station : stations) {
			// If this gets updated, update stationList.addListSelectionListener
			listModel.addElement(station.getId() + "--" + station.getName());
		}
		stationList.setModel(listModel);
	}
	private void updateWeather(String station) {
		// Get the Weather Station for which the user has selected
		WeatherStation wb = bureau.getStation(station);
		lblStationName.setText(wb.getName() + " (" + station + ")");
		
		// Attempt to get the current observation for the weather station
		try {
			Observation ob = wb.getCurrentWeather();
			// update our UI with the current observation.
			URL iconURL = new URL(ob.getIconURL());
			BufferedImage image = ImageIO.read(iconURL.openStream());
			lblIcon.setIcon(new ImageIcon(image));
			lblWeatherData.setText(ob.getDescription());
			lblTempData.setText(ob.getTemp() + " F");
			lblPressureData.setText(ob.getPressure() + " mb");
			lblHumidityData.setText(ob.getHumidity() + " %");
			lblWindConditionsData.setText(ob.getWindConditions() + " (" + ob.getBeaufortNumber() + ")");
			lblWindData.setText(ob.getWindDir() + " degrees at " + ob.getWindSpeed() + " knots");
		}
		catch(Exception e) {
			String na = "Not Available";
			lblWeatherData.setText(na);
			lblTempData.setText(na);
			lblIcon.setIcon(null);
			lblPressureData.setText(na);
			lblHumidityData.setText(na);
			lblWindConditionsData.setText(na);
			lblWindData.setText(na);
		
		}
		
		
		
	}
	private void updateWeatherData(String state, String station) {
		// get the stations for the selected state

		WeatherStation[] stations = bureau.getStationsInStateSortedByName(state);

		// get the current set of columns
		String[] columns = {"Station ID","Description", "Temp",  "Wind"};

		// initialize array to hold the data for the table
		Object[][] data = new Object[1][columns.length];
		// traverse the stations 
		for (int row = 0; row < stations.length; row++) {
			// 				"Station ID","Description", "Temp",  "Wind",
			if (stations[row].getId().equals(station)) {
				try {
					Observation ob = stations[row].getCurrentWeather();
					data[row][0] = ob.getId();
					data[row][1] = ob.getDescription();
					data[row][2] = ob.getTemp();
					data[row][3] = ob.getWindConditions();
				}
				catch(Exception e){
					data[row][0] = "";
					data[row][1] = "";
					data[row][2] = "";
					data[row][3] = "";
				}
			}
			else {
				data[row][0] = "";
				data[row][1] = "";
				data[row][2] = "";
				data[row][3] = "";
			
			}
		}
		//observationsTable.setModel(new DefaultTableModel(data,columns));
	}
}


