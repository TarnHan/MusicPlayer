package player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.Player;

public class playerController implements ActionListener{
	static ActionEvent e;
	Player pl;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.e = e;
		flagchanger fl = new flagchanger();
		fl.start();
		PlayerModel ts = new PlayerModel();
		if(e.getActionCommand()=="��"){
			PlayerFrame.play.setText("����");	
			pl = ts.LoadMusic();}
		if(e.getActionCommand()=="����"){
			PlayerFrame.play.setText("��ͣ");	
			ts.playMusic(pl);
		}	
		if(e.getActionCommand()=="��ͣ"){
			PlayerFrame.play.setText("����");
			ts.pause(pl);
		}
		if(e.getActionCommand()=="ֹͣ"){
			ts.stop(pl);
			PlayerFrame.play.setText("����");
		}	
		}
	}
class flagchanger extends Thread{
	public void run(){
		if(playerController.e.getActionCommand()=="ѭ��"){
			PlayerModel.loopflag = true;	
			PlayerFrame.loop.setText("ֹͣѭ��");
			System.out.println("successfully looped");
						}	
		if(playerController.e.getActionCommand()=="ֹͣѭ��"){
			PlayerModel.loopflag = false;
			PlayerFrame.loop.setText("ѭ��");
			System.out.println("loop canceled");
		}
	}
}
	


