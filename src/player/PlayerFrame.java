package player;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PlayerFrame extends JFrame{
	static JLabel name;
	static JButton play;
	static JButton loop;
	static JButton stop;
	static JProgressBar progress;
	playerController pc = new playerController();
	PlayerFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		setResizable(false);
		setSize(400,230);
		setLayout(new FlowLayout());
		 name = new JLabel("����");	
		 name.setFont(new java.awt.Font("����", 0, 20));
		JPanel bots = new JPanel();
		name.setPreferredSize(new Dimension(300,60));
		bots.setPreferredSize(new Dimension(400,50));
		loop = new JButton("ѭ��");
		loop.addActionListener(pc);
		loop.setPreferredSize(new Dimension(100,30));
		play = new JButton("����");
		play.addActionListener(pc);
		play.setPreferredSize(new Dimension(100,30));
		stop = new JButton("ֹͣ");
		stop.addActionListener(pc);
		stop.setPreferredSize(new Dimension(100,30));
		JPanel progressPanel;   //����������
		progress=new JProgressBar(0,145);
		
		System.out.println("bar,s len:"+progress.getMaximum());
		progressPanel=new JPanel(); //ʵ��������������  
		progressPanel.add(progress);
		progress.addMouseListener(new MouseAdapter() {
			 @Override  
	            public void mouseClicked(MouseEvent e){     //����������������ֲ��Ž���  
	                int x=e.getX();  
	                progress.setValue(x);
	                System.out.println(x+"%");
	                PlayerModel.jumpto((float) (x/145.0), PlayerModel.pl);
	            }  
		});
		progress.setStringPainted(true);
		bots.add(loop);
		bots.add(play);
		bots.add(stop);
		setVisible(true);
		JMenu fileMenu = new JMenu("�ļ�");
		//openMemuItem.addActionListener(this);
		JMenuBar menuBar = new JMenuBar();
		JMenuItem openMemuItem = new JMenuItem("��");
		openMemuItem.addActionListener(pc);
		fileMenu.add(openMemuItem);
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
		fileMenu.addActionListener(pc);
		this.add(name);
		this.add(bots);
		this.add(progressPanel);  
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.updateComponentTreeUI(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void JFClose(){
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		new PlayerFrame();
	}
}
