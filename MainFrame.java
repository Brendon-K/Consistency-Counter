import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
//import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	private final JButton good;
	private final JButton bad;
	private final JButton reset;

	private final JLabel output;

	private int successes;
	private int total;

	public MainFrame() {
		super("Consistency Counter");
		setLayout(new FlowLayout(FlowLayout.CENTER));

		successes = 0;
		total = 0;

		good = new JButton("Success");
		good.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successes++;
				total++;
				update();
			}
		});	

		bad = new JButton("Fail");
		bad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				total++;
				update();
			}
		});

		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successes = 0;
				total = 0;
				update();
			}
		});

		output = new JLabel("0 / 0 (0%)");
		update();

		JPanel topPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		topPanel.add(good);
		topPanel.add(bad);

		JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		bottomPanel.add(reset);

		JPanel gridContainer = new JPanel(new GridLayout(3, 1, 5, 5));

		gridContainer.add(topPanel);
		gridContainer.add(output);
		gridContainer.add(bottomPanel);

		add(gridContainer);

		//JPanel flowContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//flowContainer.add(gridContainer);

		//JPanel borderPanel = new JPanel(new BorderLayout(50, 50));
		//borderPanel.add(topPanel, BorderLayout.NORTH);
		//borderPanel.add(output, BorderLayout.CENTER);
		//borderPanel.add(bottomPanel, BorderLayout.SOUTH);

		//add(borderPanel);
	}

	private void update() {
		double percentage;

		if (total != 0) {
			percentage = 1.0*successes / total * 100;
		} else {
			percentage = 0;
		}
		output.setText(String.format("%d / %d (%.2f%%)", successes, total, percentage));
	}
}