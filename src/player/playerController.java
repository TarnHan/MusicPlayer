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
		if(e.getActionCommand()=="´ò¿ª"){
			PlayerFrame.play.setText("²¥·Å");	
			pl = ts.LoadMusic();}
		if(e.getActionCommand()=="²¥·Å"){
			PlayerFrame.play.setText("ÔÝÍ£");	
			ts.playMusic(pl);
		}	
		if(e.getActionCommand()=="ÔÝÍ£"){
			PlayerFrame.play.setText("²¥·Å");
			ts.pause(pl);
		}
		if(e.getActionCommand()=="Í£Ö¹"){
			ts.stop(pl);
			PlayerFrame.play.setText("²¥·Å");
		}	
		}
	}
class flagchanger extends Thread{
	public void run(){
		if(playerController.e.getActionCommand()=="Ñ­»·"){
			PlayerModel.loopflag = true;	
			PlayerFrame.loop.setText("Í£Ö¹Ñ­»·");
			System.out.println("successfully looped");
						}	
		if(playerController.e.getActionCommand()=="Í£Ö¹Ñ­»·"){
			PlayerModel.loopflag = false;
			PlayerFrame.loop.setText("Ñ­»·");
			System.out.println("loop canceled");
		}
	}
}
	


