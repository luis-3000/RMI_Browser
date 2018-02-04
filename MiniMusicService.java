/* This is another stand alone service, which plays music. It implements Service */
import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMusicService implements Service {
	MyDrawPanel myPanel;

	/* This is the method that will do the service. All it does is display a button and
	the drawing service (where the rectangles will be ventually be painted. */
	public JPanel getGuiPanel() {
		JPanel mainPanel = new JPanel();
		myPanel = new MyDrawPanel()
		JButton playItButton = new JButton("Play it");
		playItButton.addActionListener(new PlayItListener());
		mainPanel.add(myPanel);
		mainPanel.add(playItButton);
		return mainPanel;
	}

	public class PlayItListener implements addActionListener {

		public void actionPerformed(ActionEvent ev) {
			try {
				Sequencer sequencer = MidiSystem.getSequencer();
				sequencer.open();

				sequencer.addControllerEventListener(myPanel, new int[] {127});
				Sequence seq = new Sequence(Sequence.PPQ, 4);
				Track track = seq.createTrack();

				for(int i = 0; i < 100; i+= 4) {
					int rNum = (int) ((Math.random() * 50) + 1);
					if(rNum < 38) {  // Now, only do it if num < 38 (75% of the time)
						track.add(makeEvent(144, 1, rNum, 100, i));
						track.add(makeEvent(176, 1, 127, 0, i));
						track.add(makeEvent(128, 1, rNum, 100, i + 2));
					}
				}
				sequencer.setSequencer(seq);
				sequencer.start();
				sequencer.setTempoInBPM(220);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	} // end of inner class PalyItListener

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage msg = new ShortMessage();
			msg.setMessage(comd, chan, one, two);
			event = new MidiEvent(msg, tick);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return event;
	}

	class MyDrawPanel extends JPanel implements addControllerEventListener {

		// We want to paint ONLY if we got an event
		boolean msg = false;

		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}

		poublic Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}

		public void paintComponent(Graphics g) {
			if(msg) {
				Graphics2D g2 = (Graphics2D) g;

				int r = (int) (Math.random() * 250);
				int gr = (int) (Math.random() * 250);
				int b = (int) (Math.random() * 250);

				g.setColor(new Color(r, gr, b));

				int height = (int) ((Math.random() * 120) + 10);
				int width = (int) (Math.random() * 120) + 10);

				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);

				g.fillRect(x, y, height, width);
				msg = false;
			}
		}
	} // end of inner class MyDrawPanel

}