package player;

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.Player;
import javax.media.Time;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.text.StyledEditorKit.BoldAction;

public class PlayerModel {
	String filename ;
	String path ;
	File file = new File(path+filename);
	URL url; 
	static boolean loadflag = false;
	static Player pl; 
	static double songlength;
	static boolean loopflag = false;
	static boolean playflag = false;
	musicProgress mp ;
	
	JFileChooser jfc;	
	//���ļ�������������
	public Player LoadMusic(){
		if(loadflag){
			pl.stop();
			pl.close();
			}
		
			try {
				try{
					jfc=new JFileChooser();  
					jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
					jfc.showDialog(new JLabel(), "ѡ��");  
	        
					File file=jfc.getSelectedFile();  
					path = file.getAbsolutePath();
					filename = file.getName();}
				catch(Exception e){
					return null;
				}
				
				if(file.isDirectory()){  
					System.out.println("�ļ���:"+file.getAbsolutePath());  
				}else if(file.isFile()){  
					System.out.println("�ļ�:"+file.getAbsolutePath());  
				}  
	       
				url = new URL("file:"+path);
				pl = javax.media.Manager.createRealizedPlayer(url);
				loadflag = true;
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("unable to play the file");
				e.printStackTrace();
			}
			songlength = pl.getDuration().getSeconds();
			PlayerFrame.name.setText(filename);
			System.out.println(loadflag);
			System.out.println("successfully loaded");
			return pl;
			}
		
		
	//���²��ż� ��������
	public void playMusic(Player pl){
		//do{
			playflag = true;
			System.out.println("flag"+playflag);
			pl.start();
			System.out.println("state:"+pl.getState());
			mp = new musicProgress();
			mp.start();
			}
	//	while(loopflag);
		
	//}
	//������ͣ�� ��ͣ��������
	public void pause(Player pl){
		
		System.out.println("stopstate:"+pl.getState());
		playflag = false;
		pl.stop();
		System.out.println("successfully paused");
	}
	//����ֹͣ�� ֹͣ�������� �ҽ���������
	public void stop(Player pl){
		playflag = false;
		pl.stop();
		pl.setMediaTime(new Time(0));
		System.out.println("successfully stopped");
	}
	public static void jumpto(float a,Player pl){
		System.out.println("pro:"+a);
		pl.setMediaTime(new Time(pl.getDuration().getSeconds()*a));
	}
	//����ʱ���������еķ��� ����ʱʹ��
	public static void probar(Player pl){
		
	}
}
class musicProgress extends Thread{
	BigDecimal bd = new BigDecimal(PlayerModel.pl.getDuration().getSeconds());
	int i = 0;
	int playtime;
	int wholetime;
	public void run(){
		while(PlayerModel.playflag){
			i++;
			int time = (int) (PlayerModel.pl.getMediaTime().getSeconds()*145.0/PlayerModel.pl.getDuration().getSeconds());
			playtime = (int)PlayerModel.pl.getMediaTime().getSeconds();
			wholetime = (int)PlayerModel.pl.getDuration().getSeconds();
			System.out.println((int)PlayerModel.pl.getMediaTime().getSeconds());
			System.out.println((int)PlayerModel.pl.getDuration().getSeconds());
			PlayerFrame.progress.setValue(time);
			System.out.println("success"+i);
			if(playtime == wholetime){
				PlayerModel.pl.setMediaTime(new Time(0));
				PlayerModel.pl.stop();
				if(PlayerModel.loopflag){
					PlayerModel.pl.start();
					
				}
				else{
					System.out.println("successfully played");
					PlayerFrame.play.setText("����");
					return;
				}
			  }
		}
	}
}
